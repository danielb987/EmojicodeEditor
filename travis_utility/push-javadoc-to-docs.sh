#!/bin/bash

# Don't create javadoc and EmojicodeEditor.jar if the repository is a fork, or if it is a pull request or if the branch is not master

if [ "$TRAVIS_REPO_SLUG" == "danielb987/EmojicodeEditor" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then

  echo -e "Create jar file and run javadoc"

  # Create jar file
  ant jar

  # Create javadoc
  ant javadoc

  # Check if this project's javadoc follows coding standard
  ant checkstyle

  echo -e "Publishing javadoc...\n"

  export DIR=$(pwd)

  # Create a temp directory
  cd $HOME
  mkdir temp
  cd temp

  # Clone the gh-pages branch of the git repository
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "travis-ci"
  git clone --quiet --branch=gh-pages https://${GH_TOKEN}@github.com/danielb987/EmojicodeEditor > /dev/null

  # Change directory to the repository
  cd EmojicodeEditor

  # Remove the old distribution folder, create a new one, and
  # copy the EmojicodeEditor.jar file to the distribution folder.
  rm -Rf distribution
  mkdir distribution
  cp $DIR/dist/*.jar distribution/

  # Remove the javadoc folder and copy the generated javadoc folder to current folder
  rm -Rf javadoc
  cp -R $DIR/dist/javadoc .

  # Remove the checkstyle folder and copy the checkstyle report to the checkstyle folder
  rm -Rf checkstyle
  mkdir checkstyle
  cp $DIR/build/checkstyle_errors.xml checkstyle/

  # Upload the distribution and javadoc to the gh-pages branch at GitHub
  git add -f .
  git commit -m "Latest javadoc on successful travis build $TRAVIS_BUILD_NUMBER auto-pushed to gh-pages"
  git push -fq origin gh-pages > /dev/null

fi
