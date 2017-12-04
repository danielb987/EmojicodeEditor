#!/bin/bash

echo -e "Generate Findbugs report"

export FINDBUGS_HOME=./findbugs/findbugs-3.0.1

mkdir ./build/findbugs

java -classpath $FINDBUGS_HOME/lib -jar $FINDBUGS_HOME/lib/findbugs.jar -textui -html -output ./build/findbugs/findbugs.html -sourcepath ./src ./build/classes

