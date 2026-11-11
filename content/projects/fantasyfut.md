---
title: "FantasyFUT"
description: "Et SP4/ICE-projekt, jeg har arbejdet på med min studiegruppe, om at omsætte en selvvalgt idé til et fantasyfodboldspil med holdudtagelse, kampsimulering og rangliste."
draft: false
---

FantasyFUT er et **SP4/ICE-projekt**, jeg har arbejdet på med min studiegruppe. ICE står for **Innovation, Creativity og Entrepreneurship**. Opgaven gav os frihed til selv at vælge en idé, definere det problem, applikationen skulle adressere, og afgrænse en løsning, vi kunne implementere.

Vi valgte at arbejde med et fantasyfodboldspil. Den oprindelige applikation blev bygget som et JavaFX-program med SQLite som database. Spillet er siden blevet gjort tilgængeligt i browseren.

[Spil webversionen](https://fantasyfut.antwi.dk/)

## Idé og formål

Idéen med FantasyFUT er at samle holdudtagelse, kampsimulering og pointberegning i én applikation. Brugeren skal kunne sammensætte sit eget hold, simulere kampe og følge spillernes præstationer og sin placering i ligaen.

Spillets forløb giver brugeren mulighed for at afprøve forskellige hold og se, hvordan mål, assists, spilletid og kort påvirker pointene.

## Fra krav til design

ICE-opgaven lagde op til at arbejde fra funktionelle krav og objektanalyse til domænemodel, klassediagram og implementering. I projektets README beskrev vi blandt andet krav om brugerlogin, hold på præcis 11 spillere, spillersøgning, kampsimulering, pointberegning og nulstilling af sæsonen.

Projektets designmateriale indeholder en domænemodel og et klassediagram i PlantUML. Modellerne beskriver blandt andet brugere, hold, spillere, klubber, kampe og statistik samt relationerne mellem dem. De danner grundlag for at diskutere domænet og planlægge ansvarsfordelingen i koden.

## Det færdige spil

Brugeren kan oprette en konto, logge ind og gemme en startellever. Spillerlisten kan søges og filtreres efter position. Kampmotoren genererer kampbegivenheder, og pointberegneren omsætter dem til spillerpoint og holdets samlede score.

Ranglisten giver overblik over managerne og deres point. Brugeren kan også se holdenes individuelle spillerpoint og starte en ny sæson ved at nulstille resultaterne.

## Fra JavaFX til browser

Webversionen genbruger Java-modellerne, generatoren til kampprogrammet, kampmotoren og pointberegneren fra desktopprojektet. Javalin leverer HTTP-API’et, og HTML, CSS og JavaScript genskaber den oprindelige brugerflade med de samme menuer og fodboldbaggrunde.

I webversionen vælger brugeren en startellever i en 4–4–2-opstilling blandt **526 spillere og 20 klubber**. Hver manager har sin egen sæson på 38 runder, som gemmes i SQLite.

## Erfaringer fra arbejdet

Projektet samler arbejdet med at omsætte en selvvalgt idé til konkrete krav, modellere et domæne og fordele funktionalitet mellem brugerflade, datalagring og spillogik. Den senere webversion bygger videre på dette ved at genbruge spillets logik gennem en ny brugerflade.

**Teknologier** · Java 21 · JavaFX · SQLite · JDBC · PlantUML · Maven · Javalin · HTML/CSS/JavaScript

[Tilbage til projekter]({{< relref "/projects" >}})
