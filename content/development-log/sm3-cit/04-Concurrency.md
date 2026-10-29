---
title: "Uge 4 – Concurrency"
date: 2026-10-15T22:25:00+02:00
description: "Optælling af hændelser med ExecutorService, Callable og Future."
project: "sm3-cit"
draft: false
---

## Ugeopsummering

Denne uge har jeg arbejdet med concurrency ved at lave en statistikdemo til Cyber Incident Tracker.

Programmet henter hændelser fra databasen og sender to tælleopgaver til en ExecutorService med to worker-thread:

- Tæl alle åbne hændelser.
- Tæl åbne hændelser med MEDIUM severity.

## Hvad jeg har implementeret

Hver opgave er en `Callable<Integer>`, som returnerer et antal. Begge opgaver indsendes med submit(), før resultaterne hentes med Future.get(). Det giver opgaverne mulighed for at køre samtidigt.

Resultaterne udskrives i konsollen. Executor og EntityManagerFactory lukkes i en finally-blok efter håndteringen af resultaterne.

## Hvad jeg har lært

Jeg har lært forskellen på at beskrive en opgave med Callable, sende den til en ExecutorService og hente resultatet gennem Future.

I starten kaldte jeg shutdown(), før opgaverne blev indsendt. Det gav en RejectedExecutionException, fordi executor ikke længere accepterede nye opgaver. Fejlen blev løst ved at flytte shutdown().


## Resultat

En manuel kørsel mod den lokale database viste:

- Åbne hændelser: 2.
- Åbne hændelser med MEDIUM severity: 2.

Da MEDIUM er min default severity til når man opretter hændelser

[Tilbage til SM3-CIT]({{< relref "/projects/sm3-cit" >}})
