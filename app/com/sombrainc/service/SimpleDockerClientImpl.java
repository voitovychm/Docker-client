package com.sombrainc.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.sombrainc.common.SimpleDockerClientConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.net.ConnectException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.Configuration;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import play.libs.ws.WSResponse;


@Singleton
public class SimpleDockerClientImpl implements SimpleDockerClient {

  public static final Logger LOGGER = LoggerFactory.getLogger(SimpleDockerClientImpl.class);

  @Inject
  private WSClient wsClient;

  @Inject
  private Configuration configuration;

  private String DEFAULT_DAEMON_HOST_AND_PORT;

  @Inject
  private void init() {
    DEFAULT_DAEMON_HOST_AND_PORT = Optional
        .ofNullable(configuration.getConfig(SimpleDockerClientConstants.DOCKER_CONFIG))
        .map(dockerConfig -> dockerConfig.getString(SimpleDockerClientConstants.DOCKER_URL))
        .orElseThrow(() ->
            new RuntimeException(String.format(SimpleDockerClientConstants.CONFIG_IS_NOT_PRESENT,
                SimpleDockerClientConstants.DOCKER_CONFIG + SimpleDockerClientConstants.DOT
                    + SimpleDockerClientConstants.DOCKER_URL)));
  }

  @Override
  public CompletableFuture<WSResponse> listAllRunningContainers() {
    return wsClient.url(
        new StringBuilder(DEFAULT_DAEMON_HOST_AND_PORT)
            .append(SimpleDockerClientConstants.CONTAINERS_ENDPOINT_PART).append(SimpleDockerClientConstants.JSON_FORMAT).toString())
        .get().toCompletableFuture();
  }

  @Override
  public CompletableFuture<WSResponse> createContainer(JsonNode params) {
      CompletionStage<WSResponse> response = wsClient
          .url(new StringBuilder(DEFAULT_DAEMON_HOST_AND_PORT)
              .append(SimpleDockerClientConstants.CONTAINERS_CREATE_ENDPOINT_PART)
                  .toString())
          .setContentType(SimpleDockerClientConstants.APPLICATION_JSON)
          .post(params);
      return response.toCompletableFuture();
  }

  @Override
  public CompletableFuture<WSResponse> startContainer(String containerId) {
    CompletionStage<WSResponse> response = wsClient.url(
        new StringBuilder(DEFAULT_DAEMON_HOST_AND_PORT)
            .append(SimpleDockerClientConstants.CONTAINERS_ENDPOINT_PART)
            .append(containerId).append(SimpleDockerClientConstants.START_EXEC_ENDPOINT).toString())
        .setContentType(SimpleDockerClientConstants.APPLICATION_JSON).post(SimpleDockerClientConstants.EMPTY_STRING);
    return response.toCompletableFuture();
  }

  @Override
  public CompletableFuture<WSResponse> killContainer(String containerId) {
    CompletionStage<WSResponse> response = wsClient.url(
        new StringBuilder(DEFAULT_DAEMON_HOST_AND_PORT)
            .append(SimpleDockerClientConstants.CONTAINERS_ENDPOINT_PART).append(containerId)
            .append(SimpleDockerClientConstants.KILL_ENDPOINT_PART).toString())
        .setContentType(SimpleDockerClientConstants.APPLICATION_JSON).post(SimpleDockerClientConstants.EMPTY_STRING);
    return response.toCompletableFuture();
  }

  @Override
  public CompletableFuture<WSResponse> stopContainer(String containerId) {
    CompletionStage<WSResponse> response = wsClient.url(
        new StringBuilder(DEFAULT_DAEMON_HOST_AND_PORT)
            .append(SimpleDockerClientConstants.CONTAINERS_ENDPOINT_PART).append(containerId)
            .append(SimpleDockerClientConstants.STOP_ENDPOINT_PART).toString())
        .setContentType(SimpleDockerClientConstants.APPLICATION_JSON).post(SimpleDockerClientConstants.EMPTY_STRING);
    return response.toCompletableFuture();
  }

  @Override
  public CompletableFuture<WSResponse> removeContainer(String containerId,
      Map<String, String> params) {
    final WSRequest deleteRequest = wsClient.url(
        new StringBuilder(DEFAULT_DAEMON_HOST_AND_PORT)
            .append(SimpleDockerClientConstants.CONTAINERS_ENDPOINT_PART).append(containerId).toString())
        .setContentType(SimpleDockerClientConstants.APPLICATION_JSON);
    Optional.ofNullable(params).ifPresent(paramlist -> paramlist.entrySet()
        .forEach(entry -> deleteRequest.setQueryParameter(entry.getKey(), entry.getValue())));
    return deleteRequest.delete().toCompletableFuture();
  }

  @Override
  public CompletableFuture<WSResponse> execCreate(String containerId, Map<String, Object> params) {
    try {
      CompletionStage<WSResponse> response = wsClient.url(
          new StringBuilder(DEFAULT_DAEMON_HOST_AND_PORT)
              .append(SimpleDockerClientConstants.CONTAINERS_ENDPOINT_PART)
              .append(containerId).append(SimpleDockerClientConstants.EXEC_ENDPOINT_END_PART).toString())
          .setContentType(SimpleDockerClientConstants.APPLICATION_JSON)
          .post(SimpleDockerClientConstants.OBJECT_MAPPER.writeValueAsString(params));
      return response.toCompletableFuture();
    } catch (JsonProcessingException e) {
      LOGGER.error(e.getLocalizedMessage(), e);
      throw new RuntimeException(e);
    }
  }

  @Override
  public CompletableFuture<WSResponse> execStart(String execId, Map<String, Object> params) {
    try {
      CompletionStage<WSResponse> response = wsClient
          .url(new StringBuilder(DEFAULT_DAEMON_HOST_AND_PORT)
              .append(SimpleDockerClientConstants.EXEC_ENDPOINT_PART).append(execId)
              .append(SimpleDockerClientConstants.START_EXEC_ENDPOINT).toString())
          .setContentType(SimpleDockerClientConstants.APPLICATION_JSON)
          .post(SimpleDockerClientConstants.OBJECT_MAPPER.writeValueAsString(params));
      return response.toCompletableFuture();
    } catch (JsonProcessingException e) {
      LOGGER.error(e.getLocalizedMessage(), e);
      throw new RuntimeException(e);
    }
  }

  @Override
  public CompletableFuture<WSResponse> inspectContainer(String containerId) {
    CompletionStage<WSResponse> response = wsClient.url(
        new StringBuilder(DEFAULT_DAEMON_HOST_AND_PORT)
            .append(SimpleDockerClientConstants.CONTAINERS_ENDPOINT_PART).append(containerId)
            .append(SimpleDockerClientConstants.SLASH).append(SimpleDockerClientConstants.JSON_FORMAT).toString())
        .get();
    return response.toCompletableFuture();
  }

  @Override
  public CompletableFuture<WSResponse> listProcessesInsideContainer(String containerId) {
    CompletionStage<WSResponse> response = wsClient.url(
        new StringBuilder(DEFAULT_DAEMON_HOST_AND_PORT)
            .append(SimpleDockerClientConstants.CONTAINERS_ENDPOINT_PART).append(containerId)
            .append(SimpleDockerClientConstants.SLASH).append(SimpleDockerClientConstants.TOP_PATH).toString())
        .get();
    return response.toCompletableFuture();
  }

  @Override
  public CompletableFuture<Boolean> inspectContainerAvailability(String containerUrl) {
    final int ATTEMPTS_COUNT = 20;
    int iterator = 1;
    while (iterator <= ATTEMPTS_COUNT) {
      int status = checkAvailability(containerUrl);
      if (status == 200 || status == 201 || status == 204) {
        break;
      } else {
        try {
          Thread.sleep(1000);
          iterator++;
        } catch (InterruptedException e) {
          LOGGER.error(e.getLocalizedMessage());
          return CompletableFuture.completedFuture(Boolean.FALSE);
        }
      }
    }
    if (iterator > ATTEMPTS_COUNT) {
      return CompletableFuture.completedFuture(Boolean.FALSE);
    }
    return CompletableFuture.completedFuture(Boolean.TRUE);
  }

  private int checkAvailability(String containerUrl) {
    try {
      CompletionStage<WSResponse> response = wsClient.url(containerUrl).get();
      return response.toCompletableFuture().get().getStatus();
    } catch (InterruptedException | ExecutionException e) {
      if (e instanceof ConnectException || e instanceof ExecutionException) {
        LOGGER.error(e.getLocalizedMessage());
        return checkAvailability(containerUrl);
      }
      LOGGER.error(e.getLocalizedMessage());
      throw new RuntimeException(e);
    }
  }

}
