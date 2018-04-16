#!/usr/bin/env bash

# https://stackoverflow.com/questions/4774054/reliable-way-for-a-bash-script-to-get-the-full-path-to-itself
SCRIPT_FOLDER_PATH="$( cd "$(dirname "$0")" ; pwd -P )"

sh ${SCRIPT_FOLDER_PATH}/../gh-folder-sync/run.sh \
  -b ${SCRIPT_FOLDER_PATH}/blacklist \
  -d \
  -i src/main/g8/project \
  -r https://github.com/cerst/scala-base.g8 \
  -o ${SCRIPT_FOLDER_PATH}/../../project \
  -t ${SCRIPT_FOLDER_PATH}/tmp