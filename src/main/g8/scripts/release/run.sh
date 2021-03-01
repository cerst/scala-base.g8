#!/usr/bin/env bash

# ======================================================================================================================
# PREAMBLE
# ======================================================================================================================
set -euo pipefail
IFS=$'\n\t'

function fail() {
  local path=""
  # local length="${#FUNCNAME}"
  for i in "${!FUNCNAME[@]}"; do
    # omit index 0 is the function 'fail' itself
    # add '&& "$i" -lt "$length-1"' if you want to omit the 'main' function + line number
    if [[ "$i" != "0" ]]; then
      local to_add="${FUNCNAME[$i]}:${BASH_LINENO[$i - 1]}"
      if [[ "$path" == "" ]]; then
        local path="$to_add"
      else
        local path="$to_add > $path"
      fi
    fi
  done
  echo >&2 "'$path' failed: $1"
  exit 1
}

# ======================================================================================================================
# SCRIPT
# ======================================================================================================================
export RELEASE=true

# greadlink because readlink has no -f on Mac
script_dir_abs_path="$(dirname $(greadlink -f "$0"))"

project_root_abs_path="${script_dir_abs_path}/../.."

pre_release_tasks="\
$(cat "${script_dir_abs_path}/pre_release_tasks")
;versionToFile"

release_tasks=$(cat "${script_dir_abs_path}"/release_tasks)

echo "\
=========================
Run sbt PRE-release tasks
${pre_release_tasks}
========================="
(cd "$project_root_abs_path" && sbt --warn "$pre_release_tasks")

# pipe sbt output to /dev/null as it otherwise interferes with the variable assignment using cat
project_version=$(cat "${project_root_abs_path}/target/version-to-file/version")
echo ""
echo "Do you want to publish v$project_version (y/n)?"
while true; do
  read -r yn
  case ${yn} in
  [Yy]*) break ;;
  [Nn]*)
    echo "Aborted"
    exit 0
    ;;
  *) echo "Please answer yes or no." ;;
  esac
done

echo "\
===========================
Ensure Git branch is master
==========================="
git_branch=$(git symbolic-ref --short HEAD)
if [[ "$git_branch" != "master" ]]; then
  fail "You must be on Git branch 'master' to publish"
else
  echo "Ok"
fi

# super shell messes-up password queries in the terminal
echo "\
=====================
Run sbt release tasks
${release_tasks}
====================="
(cd "$project_root_abs_path" && sbt --warn --supershell=false "$release_tasks")

echo "\
=================
Release succeeded
================="
