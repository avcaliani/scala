#!/bin/bash
#
# Script Name       : run.sh
# Author            : Anthony Vilarim Caliani
# GitHub            : https://github.com/avcaliani
# Date              : 2020.04.xx
# License           : MIT
#
# Description       : The following script runs "spark-app" through Spark Submit.
PROJECT_DIR="./"
JAR_DIR="target/scala-*"

INFO="\033[0;34mINFO\033[00m:"
ERROR="\033[0;31mERROR\033[00m:"


# =~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=
#  FUNCTIONS
# =~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=

is_installed() {
    if [ -z $(command -v $1) ]; then
      echo -e "${ERROR} '$1' not found, please check it first."
      exit 0
    fi
}


# =~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=
#  RUNNING...
# =~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=

echo "
   ___                   _
  / __| _ __  __ _  _ _ | |__
  \\__ \\| '_ \/ _\` || '_|| / /
  |___/| .__/\\__,_||_|  |_\\_\\
       |_|      APP v20.04.00
"

is_installed "java"
is_installed "spark-submit"
is_installed "sbt"

cd ${PROJECT_DIR}

echo -e "${INFO} Building project..."
sbt clean compile package

jar_file=$(ls ${JAR_DIR}/*.jar)
echo -e "${INFO} JAR File -> '${jar_file}'"

echo -e "${INFO} Starting Spark..."
spark-submit \
    --master local \
    --files log4j.properties \
    --driver-java-options "
        -Dlog4j.debug
        -Dlog4j.configuration=file:log4j.properties
    " \
    ${jar_file}

echo -e "${INFO} Process has been finished \\o/"
