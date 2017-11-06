<h1>Декомпозиция требований. Веб-приложение. Интеграция в проект библиотеки - Guice. (Красильников П. В.). :pencil2: :clipboard:</h1>
<h2>Перечень задач: :point_down:</h2>

  - [x] 1. Создать в папке с репозиторием файл: RoadMap6.md для описания плана для шестого набора требований.

<hr>

  - [x] 2. Ознакомиться с документацией по инжекту зависимостей через Guice.

<hr>

  - [x] 3. Добавить необходимые зависимости для работы Guice в пакет **appServer**:

    - [x] 3. 1. Добавить зависимость: **guice**.

    - [x] 3. 2. Добавить зависимость: **guice-servlet**.

<hr>

  - [ ] 5. Добавить необходимые классы для работы сервлетов:

    - [ ] 5. 1. Добавить класс: **UserServlet**;

    - [ ] 5. 2. Добавить класс: **AuthorityServlet**;

    - [ ] 5. 3. Добавить класс: **ActivityServlet**.

<hr>

  - [ ] 5. Переопределить в классах сервлетов метод: **service (req resp)**.

<hr>

  - [ ] 6. Сконфигурировать работу путей через Guice:

    - [ ] 6. 1. Удалить из файла: **web.xml** информацию о зарегестрированных ранее сервлетах **get** и **post**;

    - [ ] 6. 2. Добавить в файл: **web.xml** необходимые аттрибуты: filter и filter-mapping для корректной работы путей Guice;

    - [ ] 6. 3. Создать класс **GuiceServletConfig**, через который будут конфигурироваться пути к сервлетам:

        - [ ] 6. 3. 1. Сервлет **UserServlet** будет работать с пользователем и доступен по пути: **/ajax/user**;

        - [ ] 6. 3. 2. Сервлет **AuthorityServlet** будет работать с авторизацией и доступен по пути: **/ajax/authority**;

        - [ ] 6. 3. 3. Сервлет **ActivityServlet** будет работать с активностью юзера и доступен по пути: **/ajax/activity**.

<hr>

  - [ ] 7. Добавить инжект логгеров в пакет **appServer**:

    - [x] 7. 1. Ознакомиться с документацией по инжекту логгеров: https://github.com/google/guice/wiki/CustomInjections

    - [ ] 7. 2. Создать аннотацию под логгер: **@InjectLogger Logger logger**;

    - [ ] 7. 3. Создать: class **Log4JTypeListener** implements **TypeListener**;

    - [ ] 7. 4. Создать:  class **Log4JMembersInjector<T>** implements **MembersInjector<T>**;

    - [ ] 7. 5. Добавить bind **Log4JTypeListener** через метод **configureLogger()** в классе: **GuiceServletConfig**.

<hr>

  - [x] 8. Ознакомиться с документацией по библиотеке **Lombock**.

    - [ ] 8. 1. Интегрировать в IDEA плагин для работы с **Lombock**;

    - [ ] 8. 2. Заменить get и set операции на аннотации при помощи **Lombock**;

<hr>