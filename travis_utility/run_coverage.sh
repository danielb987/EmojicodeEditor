#!/bin/bash

if [ "$TRAVIS_REPO_SLUG" == "danielb987/EmojicodeEditor" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then

  echo -e "Run coverage"

  ant jacoco_rebuild

#  cd jacoco

#  pwd

#  mkdir target

#  ant clean
#  ant compile
#  ant test
#  ant report

fi
