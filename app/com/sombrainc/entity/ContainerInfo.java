package com.sombrainc.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.common.collect.Lists;
import com.sombrainc.common.SimpleDockerClientConstants;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ContainerInfo {

  private String imageName;
  private String containerName;
  private String containerId;
  private String hostname;
  private String ipAddress;

  public String getImageName() {
    return imageName;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  public String getContainerName() {
    return containerName;
  }

  public void setContainerName(String containerName) {
    this.containerName = containerName;
  }

  public String getHostname() {
    return hostname;
  }

  public void setHostname(String hostname) {
    this.hostname = hostname;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  public String getContainerId() {
    return containerId;
  }

  public void setContainerId(String containerId) {
    this.containerId = containerId;
  }

  public static ContainerInfo fromInspectContainerJson(JsonNode containerJson) {
    ContainerInfo containerInfo = new ContainerInfo();
    Optional.ofNullable(containerJson.get(
        SimpleDockerClientConstants.CONFIG_PARAM))
        .map(configs -> configs.get(SimpleDockerClientConstants.IMAGE_PARAM)).map(JsonNode::asText)
        .ifPresent(containerInfo::setImageName);
    Optional.ofNullable(containerJson.get(
        SimpleDockerClientConstants.CONFIG_PARAM))
        .map(configs -> configs.get(SimpleDockerClientConstants.CONTAINER_HOST_NAME))
        .map(JsonNode::asText).ifPresent(containerInfo::setHostname);
    Optional.ofNullable(containerJson.get(
        SimpleDockerClientConstants.NAME_PARAM)).map(JsonNode::asText)
        .ifPresent(containerInfo::setContainerName);
    Optional.ofNullable(containerJson.get(
        SimpleDockerClientConstants.NETWORK_SETTINGS_PARAM))
        .map(networkSettings -> networkSettings.get(SimpleDockerClientConstants.IP_ADDRESS_PARAM))
        .map(JsonNode::asText).ifPresent(containerInfo::setIpAddress);
    Optional.ofNullable(containerJson.get(
        SimpleDockerClientConstants.ID_PARAM)).map(JsonNode::asText)
        .ifPresent(containerInfo::setContainerId);
    return containerInfo;
  }

  public static ContainerInfo fromContainerJson(JsonNode containerJson) {
    ContainerInfo containerInfo = new ContainerInfo();
    Optional.ofNullable(containerJson.get(
        SimpleDockerClientConstants.IMAGE_PARAM)).map(JsonNode::asText)
        .ifPresent(containerInfo::setImageName);
    Optional.ofNullable(containerJson.get(SimpleDockerClientConstants.CONTAINER_HOST_NAME))
        .map(JsonNode::asText).ifPresent(containerInfo::setHostname);
    Optional.ofNullable(containerJson.get(
        SimpleDockerClientConstants.NAMES_PARAM)).map(names -> {
          List<String> namesList = Lists.newArrayList();
          ((ArrayNode) names).forEach(name -> namesList.add(name.asText()));
          return namesList.stream().collect(Collectors.joining(SimpleDockerClientConstants.COMA));
    }).ifPresent(containerInfo::setContainerName);
    Optional.ofNullable(containerJson.findPath(
        SimpleDockerClientConstants.IP_ADDRESS_PARAM))
        .map(JsonNode::asText).ifPresent(containerInfo::setIpAddress);
    Optional.ofNullable(containerJson.get(
        SimpleDockerClientConstants.ID_PARAM)).map(JsonNode::asText)
        .ifPresent(containerInfo::setContainerId);
    return containerInfo;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ContainerInfo that = (ContainerInfo) o;

    if (imageName != null ? !imageName.equals(that.imageName) : that.imageName != null) {
      return false;
    }
    if (containerName != null ? !containerName.equals(that.containerName)
        : that.containerName != null) {
      return false;
    }
    if (containerId != null ? !containerId.equals(that.containerId) : that.containerId != null) {
      return false;
    }
    if (hostname != null ? !hostname.equals(that.hostname) : that.hostname != null) {
      return false;
    }
    return ipAddress != null ? ipAddress.equals(that.ipAddress) : that.ipAddress == null;
  }

  @Override
  public int hashCode() {
    int result = imageName != null ? imageName.hashCode() : 0;
    result = 31 * result + (containerName != null ? containerName.hashCode() : 0);
    result = 31 * result + (containerId != null ? containerId.hashCode() : 0);
    result = 31 * result + (hostname != null ? hostname.hashCode() : 0);
    result = 31 * result + (ipAddress != null ? ipAddress.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContainerInfo{" +
        "imageName='" + imageName + '\'' +
        ", containerName='" + containerName + '\'' +
        ", containerId='" + containerId + '\'' +
        ", hostname='" + hostname + '\'' +
        ", ipAddress='" + ipAddress + '\'' +
        '}';
  }
}
