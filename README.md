

[![codecov](https://codecov.io/gh/lanasergeeva/job4j_cinema/branch/master/graph/badge.svg?token=2PSKGOOSML)](https://codecov.io/gh/lanasergeeva/job4j_cinema)

[![Build Status](https://app.travis-ci.com/lanasergeeva/job4j_cinema.svg?branch=master)](https://app.travis-ci.com/lanasergeeva/job4j_cinema)

# Приложение кинотеатр

+ [Описание](#Описание-проекта)
+ [Технологии](#Используемые-технологии)


## Описание проекта
Это  web-приложение, которое представляет из себя сервис, для бронирования билетов в конкретном кинотеатре.
В нем есть возможность выбора сеансов, мест и оплаты. 
Доступ получают только авторизированные в системе пользователи. 


## Используемые технологии
+ **Maven**
+ **HTML**, **CSS**, **AJAX**, **Jquery**, **Javascript**
+ **Java 14**, **JDBC**, **Servlet**


## Общий вид приложения
![alt text](https://github.com/lanasergeeva/job4j_cinema/blob/master/src/main/java/cinema/rimg/view.jpg)

## Страница с авторизацией

![alt text](https://github.com/lanasergeeva/job4j_cinema/blob/master/src/main/java/cinema/rimg/f_login.jpg)

## Страница с регистрацией

![alt text](https://github.com/lanasergeeva/job4j_cinema/blob/master/src/main/java/cinema/rimg/regist.jpg)

## При регистрации и авторизации добавлена валидация данных

![alt text](https://github.com/lanasergeeva/job4j_cinema/blob/master/src/main/java/cinema/rimg/valid%20log.jpg)

## После авторизации попадаем на главную страницу приложения, в которую подгрузились фильмы из базы данных.

![alt text](https://github.com/lanasergeeva/job4j_cinema/blob/master/src/main/java/cinema/rimg/movies.jpg)


## При нажатии на фильм загружает схема зала, в которой будут отображены выкупленные места на этот сеанс.

![alt text](https://github.com/lanasergeeva/job4j_cinema/blob/master/src/main/java/cinema/rimg/chseans.jpg)

## Пользователь может выбрать свободные места, а также отменить выбор.

![alt text](https://github.com/lanasergeeva/job4j_cinema/blob/master/src/main/java/cinema/rimg/checkPla%D1%81es.gif)

## После того, как пользователь выбрал места, он переходит в корзину, где будет проходить оплата билетов

![alt text](https://github.com/lanasergeeva/job4j_cinema/blob/master/src/main/java/cinema/rimg/pay.gif)

## В корзине указано количество билетов, стоимость. Общая сумма.

![alt text](https://github.com/lanasergeeva/job4j_cinema/blob/master/src/main/java/cinema/rimg/pay.jpg)

## При нажатии оплата будет выводится сообщение о брони билета, либо будет указано, что на данное место билет уже выкуплен.

![alt text](https://github.com/lanasergeeva/job4j_cinema/blob/master/src/main/java/cinema/rimg/payinftick.jpg)

![alt text](https://github.com/lanasergeeva/job4j_cinema/blob/master/src/main/java/cinema/rimg/unf.jpg)

## Работа приложения

![alt text](https://github.com/lanasergeeva/job4j_cinema/blob/master/src/main/java/cinema/rimg/work.gif)




