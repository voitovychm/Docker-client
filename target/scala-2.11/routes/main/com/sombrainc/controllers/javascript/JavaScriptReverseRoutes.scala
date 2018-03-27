
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/sombra-31/Hdd/development/docker-client/conf/DockerClient.routes
// @DATE:Tue Mar 27 17:40:59 EEST 2018

import play.api.routing.JavaScriptReverseRoute
import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:1
package com.sombrainc.controllers.javascript {
  import ReverseRouteContext.empty

  // @LINE:1
  class ReverseDockerClientController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:1
    def listAllRunningContainers: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "com.sombrainc.controllers.DockerClientController.listAllRunningContainers",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "containers/running"})
        }
      """
    )
  
    // @LINE:2
    def runContainer: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "com.sombrainc.controllers.DockerClientController.runContainer",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "containers"})
        }
      """
    )
  
  }


}
