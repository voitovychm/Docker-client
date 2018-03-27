package com.sombrainc.repository;

import com.google.inject.Inject;
import com.sombrainc.entity.ContainerInfoEntity;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.dao.BasicDAO;

public class DockerClientDAO extends BasicDAO<ContainerInfoEntity, String> implements IDockerClientDAO {

  @Inject
  private Datastore datastore;

  @Inject
  public DockerClientDAO(Datastore datastore){
    super(ContainerInfoEntity.class, datastore);
  }


  @Override
  public String put(ContainerInfoEntity containerInfo) {
    final Key<ContainerInfoEntity> key = datastore.save(containerInfo);
    return key.getId().toString();
  }

  @Override
  public ContainerInfoEntity get(String id) {
    final ContainerInfoEntity containerInfo = datastore.get(ContainerInfoEntity.class, id);
    return containerInfo;
  }
}
