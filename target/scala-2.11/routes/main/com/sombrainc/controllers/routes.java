
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/sombra-31/Hdd/development/docker-client/conf/DockerClient.routes
// @DATE:Tue Mar 27 17:40:59 EEST 2018

package com.sombrainc.controllers;

import DockerClient.RoutesPrefix;

public class routes {
  
  public static final com.sombrainc.controllers.ReverseDockerClientController DockerClientController = new com.sombrainc.controllers.ReverseDockerClientController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final com.sombrainc.controllers.javascript.ReverseDockerClientController DockerClientController = new com.sombrainc.controllers.javascript.ReverseDockerClientController(RoutesPrefix.byNamePrefix());
  }

}
