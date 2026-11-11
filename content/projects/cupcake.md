---
title: "Cupcake Shop"
description: "Et fælles studieprojekt med en webshop, hvor kunder sammensætter cupcakes, lægger dem i kurven og følger deres bestillinger."
draft: false
---

Cupcake Shop er et studieprojekt, som jeg udviklede sammen med Emil, Tobias og Seif. Vi byggede en webshop til Olsker Cupcakes med kundelogin, sammensætning af cupcakes og administration af kunder og ordrer.

[Prøv demobutikken](https://cupcake.antwi.dk/)

## Fra cupcake til bestilling

Kunden vælger en bund, en topping og et antal. Prisen beregnes ud fra valgene, og kunden kan ændre antal eller fjerne varer fra kurven, før bestillingen gennemføres.

Checkout trækker beløbet fra kundens saldo og gemmer ordren og dens ordrelinjer i PostgreSQL. Kunden kan efterfølgende se sine egne bestillinger og deres status. Administratorer kan håndtere ordrer, opdatere ordrestatus og tilføje saldo til kundekonti.

Den publicerede udgave er en demobutik med fiktive køb. Nye konti får **100 demokroner**, så besøgende kan afprøve bestillingsforløbet.

## Teknologi og opbygning

Applikationen bruger **Java 21, Javalin og Thymeleaf**. Frontenden består af servergenereret HTML og CSS med gruppens oprindelige design. PostgreSQL gemmer kunder, produkter og bestillinger, og JDBC-mappere står for databaseadgangen.

Ved checkout håndteres saldotræk og oprettelse af ordren i en samlet databasetransaktion. Den publicerede udgave gemmer nye adgangskoder som bcrypt-hashes og begrænser kundens adgang til egne ordredetaljer.

## Hosting

Demobutikken kører i Docker på min Ubuntu-server hos DigitalOcean. Caddy håndterer domænet **cupcake.antwi.dk** og HTTPS. Cupcake har sin egen database og databasebruger i serverens eksisterende PostgreSQL-container.

**Teknologier:** Java · Javalin · Thymeleaf · HTML/CSS · PostgreSQL · JDBC · Maven · Docker · Caddy

[Tilbage til projekter]({{< relref "/projects" >}})
