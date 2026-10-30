---
title: "Uge 8 – Deployment"
description: "Deployment af CIT med GitHub Actions, Docker Hub, DigitalOcean og HTTPS gennem Caddy."
project: "sm3-cit"
draft: false
---

## Hvad jeg har implementeret

Denne uge har jeg deployet CIT til DigitalOcean, så API'et kan bruges uden at køre på min egen computer.

Jeg har fulgt undervisningens skabeloner til GitHub Actions, Docker og Caddy og tilpasset dem til mit projekt.

API'et er tilgængeligt på:

https://api.antwi.dk/api/v1/incidents

Endpointet kræver et gyldigt JWT. Derfor får man 401, hvis man åbner linket direkte uden et token.

## Build og GitHub Actions

Jeg har tilføjet Maven Shade Plugin, som samler projektet og dets biblioteker i én fil: `app.jar`. Min Dockerfile bruger
Java 17 og starter serveren fra denne fil.

GitHub Actions kører testene og bygger projektet. Hvis det lykkes, bygger workflowet et Docker-image og uploader det til
Docker Hub som `nyaw1/cit:latest`.

Docker Hub-oplysningerne ligger som GitHub Secrets. Min lokale `config.properties` bliver ikke pakket med i den færdige 
JAR-fil.

## Docker Compose og serveren

På DigitalOcean bruger jeg Docker Compose til at køre tre services:

- **api:** Min Javalin-applikation.
- **db:** PostgreSQL-databasen.
- **caddy:** Modtager HTTPS-requests og sender dem videre til API'et.


## Domæne og HTTPS

Jeg har oprettet en DNS-record, som peger `api.antwi.dk` på serverens IP-adresse.

Caddy fungerer som reverse proxy og håndterer HTTPS-certifikatet. Requests til domænet bliver sendt videre til API-containeren på port 7070.

## Udfordringer

Jeg fik først timeout, da jeg forsøgte at tilgå API'et fra min Mac. API'et virkede inde på serveren, men DigitalOceans firewall tillod ikke port 7071. 
Jeg tilføjede adgang fra min egen IP for at teste forbindelsen. Den almindelige adgang går nu gennem Caddy på HTTPS-port 443.
Jeg havde også problemer med indrykningen i `compose.yaml`. Caddy-servicen stod forkert placeret, og Docker Compose kunne derfor ikke læse filen.


## Afprøvning

Jeg har afprøvet registrering, login og hentning af hændelser med et JWT på serveren.
Jeg har også kontrolleret, at HTTPS-adressen returnerer 401 uden token. Efter en opdatering af API-containeren kontrollerede jeg igen, at API'et svarede gennem Caddy.

## Hvad jeg har lært
Jeg har lært, hvordan man peger sit domæne på en server ved at lade DigitalOcean håndtere DNS og oprette en A-record.
Derefter har jeg lært at pakke mit Java-projekt i en JAR-fil, bygge et Docker-image og bruge GitHub Actions til at teste projektet og uploade imaget til Docker Hub.
Jeg har også arbejdet med miljøvariabler, firewallregler og HTTPS for at få API’et til at køre og være tilgængeligt gennem mit domæne. 


