# Mediatek
----------
## Intro

A simple web app designed with [Ninja framework](www.ninjaframework.org) to dispay a list of products. Each products can be added to a shopping cart.

## Prerequisites

- JDK 1.7+
- Apache Maven 3.1.0+
- MySQL

## Database setup

1. `create database mediatek;`
2. `grant all privileges on mediatek.* to mediatek@'%' identified by 'mediatek';`
3. Database entry point (*e.g.  jdbc:mysql://localhost:3306/mediatek*) in `conf/application.conf` also see `META-INF/persistence.xml`
4. Add a few products: 

    ``` 
    insert into product values (NULL,"Apple");
    insert into product values (NULL,"Blueberry");
    insert into product values (NULL,"Cherries");
    insert into product values (NULL,"Clementine");
    ```

## Deploy & Run

0. `git clone`
1. `cd <root-to-project>`
2. Compile the project: `mvn compile`
3. Run the project: `mvn ninja:run`
4. Access with your browser: `http://localhost:8080/`

## Information

- MVC architecture using Ninja framework. HTML pages are rendered using Freemarker.
- All routes are located in `java/conf/Routes.java`
- An AJAX call is done in `assets/productController.js` to add a product in the shopping cart.
