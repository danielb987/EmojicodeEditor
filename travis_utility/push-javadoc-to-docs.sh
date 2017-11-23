#!/bin/bash

# https://benlimmer.com/2013/12/26/automatically-publish-javadoc-to-gh-pages-with-travis-ci/

# if [ "$TRAVIS_REPO_SLUG" == "danielb987/EmojicodeEditor" ] && [ "$TRAVIS_JDK_VERSION" == "oraclejdk7" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then

if [ "$TRAVIS_REPO_SLUG" == "danielb987/EmojicodeEditor" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then

  echo -e "Generate javadoc...\n";

#  cp -R dist/javadoc $HOME/javadoc-latest

  cd $HOME
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "travis-ci"
  git clone --quiet --branch=master https://${GH_TOKEN}@github.com/danielb987/EmojicodeEditor docs > /dev/null

  git rm -rf ./docs/javadoc

  ant javadoc

  echo -e "Publishing javadoc...\n"

  # cd gh-pages
#  git rm -rf ./docs/javadoc
#  cp -Rf $HOME/javadoc-latest ./docs/javadoc
  git add -f .
  git commit -m "Latest javadoc on successful travis build $TRAVIS_BUILD_NUMBER auto-pushed to master"
  git push -fq origin master > /dev/null

  echo -e "Published Javadoc to gh-pages.\n"
  
fi
