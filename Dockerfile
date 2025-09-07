FROM maven:3.9.11-eclipse-temurin-17 AS maven

FROM eclipse-temurin:17-jdk

ARG     BUILD_DATE=unknown
ARG     BUILD_VERSION=unknown
ARG     IMAGE_DESCRIPTION=unknown
ARG     IMAGE_NAME=unknown
ARG     UID=1010
ARG     GID=1010

ENV     MAVEN_HOME=/usr/share/maven
ENV     MAVEN_CONFIG=/home/group6/.m2

LABEL   group6.game-hub-api.build-date=$BUILD_DATE
LABEL   group6.game-hub-api.name=$IMAGE_NAME
LABEL   group6.game-hub-api.description=$IMAGE_DESCRIPTION
LABEL   group6.game-hub-api.base.image="eclipse-temurin:17-jdk"
LABEL   group6.game-hub-api.version=$BUILD_VERSION
LABEL   maintainer="GROUP6"

RUN     apt-get update \
        && apt-get install -y --no-install-recommends \
        curl libev4 libev-dev bash procps tar openssh-client \
        && groupadd -g "${GID}" group6 \
        && useradd --create-home --no-log-init -u "${UID}" -g group6 group6 \
        && apt-get clean \
        && rm -rf /var/lib/apt/lists/*

COPY    --from=maven ${MAVEN_HOME} ${MAVEN_HOME}
COPY    --from=maven /usr/local/bin/mvn-entrypoint.sh /usr/local/bin/mvn-entrypoint.sh
COPY    --from=maven /usr/share/maven/ref/settings-docker.xml /usr/share/maven/ref/settings-docker.xml

RUN     ln -s ${MAVEN_HOME}/bin/mvn /usr/bin/mvn

USER    group6

WORKDIR /opt/app

COPY    --chown=group6:group6 pom.xml .

RUN     mvn dependency:go-offline

COPY    --chown=group6:group6 src ./src

RUN     mvn clean package -DskipTests

ENTRYPOINT ["/usr/local/bin/mvn-entrypoint.sh"]

CMD     ["mvn", "spring-boot:run"]