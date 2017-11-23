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

  mkdir temp

  cd temp

  pwd

  export TEMP_DIR=$(pwd)

  echo -e "---------------------------------------------------------------\n"
  echo -e "---------------------------------------------------------------\n"
  echo -e "---------------------------------------------------------------\n"
  git config --global user.email "travis@travis-ci.org"
  echo -e "---------------------------------------------------------------\n"
  git config --global user.name "travis-ci"
  echo -e "---------------------------------------------------------------\n"
  git clone --branch=master https://${GH_TOKEN}@github.com/danielb987/EmojicodeEditor docs
  echo -e "---------------------------------------------------------------\n"
  echo -e "---------------------------------------------------------------\n"
  echo -e "---------------------------------------------------------------\n"

  ls
  echo -e "---------------------------------------------------------------\n"
  find

  cd $DIR

  pwd

fi

