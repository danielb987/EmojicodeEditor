#!/bin/bash

export FINDBUGS_HOME=./findbugs/findbugs-3.0.1

# export CLASSPATH=$FINDBUGS_HOME/lib/*:$FINDBUGS_HOME/apache-ant-1.10.1/lib/*
# export CLASSPATH=$FINDBUGS_HOME/lib:./findbugs/apache-ant-1.10.1/lib
export ./findbugs/apache-ant-1.10.1/lib

echo -e ""
echo -e "==============================="
echo -e ""
printenv
echo -e ""
echo -e "==============================="
echo -e ""
env
echo -e ""
echo -e "==============================="
echo -e ""


echo -e "Generate Findbugs report"

# export FINDBUGS_HOME=./findbugs/findbugs-3.0.1

mkdir ./build/findbugs

# java -jar $FINDBUGS_HOME/lib/findbugs.jar -textui -html -output ./build/findbugs/findbugs.html -sourcepath ./src ./build/classes ./lib
# java -classpath "$FINDBUGS_HOME/lib/*:$FINDBUGS_HOME/apache-ant-1.10.1/lib/*" -jar $FINDBUGS_HOME/lib/findbugs.jar -textui -html -output ./build/findbugs/findbugs.html -sourcepath ./src ./build/classes ./lib
# java -classpath $FINDBUGS_HOME/lib/* -jar $FINDBUGS_HOME/lib/findbugs.jar -textui -html -output ./build/findbugs/findbugs.html -sourcepath ./src ./build/classes ./lib
java -jar $FINDBUGS_HOME/lib/findbugs.jar -textui -html -output ./build/findbugs/findbugs.html -sourcepath ./src ./build/classes ./lib

# java -jar $FINDBUGS_HOME/lib/findbugs.jar -textui -html -output ./build/findbugs/findbugs.html -sourcepath ./src -home $FINDBUGS_HOME ./build/classes

# java -classpath $FINDBUGS_HOME/lib -jar $FINDBUGS_HOME/lib/findbugs.jar -textui -html -output ./build/findbugs/findbugs.html -sourcepath ./src ./build/classes
