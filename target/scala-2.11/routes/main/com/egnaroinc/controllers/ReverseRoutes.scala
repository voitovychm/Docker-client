
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/egnaro/development/db-access-service/conf/DockerClient.routes
// @DATE:Fri Nov 17 03:41:14 IST 2017

import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:1
package com.egnaroinc.controllers {

  // @LINE:1
  class ReverseMainController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:2
    def encryptTest(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "encrypt")
    }
  
    // @LINE:3
    def reloadMongoDaemon(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "reloadDaemon")
    }
  
    // @LINE:4
    def healthCheck(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "healthCheck")
    }
  
    // @LINE:1
    def verifyDBClient(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "queryDB")
    }
  
  }


}
