# Менеджер задач (Java)

[![hexlet-check](https://github.com/Vetrash/java-project-99/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/Vetrash/java-project-99/actions)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=Vetrash_java-project-99&metric=coverage)](https://sonarcloud.io/summary/new_code?id=Vetrash_java-project-99)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=Vetrash_java-project-99&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=Vetrash_java-project-99)

Учебный проект Хекслета: приложение для управления задачами с REST API, аутентификацией и разграничением доступа. В проекте реализованы CRUD для задач, статусов, меток и пользователей, фильтрация и пагинация задач, а также JWT-аутентификация.
Учебный проект Хекслета: https://ru.hexlet.io/programs/java
Как это должно работать: https://files.hexlet.app/a/xg6yxv

DEMO: https://java-project-99-jubt.onrender.com
Для входа в демо-версию используйте:

- **Username:** `hexlet@example.com`
- **Password:** `qwerty`
## Стек

- Java 21
- Spring Boot (Web, Data JPA, Security, Validation)
- PostgreSQL
- Hibernate / JPA
- Liquibase (миграции БД)
- Lombok
- MapStruct
- OpenAPI / Swagger UI
- JUnit 5, MockMvc, AssertJ, JsonAssert
- Gradle
- Docker / Docker Compose
- Render (PaaS для демо)
- SonarCloud (анализ качества кода)


## Функциональность

- **Пользователи**: регистрация, аутентификация по JWT, CRUD, разграничение прав (обычный пользователь / администратор).
- **Задачи**: CRUD, фильтрация по статусу, исполнителю и меткам, поиск по подстроке названия, пагинация и сортировка.
- **Статусы задач**: CRUD, уникальные `name` и `slug`.
- **Метки**: CRUD, уникальное `name`, связь many-to-many с задачами.
- 
## Установка

### Требования

- JDK 21
- Docker и Docker Compose (для PostgreSQL)
- Gradle (или используйте `./gradlew`)

### Клонирование и зависимости

```bash
git clone https://github.com/Vetrash/java-project-99.git
cd java-project-99
```


