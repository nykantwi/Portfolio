---
title: "Uge 6 – REST & Test"
date: 2026-10-25T12:00:00+01:00
description: "Automatiserede API-tests med REST Assured, Hamcrest og en separat PostgreSQL-testdatabase."
project: "sm3-cit"
draft: false
---

## Hvad jeg har implementeret

Denne uge har jeg lavet automatiserede tests af mit REST API med REST Assured og Hamcrest. Jeg brugte mine DAO-tests og Hotel API fra undervisningen som hjælp.

## Testopsætning

Testcontainers starter en separat PostgreSQL-database. Før hver test rydder jeg tabellerne og opretter en testbruger og en hændelse. Serveren og testene bruger den samme testdatabase.

### Hvad testene kontrollerer

Jeg har nu 7 API-tests:

- **GET af alle hændelser:** Kontrollerer status `200` og JSON som indholdstype.
- **GET via ID:** Kontrollerer, at den rigtige hændelse returneres med det forventede ID, titel og status.
- **POST:** Opretter en hændelse og kontrollerer status `201`, et genereret ID og de forventede felter. Hændelsen hentes bagefter med GET for at kontrollere, at den er gemt.
- **POST med tom titel:** Kontrollerer status `400` og fejlbeskeden `Titel må ikke være tom`.
- **PUT:** Ændrer titel, beskrivelse, severity og status. Et efterfølgende GET kontrollerer, at ændringerne er gemt.
- **DELETE:** Kontrollerer status `204` og en tom response body og derefter et GET på samme ID skal give `404`.

## Resultat

Alle syv tests i `ApiTest` bestod ved kørslen. Det var kun API-testklassen, ikke hele projektets tests.

## Hvad jeg har lært

### Given, When og Then

Testene er bygget op med `given()`, `when()` og `then()`. Det hjælper mig med at opdele dem i forberedelse, handling og forventet resultat:

```java
given()
        .spec(requestSpec)
        .pathParam("id", incidentId)
.when()
        .get("/incidents/{id}")
.then()
        .statusCode(200)
        .body("title", equalTo("Test af phishing"));
```

## Udfordringer

En af fejlene opstod, fordi jeg hentede hændelsens ID, før den var gemt. ID’et var derfor `null`, og REST Assured kunne ikke bruge det i URL’en. Jeg løste det ved at gemme ID’et efter `persist()` og `commit()` inde i `setUp()`.

En anden udfordring var at koble API-serveren til testdatabasen. `setTest(true)` ændrede ikke databaseforbindelsen i min eksisterende opsætning. Så jeg tilpassede `startServer` så testen kunne sende sin egen `EntityManagerFactory` med. På den måde bruger API’et og testdata den samme separate database.

## Næste skridt

Næste skridt kunne være flere tests med ugyldige ID’er og input. WebSockets og Webhooks er ikke implementeret i min projekt endnu, jeg vil forsøge at implementer det hvis jeg får tid til det.

[Tilbage til SM3-CIT]({{< relref "/projects/sm3-cit" >}})
