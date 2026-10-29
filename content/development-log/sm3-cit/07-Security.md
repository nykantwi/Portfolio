---
title: "Uge 7 – Security"
date: 2026-10-29T15:00:00+01:00
description: "Login med bcrypt og JWT, rollebaseret adgangskontrol og tests af beskyttede endpoints."
project: "sm3-cit"
draft: false
---

## Ugens arbejde

I denne uge har jeg tilføjet security til mit Cyber Incident Tracker-projekt. Før kunne man kalde mine incident-endpoints uden at være logget ind. Nu kræver de et gyldigt JWT, og kun en bruger med rollen ADMIN må slette hændelser.


## Brugere, passwords og roller

Min `User` har fået et `passwordHash`. Når en bruger oprettes med navn, e-mail og password, hasher constructoren passwordet med bcrypt. Ved login bruger `verifyPassword()` bcrypt til at kontrollere det indtastede password mod den gemte hash. Passwordet gemmes altså ikke som almindelig tekst.

Jeg har også udeladt password-hashen fra JSON og `toString()`, så den ikke kommer med i almindelige svar eller udskrifter af brugeren.

Hver bruger har én rolle: REPORTER, ANALYST eller ADMIN. Jeg valgte én rolle pr. bruger for at holde datamodellen og adgangskontrollen overskuelig. Nye brugere får automatisk REPORTER. Roller administreres indtil videre manuelt i databasen.

## Registrering og login

Jeg har lavet to offentlige endpoints:

- `POST /api/v1/auth/register` opretter en bruger og returnerer status 201.
- `POST /api/v1/auth/login` kontrollerer e-mail og password og returnerer et JWT med status 200.

`CreateUserDTO` indeholder navn, e-mail og password. `LoginDTO` indeholder e-mail og password, mens `TokenDTO` bruges til svaret fra login. DAO'en kontrollerer blandt andet, at registreringsfelterne er udfyldt, og at e-mailen ikke allerede findes.

Jeg bruger undervisningens `TokenSecurity`-bibliotek til JWT. Tokenet indeholder brugerens e-mail, rolle og udløbstid. Serverens signeringsnøgle læses fra miljøvariablen `JWT_SECRET`. Et token gælder i én time.

## Authentication og authorization

Jeg har lært forskellen på de to begreber gennem mit DELETE-endpoint.

**Authentication** kontrollerer, om requesten har et gyldigt token. Klienten sender det i en header:


Authorization: Kontrollere hva hvilke endpoints brugeren må tilgå ud fra sin rolle


Min `authenticate()`-metode læser headeren og kontrollerer tokenets signatur og udløbstid. Bagefter gemmer den brugeroplysningerne på den aktuelle request med `ctx.attribute("user", user)`.

**Authorization** kontrollerer, om brugeren må udføre handlingen. `authorizeAdmin()` læser brugeren fra requesten og kontrollerer rollen. Javalins `beforeMatched()` sørger for, at kontrollerne kører før incident-controlleren.

REPORTER, ANALYST og ADMIN kan hente, oprette og opdatere hændelser. Kun ADMIN kan slette. En request uden token får 401, mens en REPORTER, der forsøger at slette, får 403. Det viser forskellen på at mangle et gyldigt login og at mangle adgang til en bestemt handling.

## Tests

Jeg har opdateret mine eksisterende API-tests, fordi de nu skal sende tokens. I `setUp()` opretter jeg en REPORTER og en ADMIN i den separate testdatabase. Begge har testpasswordet `1234` og e-mailadresser under `@test.com`. Testene logger ind gennem API'et og gemmer hvert token.

GET-, POST- og PUT-requests bruger REPORTER-tokenet. DELETE-testen bruger ADMIN-tokenet og kontrollerer status 204. Et efterfølgende GET skal give 404, fordi hændelsen er slettet.

Jeg har også tilføjet fire security-tests:

- GET uden token giver 401.
- GET med et ugyldigt token giver 401.
- DELETE som REPORTER giver 403. Et efterfølgende GET kontrollerer, at hændelsen stadig findes.
- Login med forkert password giver 401.

Lige nu har jeg 33 tests som kører uden fejl, heraf 11 tests i `ApiTest`. De nye tests dækker udvalgte afvisninger; der mangler blandt andet særskilte tests af udløbne tokens og flere fejl ved registrering.

## Udfordringer og læring

En udfordring var, at en bruger stadig fik 403 efter en ændring til ADMIN. Det viste sig, at ændringen i pgAdmin ikke var gemt. Det fik mig til at overveje, om jeg i fremtiden kunne lave et endpoint til at opdatere brugernes roller i stedet for at ændre dem manuelt i databasen.

Jeg lærte også, at en rolleændring i databasen ikke ændrer et token, der allerede er udstedt. Brugeren skal logge ind igen for at få et token med den nye rolle. Et gammelt token kan fortsat bruges med sin gamle rolle, indtil det udløber.

Da jeg begyndte at tilpasse testene, manglede JUnit-konfigurationen `JWT_SECRET`. Selvom variablen var sat i Main-konfigurationen, blev den ikke automatisk brugt af testene. Jeg tilføjede derfor en separat testnøgle. Testene skulle også sende deres token med hver beskyttet request; det er ikke nok blot at logge ind i `setUp()`.

Under den manuelle test fik jeg først 404 ved sletning som ADMIN, fordi hændelsen ikke fandtes. Efter at have oprettet en ny testhændelse og brugt dens ID fik jeg 204. Jeg har dermed arbejdet med forskellen på 401, 403, 404 og et vellykket svar.

## Refleksion og mulige forbedringer

Jeg har valgt at administrere roller manuelt for at holde projektets omfang overskueligt. En senere udvidelse kunne være et administratorbeskyttet endpoint til rolleændringer. Almindelige brugere skal ikke kunne gøre sig selv til ADMIN.

Adgangskontrollen er foreløbig baseret på rolle, ikke på ejerskab. En indlogget bruger kan hente og opdatere alle hændelser, og rapportørens e-mail kommer stadig fra requesten. En forbedring kunne være at knytte rapportøren til den indloggede bruger og kontrollere, hvilke hændelser brugeren må ændre.

Der er heller ikke implementeret tilbagekaldelse af tokens ved logout eller rolleændringer. Det er en begrænsning ved den nuværende løsning, som jeg kan arbejde videre på.

Jeg har opdateret API-dokumentationen med register, login, headers og adgangskrav. Security-arbejdet er afprøvet lokalt; deployment er ikke implementeret endnu.

[Se CIT’s API-dokumentation]({{< relref "/documentation/cit-api" >}})

[Tilbage til SM3-CIT]({{< relref "/projects/sm3-cit" >}})
