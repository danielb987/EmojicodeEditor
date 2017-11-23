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

  echo -e "${GH_TOKEN}\n"

  echo -e "aaa\n"
  cd docs
  echo -e "bbb\n"
  git rm -rf ./javadoc
  echo -e "ccc\n"
####  cp -Rf $HOME/javadoc-latest ./javadoc
  echo -e "ddd\n"
  git add -f .
  echo -e "eee\n"
  git commit -m "Latest javadoc on successful travis build $TRAVIS_BUILD_NUMBER auto-pushed to master"
  echo -e "fff\n"
  git push -fq origin master > /dev/null
  echo -e "ggg\n"

  ls
  echo -e "---------------------------------------------------------------\n"
  find

  cd $DIR

  pwd

fi

