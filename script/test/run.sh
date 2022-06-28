#!/usr/bin/env bash

# ======================================================================================================================
# PREAMBLE
# ======================================================================================================================
set -euo pipefail
IFS=$'\n\t'

script_dir_abs_path="$(dirname $(readlink -f "$0"))"

project_base_dir="$(readlink -f "${script_dir_abs_path}"/../..)"

tmp_dir="${script_dir_abs_path}/tmp"

test_project_code_dir="${tmp_dir}/unnamed-project/code"

# ======================================================================================================================
# DELETE PRIOR TMP DIR
# ======================================================================================================================
rm -rf "$tmp_dir"

# ======================================================================================================================
# CREATE TEST PROJECT
# ======================================================================================================================
echo "> Create test project"
mkdir "$tmp_dir"
(cd "$tmp_dir" && sbt --error new "file://$project_base_dir")

# ======================================================================================================================
# ENSURE NO PLUGIN UPDATES
# ======================================================================================================================
echo "> Ensure no plugin updates"
expected_dependency_updates_output="\
Found 1 dependency update for project
  org.scala-lang:scala-library:provided : 2.12.16 -> 2.13.8"

(cd "$test_project_code_dir" && sbt --error "reload plugins;dependencyUpdatesReport;reload return")
dependency_updates_output="$(cat "$test_project_code_dir/project/target/dependency-updates.txt")"

if [ "$dependency_updates_output" != "$expected_dependency_updates_output" ]; then
  printf "FAIL: Found dependency updates for build. Got:\n%s" "$dependency_updates_output"
  exit 1
fi

# ======================================================================================================================
# TEST CREATED PROJECT
# ======================================================================================================================
echo "> Test created project"
(cd "$test_project_code_dir" && sbt --error "clean;compile;test;doc;core/run")

# ======================================================================================================================
# SUCCESS
# ======================================================================================================================
echo "> Success"
