package com.sombrainc.repository;

import com.sombrainc.entity.ContainerInfo;
import com.sombrainc.entity.ContainerInfoEntity;
import java.util.List;

public interface IDockerClientDAO {

  String put(ContainerInfoEntity element);

  ContainerInfoEntity get(String id);

  ContainerInfoEntity removeByContainerId(String containerId);

  List<ContainerInfoEntity> getByImageName(String imageName);
}
