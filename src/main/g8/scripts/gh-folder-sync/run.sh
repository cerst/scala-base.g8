#!/usr/bin/env bash

BLACKLIST=""
DEBUG=false
INPUT_PATH=""
OUTPUT_DIR=""
REPO_URL=""
TMP_DIR=""
USAGE="Usage: gen.sh -b <absolute-path-to-blacklist>
                     -i <repo-relative-path-to-input-dir>
                     -o <absolute-path-to-output-dir>
                     -r <repo-url>
                     -t <absolute-path-to-tmp-dir>"

if [[ $# -eq 0 ]] ; then
    echo ${USAGE}
    exit 1
fi

while getopts ":ab:dhi:o:r:p:t:" opt; do
  case ${opt} in
    b)
      BLACKLIST=$OPTARG
      ;;
    d)
      DEBUG=true
      ;;
    h)
      echo ${USAGE}
      exit 0
      ;;
    i)
      INPUT_PATH=${OPTARG}
      ;;
    o)
      OUTPUT_DIR=${OPTARG}
      ;;
    r)
      REPO_URL=${OPTARG}
      ;;
    t)
      TMP_DIR=${OPTARG}
      ;;
    \?)
      echo "Unknown option: $OPTARG. ${USAGE}" >&2
      exit 1
      ;;
    :)
      echo "Option -$OPTARG requires an argument." >&2
      exit 1
      ;;
  esac
done

if [[ -z ${BLACKLIST} ]] ; then
    echo "-b is required (use -h to display usage)"
    exit 1
elif [[ -z ${INPUT_PATH} ]] ; then
    echo "-i is required (use -h to display usage)"
    exit 1
elif [[ -z ${OUTPUT_DIR} ]] ; then
    echo "-p is required (use -h to display usage)"
    exit 1
elif [[ -z ${REPO_URL} ]] ; then
    echo "-r is required (use -h to display usage)"
    exit 1
elif [[ -z ${TMP_DIR} ]] ; then
    echo "-t is required (use -h to display usage)"
    exit 1
fi

FROM=${TMP_DIR}/${INPUT_PATH}/*

if ${DEBUG} ; then
    echo "----- DEBUG-INFO -----"
    echo "Blacklist: $BLACKLIST"
    echo "Input path: $INPUT_PATH"
    echo "Input dir: $FROM"
    echo "Output dir: $OUTPUT_DIR"
    echo "Repo Url: $REPO_URL"
    echo "Tmp dir: $TMP_DIR"
    echo "----- DEBUG-INFO -----"
fi

rm -rf ${TMP_DIR}
git clone ${REPO_URL} ${TMP_DIR}

echo "Copying files"
rsync --exclude-from=${BLACKLIST} ${FROM} ${OUTPUT_DIR}

echo "Clean-up tmp dir"
rm -rf ${TMP_DIR}
