FROM khinkali/wildfly-postgres:0.0.10

MAINTAINER Robert Brem <brem_robert@hotmail.com>

ADD target/kafka-backup.war ${JBOSS_HOME}/

CMD ${JBOSS_HOME}/start.sh