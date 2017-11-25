#!/bin/bash

if [ "$TRAVIS_REPO_SLUG" == "danielb987/EmojicodeEditor" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then

  echo -e "AAAAAAAAAA"
  echo -e "Run javadoc"

#  ant javadoc

  mkdir dist
  mkdir dist/javadoc
  echo "Test Javadoc XYZ" > dist/javadoc/index.html

  echo -e "Show file contents"
  cat dist/javadoc/index.html

  echo -e "Publishing javadoc...\n"

  pwd

  export DIR=$(pwd)

  cd $HOME

  pwd

  mkdir temp
  mkdir temp/EmojiCode
  cd temp/EmojiCode

  pwd

  echo -e "AAA"
  git config --global user.email "travis@travis-ci.org"
  echo -e "BBB"
  git config --global user.name "travis-ci"
  echo -e "CCC"
#  git clone --branch=master https://${GH_TOKEN}@github.com/danielb987/EmojicodeEditor docs
  echo -e "DDD"
  git clone --quiet --branch=master https://${GH_TOKEN}@github.com/danielb987/EmojicodeEditor docsAAA > /dev/null
  echo -e "EEE"

  echo -e "==========================================================="  
  echo -e "==========================================================="  
  echo -e "==========================================================="  
  echo -e "==========================================================="  
  echo -e "==========================================================="  
  echo -e "==========================================================="  

  pwd
  echo -e "List files"
  echo -e "-----------------------------------------------------------"

  find

  if false ; then

  echo -e "Test AAA\n"

  cp -R build/docs/javadoc $HOME/javadoc-latest

  cd $HOME
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "travis-ci"
  git clone --branch=master https://${GH_TOKEN}@github.com/danielb987/EmojicodeEditor docs
  git clone --quiet --branch=master https://${GH_TOKEN}@github.com/danielb987/EmojicodeEditor docs > /dev/null

  cd docs
  git rm -rf ./javadoc
  cp -Rf $HOME/javadoc-latest ./javadoc
  git add -f .
  git commit -m "Latest javadoc on successful travis build $TRAVIS_BUILD_NUMBER auto-pushed to master"
  git push -fq origin master > /dev/null

  echo -e "Published Javadoc to gh-pages.\n"

  fi
  
  echo -e "Test BBB\n"

fi

