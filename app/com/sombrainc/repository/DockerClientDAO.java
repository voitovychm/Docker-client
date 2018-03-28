package com.sombrainc.repository;

import com.google.inject.Inject;
import com.sombrainc.common.SimpleDockerClientConstants;
import com.sombrainc.entity.ContainerInfoEntity;
import java.util.List;
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

  @Override
  public ContainerInfoEntity removeByContainerId(String containerId) {
    final ContainerInfoEntity deletedEntity = datastore.findAndDelete(datastore
        .find(ContainerInfoEntity.class, SimpleDockerClientConstants.CONTAINER_ID_DB_PARAM,
            containerId));
    return deletedEntity;
  }

  @Override
  public List<ContainerInfoEntity> getByImageName(String imageName) {
    final List<ContainerInfoEntity> containerInfoEntities = datastore
        .find(ContainerInfoEntity.class,
            SimpleDockerClientConstants.IMAGE_NAME_DB_PARAM, imageName).asList();
    return containerInfoEntities;
  }

}
