#!/bin/bash


# Java:
#  @Running     in the leetcode repo root directory.
#  @Artifact    leetcode.jar will appear in the leetcode repo root directory.
#  @Using       $ javac -cp leetcode.jar xxx_task.java && java -cp .:leetcode.jar Solution
build_java_lib()
{
    if ! command -v javac &> /dev/null; then
        echo ERROR: javac binary not found.
    elif ! command -v jar &> /dev/null; then
        echo ERROR: \'jar\' binary not found.
    else
        JAVA_LIB_PATH="./lib/java"
        JAR_NAME="leetcode.jar"
        if  [[ -d $JAVA_LIB_PATH ]]; then
            cd $JAVA_LIB_PATH
            rm -rf ./build
            mkdir ./build
            find . -name "*.java" > src_paths
            javac @src_paths -d ./build
            cd build
            jar cf $JAR_NAME *
            cp $JAR_NAME ../../../
            cd ..
            rm -rf build
            rm src_paths
            echo $JAR_NAME updated
        else 
            echo ERROR: $JAVA_LIB_PATH/ not found
        fi
    fi
}

build_java_lib