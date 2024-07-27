# Тестовое задание на CaseLab Java

ФИО: `Кокорин Александр Алексеевич`

* ### Описание решения:

Решение представляет из себя классическую трехуровневую архитектуру - Controller <=> Service <=> Domain: (папка manager)
- Controller отвечает за общение с пользователем, принимает и отпраляет запросы, а так же обрабатывает ошибки. 
- Domain отвечает за взаимодействие с базой данных. 
- Service же является всязывающим звеном, он выполняет всю основную логику приложения. В нашем случае - собирает и преобразовывает данные.
  
Так же в решении есть тесты, полностью проверяющие работу микросервиса.

Что же касается базы данных - для создания таблиц используется liquibase (папка migrations), а сама база разворачивавется в Docker при помощи compose.yml.

* ### Инструкция по запуску приложения:

Для запуска требуется запустить compose.yml (прежде должен быть запущен Docker), после это запустить класс Application. Всё работает и готово к использованию!

* ### Примеры тестовых запросов для проверки API-методов:

Тестировалось всё через Swagger по ссылке: http://localhost:8080/swagger-ui

`POST` /file/add

Request body:
{
"file": "SGVsbG8gV29ybGQh",
"title": "title",
"creation_date": "2024-07-27T15:19:10.239Z",
"description": "description"
}

Ответ:

200 OK

Response body:
{
"id": 1
}

---

`GET` /file/1

Ответ:

200 OK

Response body:
{
"file": "SGVsbG8gV29ybGQh",
"title": "title",
"creation_date": "2024-07-27T15:19:10.239Z",
"description": "description"
}

---

`GET` /files

Ответ:

200 OK

{
"count": 1,
"files": [
{
"file": "SGVsbG8gV29ybGQh",
"title": "title",
"creation_date": "2024-07-27T15:19:10.239Z",
"description": "description"
}
]
}
