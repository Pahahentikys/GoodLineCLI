#!/bin/bash
# Создать папку: out
mkdir -p out
# Хранить файлы в отдельной папке: out, не мешать с исходниками
cp -R -v ./src/main/resources/* ./out/
javac -classpath "./libs/commons-codec-1.10.jar:./libs/commons-lang3-3.5.jar:./libs/commons-cli-1.3.1.jar:./libs/h2-1.4.194.jar:./libs/flyway-core-4.1.2.jar:./libs/log4j-core-2.8.2.jar:./libs/log4j-api-2.8.2.jar" -encoding utf8 -d ./out/ -sourcepath src/main/java/ ./src/main/java/general/Main.java
#Упаковать файлы в jar-архив
jar cvmf ./META-INF/MANIFEST.MF GoodLineCLI.jar out/ libs/