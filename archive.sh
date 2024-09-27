#!/bin/bash

rm -rf build/config
rm -f build/config.zip

mkdir -p build/config
cp -r ./socotra-config/* ./build/config/
mkdir -p ./build/config/plugins/java
cp -r ./src/main/java/com/socotra/deployment/customer/* ./build/config/plugins/java
cd build/config; zip -r ../config.zip *