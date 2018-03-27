
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/egnaro/development/db-access-service/conf/DockerClient.routes
// @DATE:Fri Nov 17 03:41:14 IST 2017

package com.egnaroinc.controllers;

import DBAccessService.RoutesPrefix;

public class routes {
  
  public static final com.egnaroinc.controllers.ReverseMainController MainController = new com.egnaroinc.controllers.ReverseMainController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final com.egnaroinc.controllers.javascript.ReverseMainController MainController = new com.egnaroinc.controllers.javascript.ReverseMainController(RoutesPrefix.byNamePrefix());
  }

}
