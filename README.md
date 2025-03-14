<h1 style="text-align: center">  Проект книжного интернет-магазина</h1>

## 0. Вступление

Проект написан мной с использованием фреймворка Spring и микросервисной архитектуры.

В проекте реализована маршрутизация между микросервисами с помощью Spring Gateway и
авторизация через Spring Oauth2 Server с выдачей токена и проверкой его 
на микросервисах для доступа к административному функционалу. 

Использован шаблонизатор Thymeleaf для отображения и генерации html-страниц.

Также использовал библиотеку Mapstruct для создания мапперов для 
преобразования сущностей БД в DTO.

Использовал обьектно-реляционную СУБД PostgresSQL как наиболее часто используемую в 
Java приложениях. 

В проекте используется глобальный перехватчик исключений GlobalExceptionHandler, 
который перехватывает необработанные исключения и будет обрабатывает их в соответствии с 
логикой или пробрасывает на фронт используя ResponseEntity класс.

Для логгирования использовал встроенный в Spring Slf4j с сохранением журналов в папку logs.

Стек технологий:
* Java 17
* Spring Boot 3.4.3, Spring JDBC, Spring Web
* Spring Security, Spring Oauth2 Server, Spring Oauth2 Resource Server, Spring Cloud Gateway
* ThymeLeaf
* MapStruct
* HTML

## Структура проекта:

![projSchema](/projStruct.svg)

## 1. web_service

Сервис для взаимодействия пользователя (покупателя) с магазином.
Позволяет посмотреть доступные книги, провести поиск и провести покупку. 
На данном этапе сервис не закончен, все html страницы созданы для демонстрации бекенда.
В частности, не реализован функционал совершения покупки, создания заказа и оплаты.
Сервис не предусматривает авторизации, после заполнения деталей заказа происходит 
перенаправление на сервис оплаты (пока отсутствует) и выдается номер заказа для контроля 
его выполнения.

В проекте задуманы два разных варианта книги: бумажный и цифровой.
При приобретении бумажного варианта после оплаты создается заказ. 
Если приобретается цифровой, то после оплаты клиент получает ссылку на скачивание книги.

Использует ThymeLeaf для управления html-страницами и отображения данных пользователю. 

## 2. admin_web_service

Сервис для взаимодействия с администраторами магазина.
Добавление новых книг, также изменение информации, доступ к заказам.
Авторизация осуществляется через собственный Oauth2 Server authorization_service 
с выдачей JWT токена и проверкой его на конечных точках через Oauth2 Resource Server.
Также предусматривается добавление новых администраторов магазина.

Использует ThymeLeaf для управления html-страницами и отображения данных пользователю.

Сервис также не закончен на данном этапе.


## 3. authorization_service

Сервис Oauth2 авторизации с выдачей JWT-токена. 
Запрашивает имя пользователя и пароль, после аутентификации выдает JWT-токен.
Микросервисы, являющиеся серверами ресурсов, проверяют валидность токен и 
предоставляют доступ к защищеным ресурсам.

## 4. book_service

Это будет основной сервис магазина. Содержит данные о книгах, хранящиеся в СУБД PostgresSQL. 
Защищен как Oauth2 сервер ресурсов. Предоставляет набор CRUD операций с сущностью книги.
Также сервис будет взаимодействовать с платежным сервисом (пока не реализован), сервисом заказов,
сервисом хранения данных через gateway_service.
Сервис пока не закончен, на данный момент работает как база данных с книгами. 


## 5. data_storage_service

Сервис, хранящий данные, такие как обложки книг, сами книги в цифровых форматах. 
Также является сервером ресурсов. При сохранении генерирует имя файла через класс UUID, 
проверяет что такого имени еще нет, сохраняет файл и возвращает его имя. Имя сохраняется в 
book_database_service в соответствующем поле таблицы. При неоходимости получить данные файл
можно получить по имени. 

## 6. order_service

Сервис, хранящий данные о заказе. Тоже является Oauth2 сервером ресурсов для контроля доступа к 
защищеным данным. Хранит заказы в СУБД Postgres. 

## 6. docker

В этой папке будут хранится файлы, необходимые для разворачивания проекта 
через Docker.

## Примечание.

При доступе к административному сервису при тестировании проекта на локальной машине 
нужно использовать http://127.0.0.1:port вместо http://localhost:port.





