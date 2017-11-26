#!/bin/bash

if [ "$TRAVIS_REPO_SLUG" == "danielb987/EmojicodeEditor" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then

  echo -e "Run coverage"

  wget http://search.maven.org/remotecontent?filepath=org/jacoco/jacoco/0.7.9/jacoco-0.7.9.zip -O jacoco-0.7.9.zip > /dev/null

  unzip jacoco-0.7.9.zip > /dev/null

  echo -e "==========================="
  ls /home/travis/build/danielb987/EmojicodeEditor/target/classes 
  echo -e "==========================="

  ant jacoco_rebuild

fi
