package com.sombrainc.entity;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity(value = "ContainerInfo")
public class ContainerInfoEntity extends ContainerInfo {
  @Id
  private String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ContainerInfoEntity(String id) {
    this.id = id;
  }

  public ContainerInfoEntity(ContainerInfo containerInfo) {
    this.setImageName(containerInfo.getImageName());
    this.setContainerName(containerInfo.getContainerName());
    this.setHostname(containerInfo.getHostname());
    this.setIpAddress(containerInfo.getIpAddress());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }

    ContainerInfoEntity that = (ContainerInfoEntity) o;

    return id != null ? id.equals(that.id) : that.id == null;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (id != null ? id.hashCode() : 0);
    return result;
  }

}
