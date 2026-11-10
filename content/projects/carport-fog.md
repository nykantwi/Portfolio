---
title: "Carport FOG"
description: "En applikation til at konfigurere carporte og håndtere bestillinger, bygget med Java, Javalin og PostgreSQL."
draft: false
---

Med Carport FOG kan kunder konfigurere en carport og sende en bestilling. Kunder, sælgere og administratorer har hver deres område i applikationen.

[Åbn applikationen](https://carport.antwi.dk/)

## Funktioner

- Oprettelse af kundekonto og login.
- Konfiguration af carport med mål, tag og mulighed for at tilføje et skur.
- Prisberegning og tegninger af carporten.
- Ordrehåndtering og sider tilpasset sælgere og administratorer.

## Teknologi

Applikationen bruger Java 21 og Javalin. Thymeleaf genererer HTML på serveren, og PostgreSQL gemmer brugere, carportkonfigurationer og bestillinger. Maven bygger applikationen og kører JUnit-testene.

[Tilbage til projekter]({{< relref "/projects" >}})
