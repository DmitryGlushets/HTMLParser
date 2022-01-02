# HtmlParser

---
### О приложении

Данное приложение разработано в Intellij IDEA 2021.2.3 (Ultimate Edition) на языке Java 8
(сборка пректа Maven).    
Были использованы следующие технологии:
* Spring Framework *(Spring Boot, Spring Data, Spring WEB, Spring MVC)*.
* Hibernate, PostgreSQL.
* Thymeleaf.
* Bootstrap.

---
### 

* Java 8+.
* PostgreSQL-14.1-windows-x64..
* Intellij IDEA 2021.2.3 Ultimate Edition.

---
### Порядок запуска приложения

* В PostgreSQL создать пользователя с именем `user` с паролем `123`.
* Создать базу данных `statistics_db`. SQL скрипт:
  >CREATE DATABASE statistics_db
  WITH
  OWNER = "user"
  ENCODING = 'UTF8'
  LC_COLLATE = 'Russian_Russia.1251'
  LC_CTYPE = 'Russian_Russia.1251'
  TABLESPACE = pg_default
  CONNECTION LIMIT = -1;
* Таблицы `table_dictionary` и `table_statistics` создаються с помошью Hibernate.
* Загрузить проект в Intellij IDEA из папки `HTMLParser`.
* Скомпилируйте и запустите приложение из *Application class*.
* Вы можете изменить настройки доступа к базе данных в файла *application.yml*:
  >spring:  
  datasource:  
  url: jdbc:postgresql://localhost:5432/statistics_db  
  username: user
  password: 123  
  driver-class-name: org.postgresql.Driver
* Логирование производится в файл `log_file.log` в корень проекта. Вы можете изменить эту настройку в файле *log4j.properties*:
  >log4j.appender.file.File=log_file.log
---
### Автор
Автор: Глушец Дмитрий
dglushets@gmail.com
+7-917-060-44-52
