---
title: "FantasyFUT"
description: "A fantasy football group project with squad selection, match simulation and a leaderboard — now available in the browser."
draft: false
---

FantasyFUT is a student group project I worked on with Emil, Tobias and Seif. It began as a JavaFX desktop application backed by SQLite. The web edition brings the core game to the browser and is hosted on my own DigitalOcean server.

[Play the web version](https://fantasyfut.antwi.dk/classic.html)

## Build a squad and play a season

Choose a starting eleven in a 4–4–2 formation from the project's dataset of **526 players and 20 clubs**. Search by player or club, filter by position, or use quick pick to get started.

Create a manager account, save your lineup, and simulate gameweeks. The match engine generates goals, assists, playing time and cards, and the original points calculator converts those performances into fantasy points. The match centre shows the latest fixtures, scorelines and individual points for your eleven.

The leaderboard lists managers by total points and shows gameweeks played. You can inspect another manager's squad and player points. Each manager plays an independent simulated season; this is a student simulation, not a live football data service.

## From desktop to web

The web edition reuses the original Java player and club models, fixture generator, match simulator and points calculator. A separate Javalin module provides the HTTP API, and a responsive interface built with HTML, CSS and JavaScript replaces the JavaFX screens in the browser. The original-look interface recreates our desktop menu and game screens using the same football and stadium backgrounds, large white buttons and familiar navigation, adapted for both desktop browsers and mobile screens.

Browser sessions keep managers signed in separately. Passwords are stored as bcrypt hashes, and each account has its own SQLite-backed season. Lineups lock after the first gameweek, and each round is saved in one transaction. A season lasts 38 gameweeks; resetting clears only that manager's results and points, keeping the account and lineup.

## Deployment

The application runs in Docker on Ubuntu at **fantasyfut.antwi.dk**. Caddy provides HTTPS and routes requests to the app. SQLite data persists in a Docker volume, so accounts and results survive container recreation. The desktop database and old accounts are not imported.

The automated tests cover account and team validation, separate user seasons, resets, score aggregation, the 38-gameweek limit, persistence, browser sessions and CSRF protection.

**Technologies:** Java 21 · Javalin · SQLite · HTML/CSS/JavaScript · Maven · JUnit · Docker · Caddy

[Back to projects]({{< relref "/projects" >}})
