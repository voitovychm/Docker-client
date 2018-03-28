package com.sombrainc.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sombrainc.common.SimpleDockerClientConstants;
import com.sombrainc.entity.ContainerInfo;
import com.sombrainc.entity.ContainerInfoEntity;
import com.sombrainc.repository.IDockerClientDAO;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.ws.WSResponse;

@Singleton
public class DockerClientService implements IDockerClientService {

  public static final Logger LOGGER = LoggerFactory.getLogger(SimpleDockerClientImpl.class);

  @Inject
  private SimpleDockerClient simpleDockerClient;

  @Inject
  private IDockerClientDAO dockerClientDAO;

  @Override
  public CompletableFuture<List<ContainerInfo>> listAllRunnningContainers(){
    return simpleDockerClient.listAllRunningContainers().thenApply(containers -> {
      List<ContainerInfo> containerInfos = Lists.newArrayList();
      ((ArrayNode) containers.asJson()).forEach(node -> containerInfos.add(ContainerInfo.fromContainerJson(node)));
      return containerInfos;
    });
  }

  @Override
  public CompletableFuture<String> runContainer(JsonNode createParam){
    return simpleDockerClient
        .createContainer(createParam).thenCompose(
            createResponse -> {
              final String containerId = Optional.of(createResponse)
                  .filter(resp -> (resp.getStatus() == 201))
                  .map(WSResponse::asJson).flatMap(
                      json -> Optional.ofNullable(json.findPath(SimpleDockerClientConstants.ID_PARAM))
                          .map(JsonNode::asText))
                  .orElseThrow(() -> new RuntimeException(createResponse.getStatusText()));
              final String containerImage = Optional.ofNullable(createParam.findPath(SimpleDockerClientConstants.IMAGE_PARAM))
                          .map(JsonNode::asText)
                  .orElseThrow(() -> new RuntimeException(createResponse.getStatusText()));
              return getStartContainerResponse(containerImage, containerId)
                  .thenApply(WSResponse::asJson).thenApply(ContainerInfo::fromInspectContainerJson).thenApply(
                      ContainerInfoEntity::new).thenApply(dockerClientDAO::put);
            });
  }

  @Override
  public CompletionStage<String> killContainer(String imageName) {
    List<String> removedContainerIds = Lists.newArrayList();
    final List<CompletableFuture<WSResponse>> completableFutureList = dockerClientDAO
        .getByImageName(imageName).stream()
        .map(containerInfo -> simpleDockerClient.killContainer(containerInfo.getContainerId())
            .thenApply(wsResponse -> {
              final int statusCode = wsResponse.getStatus();

              if (statusCode == 204) {
                removedContainerIds.add(containerInfo.getContainerId());
                return dockerClientDAO.removeByContainerId(containerInfo.getContainerId());
              }
              if (statusCode == 404) {
                LOGGER.error(String
                    .format(SimpleDockerClientConstants.CONTAINER_IS_NOT_PRESENT,
                        containerInfo.getImageName(), containerInfo.getContainerId()));
                throw new RuntimeException(String
                    .format(SimpleDockerClientConstants.CONTAINER_IS_NOT_PRESENT,
                        containerInfo.getImageName(), containerInfo.getContainerId()));
              }

              if (statusCode == 500) {
                Optional.ofNullable(wsResponse.asJson())
                    .map(response -> response.get(SimpleDockerClientConstants.MESSAGE))
                    .map(JsonNode::asText).ifPresent(message -> {
                  LOGGER.error(message);
                  throw new RuntimeException(message);
                });
              }
              throw new RuntimeException();
            }).thenCompose(entity -> simpleDockerClient.removeContainer(entity.getContainerId(),
                new HashMap<String, String>() {{
                  put(SimpleDockerClientConstants.FORCE_REMOVE_PARAMETER, String.valueOf(true));
                }})))
        .collect(Collectors.toList());
    return CompletableFuture
        .allOf(completableFutureList.toArray(new CompletableFuture[completableFutureList.size()]))
        .thenApply(v ->
            String.format(SimpleDockerClientConstants.CONTAINERS_ARE_REMOVED, imageName,
                removedContainerIds.stream()
                    .collect(Collectors.joining(SimpleDockerClientConstants.COMA))));
  }

  private CompletableFuture<WSResponse> getStartContainerResponse(String containerLabel,
      String containerId) {
    return simpleDockerClient.startContainer(containerId)
        .handle((resp, ex) -> {
          if (ex != null) {
            throw new RuntimeException(ex);
          }
          int statusCode = resp.getStatus();
          if (statusCode == 204) {
            return simpleDockerClient.inspectContainer(containerId);
          }
          if (statusCode == 304) {
            LOGGER.error(String
                .format(SimpleDockerClientConstants.CONTAINER_IS_ALREADY_STARTED,
                    containerLabel, containerId));
            throw new RuntimeException(String
                .format(SimpleDockerClientConstants.CONTAINER_IS_ALREADY_STARTED,
                    containerLabel, containerId));
          }
          if (statusCode == 404) {
            LOGGER.error(String
                .format(SimpleDockerClientConstants.CONTAINER_IS_NOT_PRESENT,
                    containerLabel, containerId));
            throw new RuntimeException(String
                .format(SimpleDockerClientConstants.CONTAINER_IS_NOT_PRESENT,
                    containerLabel, containerId));
          }

          if (statusCode == 500) {
            Optional.ofNullable(resp.asJson())
                .map(response -> response.get(SimpleDockerClientConstants.MESSAGE))
                .map(JsonNode::asText).ifPresent(message -> {
              LOGGER.error(message);
              throw new RuntimeException(message);
            });
          }
          throw new RuntimeException(resp.getStatusText());
        }).thenCompose(e -> e);
  }

}
