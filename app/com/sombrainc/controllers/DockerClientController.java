package com.sombrainc.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.common.collect.Lists;
import com.sombrainc.common.SimpleDockerClientConstants;
import com.sombrainc.service.DockerClientService;
import com.sombrainc.service.SimpleDockerClient;
import com.google.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.BodyParser.Of;
import play.mvc.Controller;
import play.mvc.Result;

public class DockerClientController extends Controller {

  @Inject
  private DockerClientService dockerClientService;

  public CompletableFuture<Result> listAllRunningContainers(){
    return dockerClientService.listAllRunnningContainers().thenApply(containerInfos -> ok(Json.toJson(containerInfos)));
  }

  @Of(BodyParser.Json.class)
  public CompletableFuture<Result> runContainer(){
    final JsonNode createParams = request().body().asJson();
    return dockerClientService.runContainer(createParams)
        .thenApply(id -> ok(String.format(SimpleDockerClientConstants.CONTAINER_INFO_SAVED, id)));
  }

}
