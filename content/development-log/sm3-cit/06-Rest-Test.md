---
title: "Uge 6 – REST & Test"
date: 2026-10-25T12:00:00+01:00
description: "Automatiserede API-tests med REST Assured, Hamcrest og en separat PostgreSQL-testdatabase."
project: "sm3-cit"
draft: false
---

## Ugens arbejde

I undervisningsforløbets uge 6 har jeg arbejdet med automatiserede tests af mit REST API i Cyber Incident Tracker. I sidste uge afprøvede jeg endpoints manuelt med HTTP-requests i IntelliJ. Nu har jeg lavet en `ApiTest`-klasse, der sender requests og kontrollerer svarene automatisk.

Jeg har brugt kode fra mine tidligere opgaver som udgangspunkt. Databaseopsætningen bygger videre på `IncidentDAOTest`, og POST-testen følger fremgangsmåden fra `HotelRouteTest` i Hotel API -projekts som vi arbejdet med Jesper på.

## Testopsætning

Jeg har tilføjet REST Assured til projektet. Det bruges til at sende HTTP-requests, mens Hamcrest bruges til at kontrollere værdier i svarene, for eksempel en titel eller en status.

Testcontainers starter en separat PostgreSQL-database med navnet `incident_api_test`. Gennem `HibernateTestConfig` oprettes en `EntityManagerFactory`, som både testdata og API-serveren bruger. På den måde arbejder testene med deres egen database.

Jeg har tilpasset `ApplicationConfig`, så serveren kan modtage en factory som argument. `Main` kan stadig starte serveren med den almindelige database, mens testen sender sin egen factory med. Under testen bruger serveren port `0`, så systemet vælger en ledig port.

Et fælles `requestSpec` indeholder serverens adresse, port, stien `/api/v1` og JSON-indstillingerne. Det gør testene kortere, fordi de ikke skal gentage hele opsætningen.

## Før og efter testene

Jeg har arbejdet med tre JUnit-annotations:

- `@BeforeAll` opretter forbindelsen til testdatabasen og starter serveren én gang før testene.
- `@BeforeEach` rydder testtabellerne og opretter en frisk testbruger og hændelse før hver test.
- `@AfterAll` stopper serveren og lukker factoryen, når testene er færdige. Testcontainers håndterer databasecontainerens opstart og nedlukning.

Oprydningen sker i rækkefølgen kommentarer, hændelser og brugere, så relationerne i databasen respekteres. Friske testdata gør, at en test, der sletter eller ændrer en hændelse, ikke påvirker den næste test.

## Hvad testene kontrollerer

Jeg har nu syv API-tests:

- **GET af alle hændelser:** Kontrollerer status `200` og JSON som indholdstype.
- **GET med kontrol af indhold:** Kontrollerer, at listen indeholder testhændelsens titel.
- **GET via ID:** Kontrollerer, at den rigtige hændelse returneres med det forventede ID, titel og status.
- **POST:** Opretter en hændelse og kontrollerer status `201`, et genereret ID og de forventede felter. Hændelsen hentes bagefter med GET for at kontrollere, at den er gemt.
- **POST med tom titel:** Kontrollerer status `400` og fejlbeskeden `Titel må ikke være tom`.
- **PUT:** Ændrer titel, beskrivelse, severity og status. Et efterfølgende GET kontrollerer, at ændringerne er gemt.
- **DELETE:** Kontrollerer status `204` og en tom response body. Et efterfølgende GET på samme ID skal give `404`.

## Given, When og Then

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

Her vælger jeg først request-indstillingerne og hændelsens ID. Derefter sender jeg en GET-request, og til sidst kontrollerer jeg svaret.

## Udfordringer og læring

En af fejlene opstod, fordi jeg hentede hændelsens ID, før den var gemt. ID’et var derfor `null`, og REST Assured kunne ikke bruge det i URL’en. Jeg løste det ved at gemme ID’et efter `persist()` og `commit()` inde i `setUp()`.

Jeg fik også importeret `hasItem` fra Testcontainers’ interne Hamcrest-pakke. Den skulle i stedet komme fra `org.hamcrest.Matchers`. Det lærte mig at kontrollere imports, når IntelliJ foreslår flere klasser eller metoder med samme navn.

En anden udfordring var at koble API-serveren til testdatabasen. `setTest(true)` ændrede ikke databaseforbindelsen i min eksisterende opsætning. Derfor tilpassede jeg `startServer`, så testen kunne sende sin egen `EntityManagerFactory` med. På den måde bruger API’et og testdata den samme separate database.

Jeg har fået en bedre forståelse af forskellen på at kontrollere en liste med `hasItem` og at sammenligne et enkelt felt med `equalTo`. Jeg har også lært, at en succesfuld statuskode ikke alene viser, at de rigtige data er gemt. Derfor bruger POST- og PUT-testene en ny GET-request til at kontrollere resultatet.

## Resultat og næste skridt

Ved kørslen bestod alle syv tests i `ApiTest` uden fejl. Denne kørsel omfattede API-testklassen, ikke hele projektets testsuite.

Testene dækker nu de grundlæggende CRUD-forløb og udvalgte fejl. En mulig udvidelse er at teste ugyldige ID’er, for eksempel `-1`, som skal give `400`, og flere ugyldige oplysninger ved oprettelse og opdatering.

WebSockets og Webhooks indgår også i ugens undervisning, men er desværre ikke implementeret i min projekt. 

[Se CIT’s API-dokumentation]({{< relref "/documentation/cit-api" >}})

[Tilbage til SM3-CIT]({{< relref "/projects/sm3-cit" >}})
