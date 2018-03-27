
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/egnaro/development/db-access-service/conf/DockerClient.routes
// @DATE:Fri Nov 17 03:41:14 IST 2017

package DBAccessService

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:1
  MainController_0: com.egnaroinc.controllers.MainController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:1
    MainController_0: com.egnaroinc.controllers.MainController
  ) = this(errorHandler, MainController_0, "/")

  import ReverseRouteContext.empty

  def withPrefix(prefix: String): Routes = {
    DBAccessService.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, MainController_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """queryDB""", """com.egnaroinc.controllers.MainController.verifyDBClient()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """encrypt""", """com.egnaroinc.controllers.MainController.encryptTest()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reloadDaemon""", """com.egnaroinc.controllers.MainController.reloadMongoDaemon()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """healthCheck""", """com.egnaroinc.controllers.MainController.healthCheck()"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:1
  private[this] lazy val com_egnaroinc_controllers_MainController_verifyDBClient0_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("queryDB")))
  )
  private[this] lazy val com_egnaroinc_controllers_MainController_verifyDBClient0_invoker = createInvoker(
    MainController_0.verifyDBClient(),
    HandlerDef(this.getClass.getClassLoader,
      "DBAccessService",
      "com.egnaroinc.controllers.MainController",
      "verifyDBClient",
      Nil,
      "POST",
      """""",
      this.prefix + """queryDB"""
    )
  )

  // @LINE:2
  private[this] lazy val com_egnaroinc_controllers_MainController_encryptTest1_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("encrypt")))
  )
  private[this] lazy val com_egnaroinc_controllers_MainController_encryptTest1_invoker = createInvoker(
    MainController_0.encryptTest(),
    HandlerDef(this.getClass.getClassLoader,
      "DBAccessService",
      "com.egnaroinc.controllers.MainController",
      "encryptTest",
      Nil,
      "POST",
      """""",
      this.prefix + """encrypt"""
    )
  )

  // @LINE:3
  private[this] lazy val com_egnaroinc_controllers_MainController_reloadMongoDaemon2_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reloadDaemon")))
  )
  private[this] lazy val com_egnaroinc_controllers_MainController_reloadMongoDaemon2_invoker = createInvoker(
    MainController_0.reloadMongoDaemon(),
    HandlerDef(this.getClass.getClassLoader,
      "DBAccessService",
      "com.egnaroinc.controllers.MainController",
      "reloadMongoDaemon",
      Nil,
      "POST",
      """""",
      this.prefix + """reloadDaemon"""
    )
  )

  // @LINE:4
  private[this] lazy val com_egnaroinc_controllers_MainController_healthCheck3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("healthCheck")))
  )
  private[this] lazy val com_egnaroinc_controllers_MainController_healthCheck3_invoker = createInvoker(
    MainController_0.healthCheck(),
    HandlerDef(this.getClass.getClassLoader,
      "DBAccessService",
      "com.egnaroinc.controllers.MainController",
      "healthCheck",
      Nil,
      "GET",
      """""",
      this.prefix + """healthCheck"""
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:1
    case com_egnaroinc_controllers_MainController_verifyDBClient0_route(params) =>
      call { 
        com_egnaroinc_controllers_MainController_verifyDBClient0_invoker.call(MainController_0.verifyDBClient())
      }
  
    // @LINE:2
    case com_egnaroinc_controllers_MainController_encryptTest1_route(params) =>
      call { 
        com_egnaroinc_controllers_MainController_encryptTest1_invoker.call(MainController_0.encryptTest())
      }
  
    // @LINE:3
    case com_egnaroinc_controllers_MainController_reloadMongoDaemon2_route(params) =>
      call { 
        com_egnaroinc_controllers_MainController_reloadMongoDaemon2_invoker.call(MainController_0.reloadMongoDaemon())
      }
  
    // @LINE:4
    case com_egnaroinc_controllers_MainController_healthCheck3_route(params) =>
      call { 
        com_egnaroinc_controllers_MainController_healthCheck3_invoker.call(MainController_0.healthCheck())
      }
  }
}
