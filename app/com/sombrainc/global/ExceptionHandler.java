package com.sombrainc.global;

import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.http.HttpErrorHandler;
import play.libs.Json;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import play.mvc.Results;

public class ExceptionHandler implements HttpErrorHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

  public CompletionStage<Result> onClientError(RequestHeader request, int statusCode,
      String message) {
    LOGGER.error(message);
    ObjectNode result = Json.newObject().put("message", message);
    return CompletableFuture.completedFuture(Results.status(statusCode, result));
  }

  @Override
  public CompletionStage<Result> onServerError(RequestHeader request, Throwable exception) {
    final String stackTrace = ExceptionUtils.getStackTrace(exception);
    LOGGER.error(stackTrace);
    return exception instanceof RuntimeException ? CompletableFuture
        .completedFuture(Results.badRequest(stackTrace))
        : CompletableFuture
            .completedFuture(Results.status(500, stackTrace));
  }
}

