---
title: "Uge 5 – REST API intro"
date: 2026-10-21T12:00:00+02:00
description: "REST API til CIT med Javalin, CRUD-endpoints, DTO’er, validering og fejlhåndtering."
project: "sm3-cit"
draft: false
---

## Hvad jeg har implementeret

Denne uge har jeg lavet et REST API til CIT med Javalin. Før testede jeg funktionerne direkte i Java. Nu kan jeg sende HTTP-requests og oprette, hente, opdatere og slette hændelser.

## Opbygning af API’et

Jeg brugte Hotel API fra undervisningen som hjælp til strukturen. Mine routes starter med `/api/v1`, og serveren kører på port `7070`. Routes sender requesten videre til controlleren, som bruger service- og DAO-laget.

Jeg har også lavet DTO’er til oprettelse, opdatering og svar. Der er kontrol af blandt andet tom titel, ugyldigt ID og manglende severity. Fejl sendes tilbage som JSON med en statuskode.

## Afprøvning og tests

Jeg testede API’et med HTTP-requests i IntelliJ og skrev API-dokumentation. Der blev også tilføjet to tests af opdatering gennem service og database. Ved kørslen bestod alle 17 tests. De var endnu ikke automatiserede HTTP-tests.

## Hvad jeg har lært

Jeg har fået bedre styr på, hvordan en request kommer fra klienten til databasen og tilbage som JSON. Jeg lærte også, at en DTO til oprettelse ikke behøver have de samme felter som svaret.

## Udfordringer

Undervejs fik jeg `500` ved fejl, der skulle give `400` eller `404`. Det hjalp mig med at forstå forskellen på forkert input, en hændelse der ikke findes og en fejl på serveren.

## Næste skridt

Næste skridt er at lave automatiserede tests af endpoints, så jeg ikke skal teste det hele manuelt.

## API-dokumentation

[Se API-dokumentationen]({{< relref "/documentation/cit-api" >}})

[Tilbage til SM3-CIT]({{< relref "/projects/sm3-cit" >}})
