# Java EE Database interface

This project manages the tables Students, Teacher, Departments.

> A many-to-many relationship exists between students and teachers

> a many-to-many relationship exists between teachers and departments

## This project uses Java Servlets, Java Filters, Java Server Pages, JDBC, JSTL and an OracleSQL database

Also it uses the MVC Paradigm with

> servlet classes -> controller

> jsp file -> view

> service + dao + model classes -> model

It also features an authentication/authorization features.
Every user needs to register/login to the app. 

Basic users can only perform read/create/update operations on the DB.
Only admins can perfom the delete operation (full CRUD support) and managed the registered users. 
