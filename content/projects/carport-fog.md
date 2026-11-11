---
title: "Carport FOG"
description: "Et fælles studieprojekt om at digitalisere FOGs carportbestilling fra kundens konfiguration til sælgerens ordrebehandling."
draft: false
---

Carport FOG er et studieprojekt, som vi udviklede i samarbejde på datamatikeruddannelsen. Opgaven var at udvikle et webbaseret carportsystem med udgangspunkt i FOGs forretning og arbejdsgange. Målet var en løsning, der kunne danne grundlag for digitalisering af virksomhedens bestillingsproces.

[Åbn applikationen](https://carport.antwi.dk/)

## Baggrund og formål

Projektets udgangspunkt var en manuel bestillingsproces, hvor kunden indsender sine ønskede mål, og en sælger derefter genindtaster dem i et system for at beregne en pris og sende et tilbud. Det tager tid og øger risikoen for fejl.

Vi arbejdede derfor med, hvordan kunden selv kunne konfigurere en carport ud fra faste valgmuligheder, indsende en bestilling og følge sin ordrestatus. Samtidig skulle sælgere og administratorer kunne behandle ordrer og administrere systemet uden at genindtaste kundens valg.

## Projektets fokus

- Digital bestilling og ordrebehandling fra kundens konfiguration til sælgerens behandling.
- Automatisk prisberegning samt generering af materialelister og SVG-plantegninger ud fra kundens valg.
- Lagring af brugere, ordrer og carportdata i PostgreSQL.
- Login, validering og rollebaseret adgang for kunder, sælgere og administratorer.
- En MVC-inspireret opbygning, der gør løsningen overskuelig og lettere at vedligeholde.

## Teknologi

Applikationen bruger Java 21 og Javalin med en MVC-inspireret arkitektur. Thymeleaf genererer HTML på serveren, og PostgreSQL gemmer brugere, carportkonfigurationer og bestillinger. Maven bygger applikationen og kører JUnit-testene.

[Tilbage til projekter]({{< relref "/projects" >}})
