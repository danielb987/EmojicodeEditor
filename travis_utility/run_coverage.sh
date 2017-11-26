#!/bin/bash

if [ "$TRAVIS_REPO_SLUG" == "danielb987/EmojicodeEditor" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then

  echo -e "Run coverage"

  wget http://search.maven.org/remotecontent?filepath=org/jacoco/jacoco/0.7.9/jacoco-0.7.9.zip -O jacoco-0.7.9.zip

  echo -e "------------------"
  ls
  echo -e "------------------"
  unzip jacoco-0.7.9.zip
  echo -e "------------------"
  ls
  echo -e "------------------"
  find
  echo -e "------------------"

  ant jacoco_rebuild

#  cd jacoco

#  pwd

#  mkdir target

#  ant clean
#  ant compile
#  ant test
#  ant report

fi
