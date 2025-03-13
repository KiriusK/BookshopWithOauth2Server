<h1 align="center">  Проект книжного интернет-магазина</h1>

## 0. Вступление

Проект написан мной с использованием фреймворка Spring и микросервисной архитектуры.

В проекте реализована маршрутизация между микросервисами с помощью Spring Gateway и
авторизация через Spring Oauth2 Server с выдачей токена и проверкой его 
на микросервисах для доступа к административному функционалу. 


Использовал обьектно-реляционную СУБД PostgresSQL как наиболее часто используемую в 
Java приложениях. 

Стек технологий:
* Java 17
* Spring Boot 3.4.3, Spring JDBC, Spring Web
* Spring Security, Spring Oauth2 Server, Spring Oauth2 Resource Server, Spring Cloud Gateway
* MapStruct
* HTML

## 1. web_service

Сервис для взаимодействия пользователя (покупателя) с магазином.
Позволяет посмотреть доступные книги, провести поиск и провести покупку. 
На данном этапе сервис не закончен, все html страницы созданы для демонстрации бекенда.
В частности, не реализован функционал совершения покупки, создания заказа и оплаты.
Сервис не предусматривает авторизации, после заполнения деталей заказа происходит 
перенаправление на сервис оплаты (пока отсутствует) и выдается номер заказа для контроля 
его выполнения. 

## 2. admin_web_service

Сервис для взаимодействия с администраторами магазина.
Добавление новых книг, также изменение информации, доступ к заказам.
Автризация осуществляется через собственный Oauth2 Server authorization_service с выдачей JWT 
токена и проверкой его на конечных точках через Oauth2 Resource Server.
Сервис также не закончен на данном этапе.





