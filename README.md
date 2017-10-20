<h1> "GoodLineCLI" - это консольное приложение, процесс разработки которого ведётся в курсе: "Программная инженерия" на кафедре GoodLine 
</h1>

[GoodLine](http://kafedra-goodline.info/) - это кафедра, организованная на основе [КузГТУ](https://www.kuzstu.ru/)

[Сайт проекта на GitHub Pages](https://pahahentikys.github.io/GoodLineCLI/)

<h2>Ход работы:</h2>

Работа выполняется с последовательным выполнений группы наборов требований:

  1. [Набор требований для первого задания](https://github.com/Pahahentikys/GoodLineCLI/blob/master/RoadMap.md)
  
  2. [Набор требований для второго задания](https://github.com/Pahahentikys/GoodLineCLI/blob/master/RoadMap2.md)
  
  3. [Набор требований для третьего задания](https://github.com/Pahahentikys/GoodLineCLI/blob/master/RoadMap3.md)

  4. [Набор требований для четвёртого задания](https://github.com/Pahahentikys/GoodLineCLI/blob/master/RoadMap4.md)

<h2>Набор инструкций по сборке проекта: </h2>

Чтобы собрать проект в файл с расширением **jar**, требуется произвести запуск файла: **BUILD.sh** при помощи специальной команды: **./BUILD.sh**, которая должна вводится из коммандной строки. Реузльтатом работы команды будет файл: **GoodLineCLI.jar**.

<h2>Набор инструкция по запуску проекта: </h2>

Чтобы запустить проект, требуется запустить файл: **RUN.sh** и передать на вход, как параметры, набор определённых данных и комманд. Например, это может быть сделано вот так (на примере указан процесс **аутентификации, авторизации, аккаунтинга**):
```sh
./RUN.sh -login jdoe -pass sup3rpaZZ -role READ -res a.b -ds 2015-01-01 -de 2015-12-31 -vol 100
```

<h2>Набор инструкций по тестированию проекта:  </h2> 

Чтобы выполнить тестирование проекта, требуется запустить файл: **TEST.sh** при помощи специальной команды: **./TEST.sh**, которая должна быть введена из командной строки. Результатов выполнения тестирования может быть два: **код-0**, если тестирование прошло успешно и **код-1**, если хотя бы один тест приложения провален. 

**Примечание**: тестирование проекта производится при помощи бесплатного сервиса: [Travis CI](https://travis-ci.org/) [![Build Status](https://travis-ci.org/Pahahentikys/GoodLineCLI.svg?branch=master)](https://travis-ci.org/Pahahentikys/GoodLineCLI) , который  интегрируется с **GitHub**.


