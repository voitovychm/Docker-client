
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/egnaro/development/db-access-service/conf/DockerClient.routes
// @DATE:Fri Nov 17 03:41:14 IST 2017

import play.api.routing.JavaScriptReverseRoute
import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:1
package com.egnaroinc.controllers.javascript {
  import ReverseRouteContext.empty

  // @LINE:1
  class ReverseMainController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:2
    def encryptTest: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "com.egnaroinc.controllers.MainController.encryptTest",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "encrypt"})
        }
      """
    )
  
    // @LINE:3
    def reloadMongoDaemon: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "com.egnaroinc.controllers.MainController.reloadMongoDaemon",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "reloadDaemon"})
        }
      """
    )
  
    // @LINE:4
    def healthCheck: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "com.egnaroinc.controllers.MainController.healthCheck",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "healthCheck"})
        }
      """
    )
  
    // @LINE:1
    def verifyDBClient: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "com.egnaroinc.controllers.MainController.verifyDBClient",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "queryDB"})
        }
      """
    )
  
  }


}
