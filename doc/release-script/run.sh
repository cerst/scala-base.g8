#!/usr/bin/env bash

# ======================================================================================================================
# PREAMBLE
# ======================================================================================================================
set -euo pipefail
IFS=$'\n\t'

# ======================================================================================================================
# VARIABLES
# ======================================================================================================================
pre_release_tasks="\
;clean
;compile
;test
;doc"

release_tasks="\
;publishLocal"

script_dir_abs_path="$(dirname $(readlink -f "$0"))"

project_root_abs_path="${script_dir_abs_path}/../.."

# ======================================================================================================================
# RUN PRE_RELEASE TASKS
# ======================================================================================================================
echo "\
> Run sbt PRE-release tasks
${pre_release_tasks}
"
(cd "$project_root_abs_path" && sbt --warn "$pre_release_tasks")

# ======================================================================================================================
# DETERMINE & CONFIRM VERSION
# ======================================================================================================================
# pipe sbt output to /dev/null as it otherwise interferes with the variable assignment using cat
project_version_path="${project_root_abs_path}/target/printed_version"
project_version=$(
  rm -f "$project_version_path" \
  && sbt --error "print core/version" > "$project_version_path" \
  && cat "$project_version_path"
)
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

# ======================================================================================================================
# ENSURE MASTER BRANCH
# ======================================================================================================================
echo "> Ensure Git branch is master"
git_branch=$(git symbolic-ref --short HEAD)
if [[ "$git_branch" != "master" ]]; then
  echo "FAILED: You must be on Git branch 'master' to publish"
  exit 1
fi

# ======================================================================================================================
# RUN RELEASE TASKS
# ======================================================================================================================
# super shell messes-up password queries in the terminal
echo "\
> Run sbt release tasks:
${release_tasks}"
(cd "$project_root_abs_path" && sbt --warn --supershell=false "$release_tasks")

# ======================================================================================================================
# DONE
# ======================================================================================================================
echo "> Release succeeded"
