<h1>Декомпозиция требований. Задание по созданию базы данных, реализации логирования и механизма миграций (Красильников П. В.). :pencil2: :clipboard:</h1>
<h2>Перечень задач: :point_down:</h2>

  - [x] 1. Создать в папке с репозиторием файл: RoadMap3.md для описания плана для третьего набора требований.

<hr>

  - [x] 2. Произвести проектирование базы данных, к ходе которой выявить необходимый набор таблиц и их атрибуты:

    - [x] 2. 1. Реализовать таблицу: **Users** с пользователями и набором следующих атрибутов:
    
      - user_id;
      
      - user_name;
      
      - user_login;
      
      - user_pass_hash;
      
      - user_salt;
      
      - user_resource_id **(внешний ключ из User_Resources в Users)**.

    - [x] 2. 2. Реализовать таблицу: **User_Resources** с ресурсами и набором следующих атрибутов:
    
      - user_resource_id;
     
      - user_resource_path;

      - user_resource_role;

      - uuser_seans_id **(внешний ключ из User_Seans в User_Resources)**.

    - [x] 2. 3. Релизовать таблицу: **User_Seans** с пользовательскими сеансами и набором следующих атрибутов:
    
      - user_seans_id;
     
      - user_seans_date_start;
     
      - user_seans_date_end;
     
      - user_seans_volume;

<hr>
  
  - [x] 3. Произвести работу с СУБД при помощи библиотеки: **h2**:

    - [x] 3. 1. Скачать библиотеку;

    - [x] 3. 2. Подключить библиотеку в проект;

    - [x] 3. 3. Создать базу данных: GooLineCLI.db:
    
      - [x] 3. 3. 1. Провести проверку подключения к базе;

      - [x] 3. 3. 2. Создать тестовую таблицу;
      
      - [x] 3. 3. 3. Написать скрипт на языке SQL для формирования тестовых данных;
      
      - [x] 3. 3. 4. Провести набор тестовых операций с тестовыми данными по: добавлению, обнавлению, удалению (CRUD);

<hr>

  - [x] 4. Произвести работу с миграциями при помощи библиотеки: **flyway**:

      - [x] 4. 1. Скачать библиотеку;
      
      - [x] 4. 2. Подключить библиотеку к проекту;
      
      - [x] 4. 3. Ознакомится с понятием миграций, местом их хранения, формы хранения;
      
      - [x] 4. 4. Написать скрипт на языке SQL, который создаёт структуру таблиц, описанную в п. 2;
      
      - [x] 4. 5. Выполнить миграцию.

<hr>
  
  - [x] 5. Разобраться с построением доступа к данным через **DAO**:
  
      - [x] 5. 1. Создать класс: **DataContextDAO** для описания источника данных;
      
      - [x] 5. 2. Реализовать механизм поиска в классе: **UserInfoDAO**, который ищет пользователя по логину;
      
      - [x] 5. 3. Реализовать механизм поиска в классе: **UserResourceDAO** ресурса, доступ к которому, есть у пользователя;

      - [x] 5. 4. Реализовать механизм поиска в классе: **AccountingDAO** пользовательского сеанса по userSeansId;

<hr>
  
  - [x] 6. Произвести работу с логированием при помощи библиотеки: **log4**:

      - [x] 6. 1. Скачать библиотеку;
      
      - [x] 6. 2. Подключить библиотеку к проекту;
      
      - [x] 6. 3. Прочитать про то, как её сконфигурировать на текущем проекте;
      
      - [x] 6. 4. Скачать в нужное место файл с конфигурацией;
      
      - [x] 6. 5. Получить логер;
      
      - [x] 6. 6. Залогировать сообщения с разного уровня в файл GoodLineCLI.log;
      
      - [x] 6. 7. Настроить уровень логирования через конфиг дебага;
      
      - [x] 6. 8. Переписать методы sout на механизм логирования;
      
      




