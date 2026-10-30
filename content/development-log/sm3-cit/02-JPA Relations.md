---
title: "Uge 2 – JPA Relations"
description: "Relationer mellem brugere og hændelser, DAO-interfaces og validering."
project: "sm3-cit"
draft: false
---

## Hvad har jeg implementeret?

Jeg har implementeret relationer mellem brugere, hændelser og kommentarer med JPA. En hændelse har en rapportør, 
og en kommentar tilhører både en bruger og en hændelse.

## Implementering


- User, Incident og Comment med ManyToOne-relationer.
- DAO-klasser og interfaces til databaseoperationer.
- JPQL til brugeropslag, rapportør filtrering og kommentarer til en hændelse.
- IncidentService med validering og håndtering af hændelser.

## Test og læring

Der er implementeret databasetests med JUnit og Testcontainers for CRUD, relationer, brugeropslag og filtrering.

Jeg har lært at adskille databasearbejdet i DAO-laget fra validering og forretningslogik i service-laget.

## Næste skridt

- Muligvis tilføje tests af IncidentService.


[Tilbage til SM3-CIT]({{< relref "/projects/sm3-cit" >}})