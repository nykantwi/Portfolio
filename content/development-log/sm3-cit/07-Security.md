---
title: "Uge 7 – Security"
date: 2026-10-29T15:00:00+01:00
description: "Login med bcrypt og JWT, rollebaseret adgangskontrol og tests af beskyttede endpoints."
project: "sm3-cit"
draft: false
---

## Hvad jeg har implementeret

Denne uge har jeg tilføjet registrering og login til CIT. Passwords gemmes som en bcrypt-hash, og ved login får brugeren et JWT, der gælder i en time. Jeg bruger`TokenSecurity`-bibliotek, og nøglen hentes fra `JWT_SECRET`.

## Brugere, passwords og roller

Min `User` har fået et `passwordHash`. Når en bruger oprettes med navn, e-mail og password, hasher constructoren passwordet med bcrypt. Ved login bruger `verifyPassword()` bcrypt til at kontrollere det indtastede password mod den gemte hash.

Hver bruger har én rolle: REPORTER, ANALYST eller ADMIN. Jeg valgte én rolle pr. bruger for at holde datamodellen og adgangskontrollen mere overskuelig. Nye brugere får automatisk REPORTER. Lige nu administreres roller indtil videre manuelt i databasen.

Incident-endpoints kræver et gyldigt token. Alle tre roller kan hente, oprette og opdatere hændelser, men kun ADMIN kan slette.

## Tests

Jeg har opdateret API-testene, så de logger ind og sender et token med deres requests. Nye tests kontrollerer blandt andet forkert password, ugyldigt token og sletning som REPORTER. Ved kørslen bestod alle test

## Hvad jeg har lært

Jeg har lært forskellen på authentication og authorization.

**Authentication** betyder, at API’et kontrollerer, hvem brugeren er. I min projekt sker det ved at kontrollere brugerens token.

**Authorization** betyder, at API’et kontrollerer, hvad brugeren har tilladelse til ud fra sin rolle.

I mit projekt er reglerne:

- REPORTER må hente, oprette og opdatere hændelser.
- ANALYST må også hente, oprette og opdatere hændelser.
- ADMIN må det samme og kan også slette hændelser.


## Udfordringer

En udfordring var, at en bruger stadig fik `403` efter en ændring til ADMIN fordi ændringen i pgAdmin ikke var gemt. Det fik mig til at overveje, om jeg i fremtiden kunne lave et endpoint til at opdatere brugernes roller i stedet for at ændre dem manuelt i databasen.

Da jeg begyndte at tilpasse testene, manglede JUnit-konfigurationen `JWT_SECRET`. Selvom variablen var sat i Main-konfigurationen, blev den ikke automatisk brugt af testene. Jeg tilføjede derfor en ny testnøgle for at få den til at virke.

## Mulige forbedringer og næste skridt

Lige nu kan brugere som er logget ind hente og opdatere alle hændelser, og rapportørens mail kommer fra requesten. Det kan forbedres ved at bruge den indloggede bruger som rapportør og kontrollere, hvilke hændelser brugeren må ændre.

Jeg vil gerne lave et endpoint, hvor kun ADMIN kan ændre brugernes roller, så jeg ikke skal opdatere dem manuelt i databasen som jeg nævnt tidligere.

## API-dokumentation

[Se API-dokumentationen]({{< relref "/documentation/cit-api" >}})

[Tilbage til SM3-CIT]({{< relref "/projects/sm3-cit" >}})
