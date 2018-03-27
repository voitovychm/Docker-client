package com.sombrainc.repository;

import com.sombrainc.entity.ContainerInfo;
import com.sombrainc.entity.ContainerInfoEntity;

public interface IDockerClientDAO {

  String put(ContainerInfoEntity element);

  ContainerInfoEntity get(String id);

}
