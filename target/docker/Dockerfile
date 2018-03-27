FROM java
ENV HOME="/root"
RUN ["apt-get", "update"]
RUN ["apt-get", "install", "-y", "nmap", "unzip"]
COPY 0/docker-client-1.0-SNAPSHOT.zip /root/docker-client-1.0-SNAPSHOT.zip
WORKDIR /root
RUN ["unzip", "-o", "docker-client-1.0-SNAPSHOT.zip"]
RUN ["chmod", "777", "-R", "\/root\/docker-client-1.0-SNAPSHOT"]
WORKDIR /root/docker-client-1.0-SNAPSHOT