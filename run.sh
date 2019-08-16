#!/bin/bash
#
# Script Name       : run.sh
# Author            : Anthony Vilarim Caliani
# GitHub            : https://github.com/avcaliani
# Date              : 2019.08.xx
# License           : MIT
#
# Description       : The following script runs "spark-app" through Spark Submit.
# Run Information   : This script needs to be executed manually.
# Standard Output   : Console Output
# Error Log         : Console Output
#
PROJECT_DIR="./"
JAR_DIR="target/scala-2.11"

INFO="\033[0;34mINFO\033[00m:"
ERROR="\033[0;31mERROR\033[00m:"


# =~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=
#  FUNCTIONS
# =~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=

is_installed() {
    if [ -z $(command -v $1) ]; then
      echo "${ERROR} '$1' not found, please check it first."
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
       |_|      APP v19.08.00
"

is_installed "java"
is_installed "spark-submit"
is_installed "sbt"

cd ${PROJECT_DIR}

echo "${INFO} Building project..."
sbt clean compile package

jar_file=$(ls ${JAR_DIR}/*.jar)
echo "${INFO} JAR File -> '${jar_file}'"

echo "${INFO} Starting Spark..."
spark-submit \
    --files log4j.properties \
    --driver-java-options "
        -Dlog4j.debug
        -Dlog4j.configuration=file:log4j.properties
    " \
    ${jar_file}

echo "${INFO} Process has been finished \\o/"
