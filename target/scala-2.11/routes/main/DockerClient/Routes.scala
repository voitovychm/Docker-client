
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/sombra-31/Hdd/development/docker-client/conf/DockerClient.routes
// @DATE:Tue Mar 27 17:40:59 EEST 2018

package DockerClient

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:1
  DockerClientController_0: com.sombrainc.controllers.DockerClientController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:1
    DockerClientController_0: com.sombrainc.controllers.DockerClientController
  ) = this(errorHandler, DockerClientController_0, "/")

  import ReverseRouteContext.empty

  def withPrefix(prefix: String): Routes = {
    DockerClient.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, DockerClientController_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """containers/running""", """com.sombrainc.controllers.DockerClientController.listAllRunningContainers()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """containers""", """com.sombrainc.controllers.DockerClientController.runContainer()"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:1
  private[this] lazy val com_sombrainc_controllers_DockerClientController_listAllRunningContainers0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("containers/running")))
  )
  private[this] lazy val com_sombrainc_controllers_DockerClientController_listAllRunningContainers0_invoker = createInvoker(
    DockerClientController_0.listAllRunningContainers(),
    HandlerDef(this.getClass.getClassLoader,
      "DockerClient",
      "com.sombrainc.controllers.DockerClientController",
      "listAllRunningContainers",
      Nil,
      "GET",
      """""",
      this.prefix + """containers/running"""
    )
  )

  // @LINE:2
  private[this] lazy val com_sombrainc_controllers_DockerClientController_runContainer1_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("containers")))
  )
  private[this] lazy val com_sombrainc_controllers_DockerClientController_runContainer1_invoker = createInvoker(
    DockerClientController_0.runContainer(),
    HandlerDef(this.getClass.getClassLoader,
      "DockerClient",
      "com.sombrainc.controllers.DockerClientController",
      "runContainer",
      Nil,
      "POST",
      """""",
      this.prefix + """containers"""
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:1
    case com_sombrainc_controllers_DockerClientController_listAllRunningContainers0_route(params) =>
      call { 
        com_sombrainc_controllers_DockerClientController_listAllRunningContainers0_invoker.call(DockerClientController_0.listAllRunningContainers())
      }
  
    // @LINE:2
    case com_sombrainc_controllers_DockerClientController_runContainer1_route(params) =>
      call { 
        com_sombrainc_controllers_DockerClientController_runContainer1_invoker.call(DockerClientController_0.runContainer())
      }
  }
}
