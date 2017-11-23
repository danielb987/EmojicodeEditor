#!/bin/bash

# https://benlimmer.com/2013/12/26/automatically-publish-javadoc-to-gh-pages-with-travis-ci/

# if [ "$TRAVIS_REPO_SLUG" == "danielb987/EmojicodeEditor" ] && [ "$TRAVIS_JDK_VERSION" == "oraclejdk7" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then

if [ "$TRAVIS_REPO_SLUG" == "danielb987/EmojicodeEditor" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then

  echo -e "Generate javadoc...\n";

#  cp -R dist/javadoc $HOME/javadoc-latest

  cd $HOME
  echo -e "AAA\n";
  git config --global user.email "travis@travis-ci.org"
  echo -e "BBB\n";
  git config --global user.name "travis-ci"
  echo -e "CCC\n";
  git clone --branch=master https://${GH_TOKEN}@github.com/danielb987/EmojicodeEditor
#  git clone --quiet --branch=master https://${GH_TOKEN}@github.com/danielb987/EmojicodeEditor
#  git clone --quiet --branch=master https://${GH_TOKEN}@github.com/danielb987/EmojicodeEditor master > /dev/null
  echo -e "DDD\n";

  ls

  git rm -rf ./docs/javadoc

#  ant javadoc

  echo -e "Publishing javadoc... AAAAAAAAAA\n"

  # cd gh-pages
#  git rm -rf ./docs/javadoc
#  cp -Rf $HOME/javadoc-latest ./docs/javadoc
  echo -e "EEE\n";
  git add -f .
  echo -e "FFF\n";
#  git commit -m "Latest javadoc on successful travis build $TRAVIS_BUILD_NUMBER auto-pushed to master"
  echo -e "GGG\n";
#  git push -fq origin master > /dev/null
  echo -e "HHH\n";

  echo -e "Published Javadoc to gh-pages.\n"
  
fi
