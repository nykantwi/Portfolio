---
title: "Uge 3 – Dataintegration med et eksternt API"
date: 2026-10-13T18:48:00+02:00
description: "CVE-opslag med Java HttpClient, JSON-mapping og tests. Koblingen til CIT-hændelser mangler endnu."
project: "sm3-cit"
draft: false
---

## Ugeopsummering

Denne uge har jeg arbejdet med dataintegration ved at hente oplysninger om kendte sikkerhedssårbarheder fra NVD’s CVE-API.

Formålet er at lære at hente eksterne data i Java og omdanne dem til objekter, som projektet kan bruge. På længere sigt kan oplysningerne supplere en hændelse i CIT, hvis den vedrører en kendt sårbarhed.

## Hvad jeg har implementeret

- `CveClient`, som sender GET-requests med Java `HttpClient`.
- JSON-mapping med Jackson og ObjectMapper.
- `CveDTO` med CVE-ID, engelsk beskrivelse og CVSS-score.
- `CveDemo`, som udfører et opslag og viser resultatet i konsollen.

I `CveClient` henter fetchJson() API-svaret som tekst. Metoden fetchById() læser derefter JSON-strukturen og returnerer en CveDTO med de relevante oplysninger.

Der er også tilføjet validering af CVE-ID, timeouts og håndtering af HTTP-fejl.

## Udfordringer og læring

Jeg brugte Activity Logger-opgaven som inspiration til HTTP-kald og JSON-mapping. Jeg lærte, at fremgangsmåden kan genbruges, men at felterne skal tilpasses det konkrete API-svar.

NVD’s JSON indeholder flere niveauer og lister. Derfor bruger jeg JsonNode til at finde CVE-objektet og vælge den engelske beskrivelse. Hele svaret kan ikke læses direkte ind i vores enkle DTO.


Jeg har også lært, at en DTO transporterer udvalgte data og ikke automatisk gemmes i databasen.

## Afprøvning og tests

Fire automatiserede tests kontrollerer JSON-mappingen med faste API-svar. De dækker blandt andet valg af engelsk beskrivelse, håndtering af CVSS-score og manglende oplysninger samt afvisning af tomme resultater og et forkert CVE-ID.

Testene bruger faste svar, så de kan køre uden forbindelse til NVD.

Jeg afprøvede også et rigtigt opslag på `CVE-2021-44228`. Det returnerede en beskrivelse af Log4j-sårbarheden og en CVSS-score på 10.0.


[Tilbage til SM3-CIT]({{< relref "/projects/sm3-cit" >}})