<h1>Декомпозиция требований. Веб-приложение. Работа с ORM. (Красильников П. В.). :pencil2: :clipboard:</h1>
<h2>Перечень задач: :point_down:</h2>

  - [x] 1. Создать в папке с репозиторием файл: RoadMap8.md для описания плана для восьмого набора требований.

<hr>

  - [x] 2. Ознакомиться со следующей документацией:

    - [x] 2. 1. По работе с HIBERNATE по ссылке: **https://docs.jboss.org/hibernate/annotations/3.5/reference/en/html/entity.html#entity-overview**.

    - [x] 2. 2. По работе с GuicePersist по ссылке: **https://github.com/google/guice/wiki/GuicePersist**.

<hr>

  - [x] 3. Интегрировать в проект набор библиотек:

    - [x] 3. 1. **hibernate-core**

    - [x] 3. 2. **guice-persist**

<hr>

  - [x] 4. Сконфигурировать поведение HIBERNATE через файл: **persistence.xml** в пакете appServer, внутри папки META-INF: **https://docs.jboss.org/hibernate/orm/5.2/quickstart/html_single/**.

<hr>

  - [x] 5. Настроить работу сущностей из пакета dom на работу с ORM:

    - [x] 5. 1. Конфигурирование класса: **Accounting**:

        - [x] 5. 1. 1. К классу **Accounting** добавить аннотации *@Entity* и *@Table*(name="User_Seans")

        - [x] 5. 1. 2. Поля класса отметить аннотацией *@Column*

        - [x] 5. 1. 3. Поле первичного ключа отметить аннотацией *@Id*

        - [x] 5. 1. 4. Добавить поле **userResources** с типом данных **UserResources**, поле пометить аннотацией *@ManyToOne*

        - [x] 5. 1. 5. Добавить на поле **userResources** аннотацию @JoinColumn(name= "user_resource_id")

    - [x] 5. 2. Конфигурирование класса: **UserInfo**:

        - [x] 5. 2. 1. К классу **UserInfo** добавить аннотации *@Entity* и *@Table*(name="Users")

        - [x] 5. 2. 2. Поля класса отметить аннотацией *@Column*

        - [x] 5. 2. 3. Поле первичного ключа отметить аннотацией *@Id*

    - [x] 5. 3 Конфигурирование класса: **UserResources**.

        - [x] 5. 3. 1. К классу **UserResources** добавить аннотации *@Entity* и *@Table*(name="User_Resources").

        - [x] 5. 3. 2. Поля класса отметить аннотацией *@Column*

        - [x] 5. 3. 3. Поле первичного ключа отметить аннотацией *@Id*

        - [x] 5. 3. 4. Добавить поле **userInfo** с типом данных **UserInfo**, поле пометить аннотацией *@ManyToOne*

        - [x] 5. 3. 5. Добавить на поле **userInfo** аннотацию @JoinColumn(name= "user_id")

<hr>

  - [x] 6. Сконфигурировать работу **EntityManager** внутри проекта:

    - [x] 6. 1. Создать источник данных, который будет использовать свойства, описанные в конфиге *persistence.xml*.

    - [x] 6. 2. Создать класс: **EntityManagerProvider**, перенести в него код из класса **DataBaseConnectionProvider**.

    - [x] 6. 3. Настроить создание **EntityManager** через фабрику сущностей.

    - [x] 6. 4. В класс: **GuiceServletConfig** добавить привязку для **EntityManager**.

<hr>

  - [x] 7. Настроить работу классов пакета **DAO** на работу с БД через **EntityManager**.

     - [x] 7. 1. Добавить инъекцию **EntityManger** в DAO-классы поочерёдно в классы:

        - [x] 7. 1. 1. UserInfoDAO.

        - [x] 7. 1. 2. AccountingDAO.

        - [x] 7. 1. 3. UserResourceDAO.

     - [x] 7. 2. Настроить работу с БД с помощью **EntityManger** в методах DAO-классов:

        - [x] 7. 2. 1. AccountingDAO(getAllUserSeanses(), searchAccountingWithId(), searchAccountingWithUserResId(), addUserSeans()).

        - [x] 7. 2. 2. UserInfoDAO(getAllUsersInfo(), searchUserLogin(), searchUserInfoWhereId()).

        - [x] 7. 2. 3. UserResourceDAO(getAllAccessRightsForResources(), findIdRes(), searchAccessRightWhereUserResId(), searchAccessRightWhereUserId())..

<hr>

  - [x] 8. Адаптация миграций к PostgreSQL, перевести H2 в режим совместимости с PostgreSQL:

    - [x] 8. 1. Ознакомиться с докой по переведению в режим совместимости: **http://www.h2database.com/html/features.html#compatibility**

<hr>

  - [x] 9. Заюзать PostgreSQL предоставляемую сервисом Heroku: **https://www.heroku.com/postgres**.

<hr>

  - [x] 10. Заюзать пулл коннектов **c3p0**: **http://javacore.ru/topic/3-jdbc-spring-c3p0.htm**.

<hr>

№ | Время (мин) | Результат (мин) 
------------ | ------------- |------------  
1 | 2 | 2
2 | 80 | 180
3 | 5 | 5
4 | 40 | 50
5 | 600 | 1800
6 | 60 | 120
7 | 300 | 400
8 | 15 | 15
9 | 5 | 20
10| 30 | 30

