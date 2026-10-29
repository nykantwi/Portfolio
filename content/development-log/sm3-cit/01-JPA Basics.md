---
title: "Uge 1 – JPA Basics"
date: 2026-09-19T21:00:00+02:00
description: "Opsætning af projektet, lagring af hændelser og CRUD med JPA/Hibernate."
project: "sm3-cit"
week: "2026-W38"
period: "Kalenderuge 38 · 14.–20. september 2026"
draft: false
---

## Opsumering 

Denne uge begyndte jeg på Cyber Incident Tracker og opbyggede fundamentet til projektets backend med Java, JPA/Hibernate og PostgreSQL.

Fokus var at kunne oprette hændelser som Java-objekter, gemme dem i databasen og hente dem igen.

## Hvad jeg har implementeret?

Jeg oprettede projektets packages og en `Incident`-entity med ID, titel og beskrivelse. Med JPA-annotationer koblede jeg klassen til tabellen `incidents` i min database.

Derefter implementerede jeg CRUD i `IncidentDAO` med metoder til at:

- Oprette en hændelse.
- Hente en hændelse ud fra dens ID.
- Hente alle hændelser.
- Opdatere en hændelse.
- Slette en hændelse.

Metoder, der ændrer data, bruger transaktioner med commit() og rollback(). Hver DAO-metode åbner og lukker sin egen EntityManager.

Jeg afprøvede CRUD-forløbet manuelt med en testhændelse. På dette tidspunkt havde jeg endnu ikke automatiserede DAO-tests.

## Status og severity

For at gøre hændelserne mere relevante for projektet tilføjede jeg to enums:

- **Status:** `OPEN`, `INVESTIGATING`, `CONTAINED` og `RESOLVED`.
- **Severity:** `LOW`, `MEDIUM`, `HIGH` og `CRITICAL`.

Enum-værdierne gemmes som tekst i databasen med `EnumType.STRING`. Nye hændelser får automatisk status `OPEN` og severity `MEDIUM`, medmindre værdierne ændres eller bliver sat.


*Opsummeringen beskriver arbejdet i denne uge. Senere udvidelser og tests dokumenteres i de efterfølgende indlæg.*

[Tilbage til SM3-CIT]({{< relref "/projects/sm3-cit" >}})