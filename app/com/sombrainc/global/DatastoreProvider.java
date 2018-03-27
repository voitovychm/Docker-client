package com.sombrainc.global;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.sombrainc.common.SimpleDockerClientConstants;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import play.Configuration;
import play.api.inject.ApplicationLifecycle;

@Singleton
public class DatastoreProvider implements Provider<Datastore> {

  @Inject
  private Configuration configuration;

  @Inject
  private ApplicationLifecycle lifecycle;

  private MongoClient mongoClient;

  private Datastore datastore;

  @Inject
  private void init() {
    final String host = Optional
        .ofNullable(configuration.getConfig(SimpleDockerClientConstants.DB_CONFIG))
        .map(dbConfig -> dbConfig.getString(SimpleDockerClientConstants.DB_HOST_CONFIG))
        .orElseThrow(() -> new RuntimeException(String.format(
            SimpleDockerClientConstants.CONFIG_IS_NOT_PRESENT,
            SimpleDockerClientConstants.DB_CONFIG + SimpleDockerClientConstants.DOT
                + SimpleDockerClientConstants.DB_HOST_CONFIG)));
    final Integer port = Optional
        .ofNullable(configuration.getConfig(SimpleDockerClientConstants.DB_CONFIG))
        .map(dbConfig -> dbConfig.getInt(SimpleDockerClientConstants.DB_PORT_CONFIG))
        .orElseThrow(() -> new RuntimeException(String.format(
            SimpleDockerClientConstants.CONFIG_IS_NOT_PRESENT,
            SimpleDockerClientConstants.DB_CONFIG + SimpleDockerClientConstants.DOT
                + SimpleDockerClientConstants.DB_PORT_CONFIG)));
    final String dbName = Optional
        .ofNullable(configuration.getConfig(SimpleDockerClientConstants.DB_CONFIG))
        .map(dbConfig -> dbConfig.getString(SimpleDockerClientConstants.DB_NAME_CONFIG))
        .orElseThrow(() -> new RuntimeException(String.format(
            SimpleDockerClientConstants.CONFIG_IS_NOT_PRESENT,
            SimpleDockerClientConstants.DB_CONFIG + SimpleDockerClientConstants.DOT
                + SimpleDockerClientConstants.DB_NAME_CONFIG)));

    mongoClient = new MongoClient(new ServerAddress(host, port));

    final Morphia morphia = new Morphia();
    lifecycle.addStopHook(() -> {
      mongoClient.close();
      return CompletableFuture.completedFuture(null);
    });
    datastore = morphia.createDatastore(mongoClient, dbName);

  }

  public MongoClient getMongoClient() {
    return mongoClient;
  }

  @Override
  public Datastore get() {
    return datastore;
  }
}
