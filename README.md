# box
Тестовая программа для Java Spring

## Установка
Для работы программы нужна BD PostgresSQL 10+ (запускал на 12). 
Название БД, логин и пароль в ``application.properties``

## Принцип работы
1. Сразу после запуска старается получить файл, переданный как параметр.
2. Проводит инициализацию БД - удаляет старые таблицы, создает новые, наполняет данными.
3. Переход в рабочий режим, ожидая REST запроса.
Логирование проводится в консоль.

## Запуск программы:
1. С локальным файлом ``mvn spring-boot:run -Dspring-boot.run.arguments=--filepath=file:/home/prostakov/project/java/alpha/1.xml``
2. С файлом из инета ``mvn spring-boot:run -Dspring-boot.run.arguments=--filepath=url:https://hand-ceramic.club/1.xml``
3. С файлом из classpath ``mvn spring-boot:run -Dspring-boot.run.arguments=--filepath=classpath:1.xml``, 
только не забудьте это файл в classpath положить.

## REST

Запрос:
    
    POST http://127.0.0.1:8080/test
    Connection: keep-alive
    Content-Type: application/json; charset=utf-8
    Content-Length: 34
    Host: 127.0.0.1:8080
    User-Agent: Apache-HttpClient/4.5.7 (Java/11.0.9.1)
       
    POST data:
    {
    	"id": 1,
    	"color": "red"
    }
    
Ответ:
    
    HTTP/1.1 200 
    Content-Type: application/json
    Transfer-Encoding: chunked
    Date: Wed, 23 Dec 2020 15:17:07 GMT
    Keep-Alive: timeout=60
    Connection: keep-alive
    
    [
        2,
        3
    ]

Есть проверка входных данных, может вернуть сообщение об ошибке:

    {
        "errorCode": 400,
        "errorDetail": "Bad request format: ID must be not null"
    }
