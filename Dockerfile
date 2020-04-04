FROM openjdk:8

ENV SBT_HOME="/opt/sbt"
ENV SBT_VERSION="1.3.9"
ENV PATH="${SBT_HOME}/bin:${PATH}"

ENV SPARK_HOME="/opt/spark"
ENV SPARK_VERSION="3.0.0-preview2"
ENV HADOOP_VERSION="2.7"
ENV PATH="${SPARK_HOME}/bin:${PATH}"

WORKDIR /opt

# SBT
ADD "https://piccolo.link/sbt-${SBT_VERSION}.tgz" .
RUN tar -xzf sbt*.tgz && rm -f sbt*.tgz

# Spark
ADD "https://downloads.apache.org/spark/spark-${SPARK_VERSION}/spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz" .
RUN tar -xzf spark*.tgz && rm -f spark*.tgz && mv spark* spark

CMD tail -f /dev/null
