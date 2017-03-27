#!/bin/bash
read -n 1 -p "Запустить скрипт для построения билда? (y/n): " AMSURE
[ "$AMSURE" = "y" ] || exit
echo "" 1>&2
# Создать папку: out
mkdir -p out

# Хранить файлы в отдельной папке: out, не мешать с исходниками
javac -encoding utf8 -d ./out/ -sourcepath src/ ./src/Main.java -classpath "./libs/commons-codec-1.10.jar;./libs/commons-lang3-3.5.jar;./libs/commons-cli-1.3.1.jar"

#Упаковать файлы в jar-архив
jar cvmf META-INF/MANIFEST.MF GoodLineCLI.jar out/