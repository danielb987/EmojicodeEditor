#!/bin/bash

if [ "$TRAVIS_REPO_SLUG" == "danielb987/EmojicodeEditor" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then

  echo -e "Run javadoc"

  ant javadoc

  echo -e "Publishing javadoc...\n"

  cp -R dist/javadoc docs/
#  cp -R build/docs/javadoc $HOME/javadoc-latest

#  cd $HOME
#  git config --global user.email "travis@travis-ci.org"
#  git config --global user.name "travis-ci"
#  git clone --branch=master https://${GH_TOKEN}@github.com/danielb987/EmojicodeEditor docs
#  git clone --quiet --branch=master https://${GH_TOKEN}@github.com/danielb987/EmojicodeEditor docs > /dev/null

#  cd docs
#  git rm -rf ./javadoc
#  cp -Rf $HOME/javadoc-latest ./javadoc
  echo -e "qqq\n"
  git add -f .
  echo -e "AAA\n"
  git commit -m "Latest javadoc on successful travis build $TRAVIS_BUILD_NUMBER auto-pushed to master"
  echo -e "BBB\n"
  git push -fq origin master > /dev/null
  echo -e "CCC\n"

  echo -e "Published Javadoc to gh-pages.\n"
  
fi

