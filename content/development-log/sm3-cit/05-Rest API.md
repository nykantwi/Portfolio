---
title: "Uge 5 – REST API intro"
date: 2026-10-21T12:00:00+02:00
description: "REST API til CIT med Javalin, CRUD-endpoints, DTO’er, validering og fejlhåndtering."
project: "sm3-cit"
draft: false
---

## Ugens arbejde

Denne uge har jeg arbejdet med at bygge et REST API til mit Cyber Incident Tracker-projekt. Tidligere brugte jeg Java-programmer til at afprøve funktionerne direkte. Nu kan jeg sende HTTP-requests til serveren og hente eller ændre hændelser i databasen gennem API’et.

Jeg har brugt mit Hotel API fra undervisningen som skabelon og tilpasset strukturen til CIT. Det har hjulpet mig med at forstå, hvordan routes, controllers, DTO’er og service-laget arbejder sammen.

## Hvad jeg har implementeret

Jeg har sat en Javalin-server op på port `7070` med `/api/v1` som fælles start på mine routes. API’et har følgende endpoints:

Alle endpoints starter med `/api/v1`. v1 betyder bare version 1.

- **GET /incidents** – Henter alle hændelser. 
- **GET /incidents/{id}** – Henter én hændelse. 
- **POST /incidents** – Opretter en hændelse. 
- **PUT /incidents/{id}** – Opdaterer en hændelse.
- **DELETE /incidents/{id}** – Sletter en hændelse. 


Routes forbinder en HTTP-metode og en URL med en metode i `IncidentController`. Controlleren læser requesten og kalder `IncidentService`, som validerer oplysningerne og bruger DAO-laget til at arbejde med databasen.

Ved opdatering kan jeg ændre titel, beskrivelse, severity og status. Hændelsen beholder sin oprindelige rapportør. Ved sletning returneres status `204` uden en response body.

## DTO’er og JSON

Jeg har arbejdet med tre DTO’er:

- `IncidentDTO` bruges til at sende hændelser tilbage til klienten. Den indeholder blandt andet hændelsens ID, titel, status, severity og rapportørens ID.
- `CreateIncidentDTO` bruges til at modtage oplysninger ved oprettelse, herunder e-mailen på en eksisterende rapportør.
- `UpdateIncidentDTO` bruges til at modtage de felter, der kan ændres ved opdatering.

Jeg har lært, at oplysningerne i en request ikke nødvendigvis er de samme som i et response. Ved oprettelse sender klienten eksempelvis rapportørens e-mail, mens svaret indeholder `reportedById`.

Når alle hændelser hentes, gennemløber controlleren listen og omdanner hver `Incident` til en `IncidentDTO`. Derefter sendes listen som JSON.

## Validering, fejlhåndtering og logging

Jeg har tilføjet kontrol af, at ID’et i URL’en er et tal større end nul. Service-laget kontrollerer blandt andet, at titlen ikke er tom, at severity er angivet, og at beskrivelsen højst er 2.000 tegn. Ved opdatering skal status også være angivet.

Jeg har arbejdet med forskellen på følgende HTTP-statuskoder:

- `400 Bad Request`: Ugyldige oplysninger, for eksempel en tom titel.
- `404 Not Found`: Hændelsen findes ikke.
- `500 Internal Server Error`: En uventet fejl på serveren.

Min `ExceptionController` håndterer `ApiException` og sender en fejlbesked som JSON. Uventede fejl bliver logget med fejldetaljer, mens klienten får en generel besked om en intern serverfejl.

Undervejs fik jeg først status `500` ved fejl, som skulle have været håndteret som ugyldigt input eller en manglende hændelse. Det hjalp mig med at forstå, hvorfor de rigtige exceptions og statuskoder er vigtige.

## Afprøvning og tests

Jeg har afprøvet API’et med HTTP-requests i IntelliJ. Jeg fik blandt andet `201 Created` ved oprettelse, `400` ved en tom titel og `204 No Content` ved sletning. Jeg har også afprøvet opslag med ugyldige ID’er og hændelser, der ikke findes.

I forbindelse med opdateringsfunktionen blev der tilføjet to automatiserede tests. De kontrollerer, at ændringer gemmes korrekt, at rapportøren bevares, og at ugyldige oplysninger eller et ukendt ID afvises uden at ændre den eksisterende hændelse.

Ved testkørslen bestod alle 17 tests i projektet. Det var 15 eksisterende tests og de to nye tests. De nye tests kontrollerer service- og databaseforløbet i en isoleret PostgreSQL-testcontainer; de er ikke automatiserede HTTP-endpointtests.

## Hvad jeg har lært og næste skridt

Jeg har fået en bedre forståelse af hele forløbet fra en HTTP-request til databasen og tilbage som JSON. Jeg har også lært, at serverens port er adskilt fra de porte, som PostgreSQL og pgAdmin bruger. Mit API kører på `7070`, mens pgAdmin bruger `8080`.

CRUD-funktionerne til hændelser er nu tilgængelige gennem API’et. Jeg har også skrevet API-dokumentation med request- og response-eksempler, nødvendige felter og mulige statuskoder.

## API-dokumentation

Her kan du læse, hvordan CIT’s API bruges:

<a class="btn btn-primary" href="{{< relref "/documentation/cit-api" >}}">Læs API-dokumentationen</a>

[Tilbage til SM3-CIT]({{< relref "/projects/sm3-cit" >}})
