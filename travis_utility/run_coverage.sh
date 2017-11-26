#!/bin/bash

if [ "$TRAVIS_REPO_SLUG" == "danielb987/EmojicodeEditor" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then

  echo -e "Run coverage"

  wget http://search.maven.org/remotecontent?filepath=org/jacoco/jacoco/0.7.9/jacoco-0.7.9.zip -O jacoco-0.7.9.zip 2> /dev/null

  unzip jacoco-0.7.9.zip > /dev/null

  ant compile

  echo -e "==========================="
  ls /home/travis/build/danielb987/EmojicodeEditor/target/classes
  find -iname "*.class"
  find -iname "*.jar"
  find -iname "EmojiPackageTest.*"
  echo -e "==========================="

  ant jacoco_rebuild

  mvn coveralls:report

fi
