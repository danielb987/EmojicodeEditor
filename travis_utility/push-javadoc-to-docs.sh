#!/bin/bash

if [ "$TRAVIS_REPO_SLUG" == "danielb987/EmojicodeEditor" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then

  echo -e "Run javadoc"

  echo -e "ls"
  ls

  echo -e "ls dist"
  ls dist

  # Create jar file and javadoc
  ant jar

#  ant javadoc

  echo -e "Publishing javadoc...\n"

  export DIR=$(pwd)

  cd $HOME
  mkdir temp
  cd temp

  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "travis-ci"
  git clone --quiet --branch=gh-pages https://${GH_TOKEN}@github.com/danielb987/EmojicodeEditor > /dev/null

  cd EmojicodeEditor

  rm -Rf distribution
  mkdir distribution
  cp $DIR/dist/*.jar distribution/

  rm -Rf javadoc
  cp -R $DIR/dist/javadoc .

  git add -f .
  git commit -m "Latest javadoc on successful travis build $TRAVIS_BUILD_NUMBER auto-pushed to gh-pages"
  git push -fq origin gh-pages > /dev/null

fi
