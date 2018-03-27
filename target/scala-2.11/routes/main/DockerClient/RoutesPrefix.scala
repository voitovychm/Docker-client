
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/sombra-31/Hdd/development/docker-client/conf/DockerClient.routes
// @DATE:Tue Mar 27 17:40:59 EEST 2018


package DockerClient {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
