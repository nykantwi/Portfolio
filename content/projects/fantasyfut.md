---
title: "FantasyFUT"
description: "Et fantasyfodboldspil til browseren med holdudtagelse, kampsimulering og rangliste."
draft: false
---

FantasyFUT er et projekt, jeg har arbejdet på med min studiegruppe. Det begyndte som et JavaFX-program til computeren med SQLite som database. Webversionen gør spillets vigtigste funktioner tilgængelige i browseren.

[Spil webversionen](https://fantasyfut.antwi.dk/)

## Sæt et hold og spil en sæson

Vælg en startellever i en 4–4–2-opstilling blandt projektets **526 spillere og 20 klubber**. Søg efter spiller eller klub, filtrér efter position, eller brug hurtigvalg.

Opret en managerkonto, gem din opstilling, og simulér spillerunderne. Kampmotoren genererer mål, assists, spilletid og kort, som den oprindelige pointberegner omsætter til fantasypoint. I kampoversigten kan du se de seneste kampe, resultater og point for hver spiller på dit hold.

Ranglisten viser managerne efter deres samlede point og antal spillede runder. Du kan se andre manageres hold og spillerpoint. Hver manager spiller sin egen simulerede sæson.

## Fra computerprogram til webspil

Webversionen genbruger Java-modellerne for spillere og klubber, generatoren til kampprogrammet, kampmotoren og pointberegneren. Et separat Javalin-modul leverer HTTP-API’et. Brugerfladen er bygget med HTML, CSS og JavaScript og tilpasser sig både computer og mobil. Den genskaber menuerne og spilskærmene fra JavaFX-versionen med de samme fodbold- og stadionbaggrunde.

Hver manager har sin egen browsersession, og adgangskoder gemmes som bcrypt-hashes. Hver kontos sæson gemmes i SQLite. Opstillingen låses efter første spillerunde, og hver runde gemmes i én databasetransaktion. En sæson varer 38 runder. Ved nulstilling slettes managerens resultater og point, mens kontoen og opstillingen bevares.

De automatiske tests dækker validering af konti og hold, separate sæsoner, nulstilling, pointberegning, grænsen på 38 runder, datalagring, browsersessioner og CSRF-beskyttelse.

**Teknologier** · Java 21 · Javalin · SQLite · HTML/CSS/JavaScript · Maven · JUnit

[Tilbage til projekter]({{< relref "/projects" >}})
