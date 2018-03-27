package com.sombrainc.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.ImplementedBy;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import play.libs.ws.WSResponse;

@ImplementedBy(SimpleDockerClientImpl.class)
public interface SimpleDockerClient {

  CompletableFuture<WSResponse> listAllRunningContainers();

  CompletableFuture<WSResponse> createContainer(JsonNode params);

  CompletableFuture<WSResponse> startContainer(String containerId);

  CompletableFuture<WSResponse> killContainer(String containerId);

  CompletableFuture<WSResponse> stopContainer(String containerId);

  CompletableFuture<WSResponse> removeContainer(String containerId, Map<String, String> params);

  CompletableFuture<WSResponse> execCreate(String containerId, Map<String, Object> params);

  CompletableFuture<WSResponse> execStart(String execId, Map<String, Object> params);

  CompletableFuture<WSResponse> inspectContainer(String containerId);

  CompletableFuture<WSResponse> listProcessesInsideContainer(String containerId);

  CompletableFuture<Boolean> inspectContainerAvailability(String containerUrl);
}
