---
title: "SM3-CIT"
description: "Cyber Incident Tracker — backend progress and weekly development summaries."
draft: false
---

## Cyber Incident Tracker

SM3-CIT is my third-semester Cyber Incident Tracker project. I am building the backend in Java, using JPA/Hibernate and PostgreSQL. This portfolio documents the implementation and its connection to the course through the backend exam in early November 2026.

## Current implementation

As of 3 October, the project contains incident CRUD, status and severity enums, users and a required relationship between incidents and their reporters. DAO interfaces define persistence operations, and JPQL supports finding users by email and incidents by reporter. An incident service adds validation and application operations on top of the DAOs.

The automated test setup uses JUnit and a PostgreSQL Testcontainer. Its current test checks database startup; CRUD and service behaviour still need automated coverage. No test run was performed for this report.

REST endpoints, external API integration, concurrency features, authentication and deployment are not yet documented as implemented in the inspected CIT source. These remain later backend work.

## Weekly development log

Each entry collects the whole week's implemented work, technical choices, testing progress and next steps. Further work in the same week is added to that entry. Weeks follow the actual project history, with a separate explanation of their connection to the teaching topics.

The available history documents work in weeks 38 and 40. There is no separately evidenced implementation for week 39, so no progress entry has been invented for that week. The week 40 summary is current through 3 October.

{{< sm3-cit-log >}}

[Back to my portfolio]({{< relref "/" >}}#projects)
