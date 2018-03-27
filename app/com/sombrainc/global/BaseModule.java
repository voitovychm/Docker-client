package com.sombrainc.global;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.sombrainc.repository.DockerClientDAO;
import com.sombrainc.repository.IDockerClientDAO;
import org.mongodb.morphia.Datastore;

public class BaseModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(Datastore.class).toProvider(DatastoreProvider.class).asEagerSingleton();
    bind(IDockerClientDAO.class).to(DockerClientDAO.class).in(Singleton.class);
  }
}
