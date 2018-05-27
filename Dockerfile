FROM khinkali/wildfly-postgres:0.0.1

MAINTAINER Robert Brem <brem_robert@hotmail.com>

ADD target/kafka-backup.war ${JBOSS_HOME}/application.war

CMD ${JBOSS_HOME}/start.sh