package com.sombrainc.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class SimpleDockerClientConstants {


  public static final String COLON = ":";
  public static final String SEMICOLON = ";";
  //	Port related configs
  public static final String DB_ACCESS_SERVICE_CONTAINER_DEFAULT_PORT = "9010";
  public static final String DB_ACCESS_SERVICE_CONTAINER_DEFAULT_PORT_LINKED = new StringBuilder(DB_ACCESS_SERVICE_CONTAINER_DEFAULT_PORT)
      .append(            COLON)
      .append(DB_ACCESS_SERVICE_CONTAINER_DEFAULT_PORT).toString();
  public static final String TCP_PORT_PART = "/tcp";


  public static final String DB_ACCESS_SERVICE_CONTAINER_DEFAULT_PORT_TCP = new StringBuilder(DB_ACCESS_SERVICE_CONTAINER_DEFAULT_PORT).append(TCP_PORT_PART).toString();
  public static final String IMAGE_IS_NOT_RUNNING = "Image %s is not running";
  public static final String MULTIPLE_IMAGES_WITH_SAME_NAME_RUNNING = "Multiple images with name %s is running";
  public static final String ID_PARAM = "Id";
  public static final String MESSAGE = "message";
  public static final String DOCKER_CONFIG = "docker";
  public static final String DOCKER_URL = "hostUrl";
  public static final String IP_ADDRESS_PARAM = "IPAddress";
  public static final String DB_CONFIG = "datastore";
  public static final String DB_PORT_CONFIG = "port";
  public static final String DB_HOST_CONFIG = "host";
  public static final String DB_NAME_CONFIG = "dbName";
  public static final String CONFIG_PARAM = "Config";
  public static final String NETWORK_SETTINGS_PARAM = "NetworkSettings";

  public static final String DB_ACCESS_DEFAULT_PORT_TCP(String port){
    return new StringBuilder(port).append(TCP_PORT_PART).toString();
  }

  // HTTP related constants
  public static final String HTTP_PROTOCOL = "http://";
  public static final String TOPICS_PATH = "/topics/";
  public static final int MAX_RETRIES = 1000;

  public static final String ACCEPT_HEADER = "Accept";



  public static final String CONTAINER_HOST_NAME = "Hostname";
  public static final String DAO_ENTITY_CLASS_NAMED_PARAM = "DAO ENTITY CLASS";
  public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


  public static final String CONTAINER_NAME_IS_NOT_PRESENT = "Container name is not present";
  public static final String CONTAINER_ID_IS_NOT_PRESENT = "Container id is not present";
  public static final String CONTAINER_IS_KILLED = "Container %s with id %s is killed";
  public static final String CONTAINER_IS_NOT_PRESENT = "Container from image %s with id %s is not present";
  public static final String CONFIG_IS_NOT_PRESENT = "Config '%s' is not present";
  public static final String CONTAINER_INFO_SAVED = "Container info is created and stored to db with id %s";

  public static final String ONE_STRING = "1";
  public static final String DOT = ".";
  public static final String CONTAINERS_ENDPOINT_PART = "/containers/";
  public static final String EXEC_ENDPOINT_END_PART = "/exec";
  public static final String EXEC_ENDPOINT_PART = "/exec/";
  public static final String START_EXEC_ENDPOINT = "/start";
  public static final String STOP_ENDPOINT_PART = "/stop";
  public static final String TOP_PATH = "top";

  public static final String KILL_ENDPOINT_PART = "/kill";
  public static final String CONTAINERS_CREATE_ENDPOINT_PART = "/containers/create";
  public static final String NAME_REQUEST_PARAM = "?name=";
  public static final String RESPONSE_SUCCESS = "success";
  public static final String DEFAULT_DOCKER_DAEMON_HOST_PORT = "http://localhost:2375";
  public static final String KAFKA_CONFIG_ENABLED = "enabled";
  public static final String KAFKA_CONFIG_ENABLED_ON_STARTUP = "enableOnStartup";
  public static final String KAFKA_CONFIG = "kafka";
  public static final String TTY_PARAM = "Tty";
  public static final String DETACH_PARAM = "Detach";
  public static final String EXPOSED_PORTS_PARAM = "ExposedPorts";
  public static final String HOST_CONFIG_PARAM = "HostConfig";
  public static final String LINKS_PARAM = "Links";
  public static final String HOST_PORT_PARAM = "HostPort";
  public static final String PORT_BINDINGS_PARAM = "PortBindings";
  public static final String HOSTNAME_PARAM = "Hostname";
  public static final String IMAGE_PARAM = "Image";
  public static final String CMD_PARAM = "Cmd";
  public static final String ENV_PARAM = "Env";
  public static final String ATTACH_STDERR_PARAM = "AttachStderr";
  public static final String ENV_VARIABLE_MONGO_PORT = "MONGO_PORT";
  public static final String ENV_VARIABLE_MONGO_DB_PATH = "MONGO_DB_PATH";
  public static final String ENV_VARIABLE_MONGO_DB_CONFIG = "MONGO_DB_CONFIG";
  public static final String ATTACH_STDOUT_PARAM = "AttachStdout";

  public static final String ATTACH_STDIN_PARAM = "AttachStdin";
  public static final String BIN_SH_CMD = "/bin/sh";

  public static final String PARTITIONS_PARAMETER = " --partitions ";
  public static final String TOPIC_PARAMETER = " --topic ";
  public static final String TRUE_STRING = "true";
  public static final String AUTO_OFFSET_RESET_TIME_SMALLEST = "smallest";

  public static final String JSON_FORMAT = "json";
  public static final String JAVA_PROCESS_NAME_PART = "java";

  public static final String COMMAND = "-c";
  public static final String SLASH = "/";
  public static final String SPACE = " ";
  public static final String EMPTY_STRING = "";
  public static final String COMA = ",";
  public static final String CONTAINER_IS_ALREADY_STOPPED = "Container %s with id %s is already stopped";
  public static final String CONTAINER_DELETION_CONFLICT = "Conflict while removing container %s with id %s";

  public static final String VOLUME_REMOVE_PARAMETER = "v";
  public static final String VOLUMES_PARAM = "Volumes";
  public static final String BINDS_PARAM = "Binds";
  public static final String FORCE_REMOVE_PARAMETER = "force";
  public static final String LINK_REMOVE_PARAMETER = "link";
  public static final String CONTAINER_IS_ALREADY_STARTED = "Container from image %s with id %s is already stopped";
  public static final String DEFAULT_DAEMON_HOST_AND_PORT = "http://localhost:2375";
  public static final String APPLICATION_JSON = "application/json";
  public static final String NAME_PARAM = "Name";

  public static final String READ_WRITE_PERMISSION = "rw";
  public static final String PROCESSES_INSIDE_CONTAINER_PROPERTY = "Processes";
  public static final Config CONFIG = ConfigFactory.load();
}
