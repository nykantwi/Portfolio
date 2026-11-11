---
title: "Cupcake Shop"
description: "Et fælles projekt fra 2. semester om en webshop, der digitaliserer bestilling af cupcakes fra kundens valg af bund og topping til betaling og ordrebehandling."
draft: false
---

Cupcake Shop er et fælles projekt fra **2. semester på datamatikeruddannelsen**, som jeg udviklede sammen med Emil, Tobias og Seif. Vi byggede et webbaseret bestillingssystem til Olsker Cupcakes med kundelogin, indkøbskurv og administration af brugere og ordrer.

[Prøv demobutikken](https://cupcake.antwi.dk/)

## Baggrund og formål

Projektets formål var at give virksomheden en digital løsning til at modtage og håndtere cupcakebestillinger. Kunden skulle selv kunne sammensætte og bestille sine cupcakes online og derefter afhente dem i butikken.

Målet var at gøre ordrebehandlingen mere overskuelig, holde styr på kunder og deres køb samt reducere fejl og tidsforbrug i den daglige drift. Projektarbejdet omfattede krav, design og implementering af løsningen.

## Kundens bestillingsforløb

Kunden opretter en konto og logger ind. Derefter vælger kunden bund, topping og antal og lægger sine cupcakes i indkøbskurven. Kurven viser priserne og den samlede pris, så kunden kan gennemgå bestillingen.

Før betaling kan kunden ændre antal eller fjerne varer. Ved checkout trækkes beløbet fra kundens saldo, og bestillingen gemmes med sine ordrelinjer. Kunden kan derefter se sine bestillinger og følge deres status.

## Administration

Administrationsdelen giver adgang til at håndtere brugere og ordrer. Administratoren kan se bestillinger, opdatere ordrestatus og tilføje saldo til kundekonti. Funktionerne understøtter virksomhedens arbejde med at behandle de indkomne bestillinger.

## Teknologi og opbygning

- **Java 21** bruges til applikationens logik.
- **Javalin 6.5.0** håndterer routes, requests og sessions til login og indkøbskurv. Webserveren er **Jetty 11.0.24**, som anvendes via Javalin.
- **Thymeleaf 3.1.2** genererer HTML på serveren, og **HTML og CSS** udgør frontenden.
- **PostgreSQL** gemmer kunder, produkter og bestillinger. Databaseadgangen sker gennem **JDBC** med PostgreSQL-driver **42.7.5** og **HikariCP 6.2.1** som connection pool.
- **IntelliJ IDEA** bruges som udviklingsmiljø, og **Maven** håndterer afhængigheder og build.

Ved checkout samles saldotræk, ordre og ordrelinjer i en databasetransaktion, så ændringerne kan rulles tilbage, hvis bestillingen fejler.

## Prøv projektet

Demobutikken kører på min egen DigitalOcean-server med Docker og HTTPS. Nye konti får **100 demokroner**, så besøgende kan afprøve bestillingsforløbet med fiktive køb.

[Tilbage til projekter]({{< relref "/projects" >}})
