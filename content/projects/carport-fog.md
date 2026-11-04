---
title: "Carport FOG"
description: "A group project for configuring carports and managing orders, built with Java, Javalin and PostgreSQL."
draft: false
---

Carport FOG is a group project I worked on with my fellow students. The application lets customers configure a carport and submit an order, with separate areas for customers, sales staff and administrators.

[Open the live application](https://carport.antwi.dk/) · [View the group repository on GitHub](https://github.com/EmilOzel/Carport-FOG)

## Features

- Customer registration and login.
- Carport configuration with dimensions, roofing and an optional shed.
- Price calculations and carport drawings.
- Order management and role-based pages for sales staff and administrators.

## Technology

The application uses Java 21 and Javalin, with Thymeleaf for server-rendered HTML. PostgreSQL stores users, carport configurations and orders. Maven builds the application and runs the JUnit tests.

## Deployment on my own server

I moved the application to my own Ubuntu server on DigitalOcean. It runs in Docker alongside my existing API, with a separate PostgreSQL database and persistent storage. Caddy routes requests to the application and provides HTTPS at **carport.antwi.dk**.

The deployment uses memory limits to fit the small server. The database was initialized from the project's SQL schema, without importing old users or orders. The Docker configuration and deployment instructions are included in the group repository.

This project gave me practical experience with deploying a Java application, connecting containers, configuring a subdomain and managing database configuration through environment variables.

[Back to projects]({{< relref "/projects" >}})
