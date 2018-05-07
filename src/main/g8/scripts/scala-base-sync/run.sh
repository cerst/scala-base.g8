#!/usr/bin/env bash

SCRIPT_FOLDER_PATH="$( cd "$(dirname "$0")" ; pwd -P )"

INCLUDE_FILES="${SCRIPT_FOLDER_PATH}/include-files"
OUTPUT_DIR="${SCRIPT_FOLDER_PATH}/../.."
REPO_URL='https://github.com/cerst/scala-base.g8'
TMP_DIR="${SCRIPT_FOLDER_PATH}/tmp"

echo "----- DEBUG-INFO -----"
echo "Include files: $INCLUDE_FILES"
echo "Output dir: $OUTPUT_DIR"
echo "Repo Url: $REPO_URL"
echo "Tmp dir: $TMP_DIR"
echo "----- DEBUG-INFO -----"

rm -rf ${TMP_DIR}
git clone ${REPO_URL} ${TMP_DIR}

echo "Copying files"
rsync --files-from=${INCLUDE_FILES} ${TMP_DIR} ${OUTPUT_DIR}

echo "Clean-up tmp dir"
rm -rf ${TMP_DIR}
