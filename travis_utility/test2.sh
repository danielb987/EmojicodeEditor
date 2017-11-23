#!/bin/bash

if [ "$TRAVIS_REPO_SLUG" == "danielb987/EmojicodeEditor" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then

  echo -e "Publishing javadoc...\n"

  pwd

  ls

  find
  
  pwd

  export DIR=$(pwd)

  cd $HOME

  pwd

  cd $DIR

  pwd

fi

