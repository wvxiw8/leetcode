#!/bin/bash

# To be run in the leetcode repo root directory.

# The leetcode.jar artifact will appear in the leetcode repo root directory.

# Build java libs
JAVA_LIB_PATH="./lib/java"
if  [[ -d $JAVA_LIB_PATH ]]; then
    cd $JAVA_LIB_PATH
    rm -rf ./build
    mkdir ./build
    find . -name "*.java" > src_paths
    javac @src_paths -d ./build
    cd build
    jar cf leetcode.jar *
    cp leetcode.jar ../../../
    cd ..
    rm -rf build
    rm src_paths
fi