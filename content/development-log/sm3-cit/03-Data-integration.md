---
title: "Uge 3 – Dataintegration med et eksternt API"
date: 2026-10-13T18:48:00+02:00
description: "CVE-opslag med Java HttpClient, JSON-mapping og tests. Koblingen til CIT-hændelser mangler endnu."
project: "sm3-cit"
draft: false
---

## Ugens arbejde

Jeg har arbejdet med undervisningsforløbets uge 3 om dataintegration og hentet sårbarhedsoplysninger fra NVD’s CVE-API. Jeg har implementeret `CveClient` med Java `HttpClient`, JSON-mapping med Jackson, en `CveDTO` med ID, engelsk beskrivelse og CVSS-score samt `CveDemo` til konsolopslag. Der er også validering af CVE-ID, timeouts og håndtering af HTTP-fejl.

En udfordring i JSON-mappingen er, at CVSS-scoren kan ligge under forskellige versioner eller mangle. Koden søger derfor fra nyere til ældre versioner og bruger `null`, hvis der ikke findes en score.

Fire automatiserede tests dækker mappingen med faste API-svar. Ved et manuelt opslag på `CVE-2021-44228` fik jeg en beskrivelse og en score på 10.0.

## Status og næste skridt

**API-opslaget er endnu ikke integreret i CIT’s hændelsesforløb.** Det fungerer som en selvstændig demo. Jeg har udskudt koblingen for først at forstå API-kald, JSON og DTO’er. Næste skridt er at kunne knytte et valgfrit CVE-ID til en hændelse og hente oplysningerne gennem service-laget.

[Tilbage til SM3-CIT]({{< relref "/projects/sm3-cit" >}})
