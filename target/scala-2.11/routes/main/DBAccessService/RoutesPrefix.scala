
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/egnaro/development/db-access-service/conf/DockerClient.routes
// @DATE:Fri Nov 17 03:41:14 IST 2017


package DBAccessService {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
