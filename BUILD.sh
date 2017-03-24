#!/bin/bash

# Создать папку: bin
mkdir bin

# Хранить файлы в отдельной папке: bin, не мешать с исходниками
javac -d bin src/Main.java -cp "libs/*.jar"

#Упаковать файлы в jar-архив
jar cfm GoodLineCLI.jar META-INF/MANIFEST.MF bin/