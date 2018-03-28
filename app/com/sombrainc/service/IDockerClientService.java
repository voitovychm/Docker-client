package com.sombrainc.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.ImplementedBy;
import com.sombrainc.entity.ContainerInfo;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@ImplementedBy(DockerClientService.class)
public interface IDockerClientService {

  CompletableFuture<List<ContainerInfo>> listAllRunnningContainers();

  CompletableFuture<String> runContainer(JsonNode createParam);

  CompletionStage<String> killContainer(String imageName);
}
