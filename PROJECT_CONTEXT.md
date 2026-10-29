# Genfundet projektkontekst

Samlet 26. september 2026 efter computerskift. Dette er et arbejdsnotat, ikke en fuldstændig eksport af samtaler eller dokumentation for, at gamle kodeforslag er afprøvet.

## Kilder og begrænsninger

- De 50 seneste tilgængelige chats blev gennemgået efter relevante titler. Arkivopslag for både Codex og ChatGPT gav ingen resultater.
- Følgende chats blev læst via de tilgængelige samtaleuddrag:
  - “Forklar Hugo installationen” — `6a8e000f-d570-83eb-a733-7ba62292f850`.
  - “Cyber Incident Tracker പദ്ധതി” — `6a841474-c18c-83eb-acad-a33143c2d727`.
  - “Link IntelliJ Til GitHub” — `6a858047-9e20-83eb-8e89-1f0d3f8f1420`.
  - “JPA template fejlfinding” — `6aad8425-2e50-83eb-8118-b4d8ed56f4c8`.
- Værktøjet returnerede ingen fortsættelsesmarkør for disse uddrag. Det beviser ikke, at hele de oprindelige samtaler er hentet. Vedhæftede filers indhold var ikke tilgængeligt i JPA-chatten.
- Portfolio-filer og lokal Git-historik blev læst. Den gamle computer og selve Java-projektets aktuelle kode/database er ikke undersøgt.
- Historiske assistentforslag er planer eller råd, ikke automatisk brugerbeslutninger eller færdige funktioner.

## Portfolio: formål og nuværende indhold

Nana Yaw Kyei Antwis portfolio. Forsiden beskriver studier i Computer Science på Erhvervsakademi København (EK), interesse for praktisk softwareudvikling og senere cybersikkerhed samt åbenhed for studiejob og praktik.

- Hugo med Adritian Free-temaet via Go modules, aktuelt version v1.10.2 i go.mod.
- Konfigureret adresse: https://nykantwi.github.io/Portfolio/.
- Nuværende sprog er engelsk. Ældre chatforslag brugte dansk; den aktuelle konfiguration er den relevante kilde til nuværende sprog.
- Navigation: Home, About, Projects og Contact.
- Demoindhold ligger fortsat på disken, men store dele er udelukket fra generering med ignoreFiles i hugo.toml.
- SM3-CIT er det første fremhævede projekt, med egen projektside og udviklingslog.
- Den ældre fil VunerabilityManagement.md findes, men er udelukket fra generering.

## Historisk arbejde med publicering

Chatten om Hugo handlede om GitHub Pages og buildfejl: standard-Hugo manglede SCSS/SASS-understøttelse; Go-versionen i workflowet var lavere end kravet i go.mod; cacheindstillingen og forskellen mellem TOML og YAML blev også gennemgået.

Den aktuelle .github/workflows/hugo.yaml kører ved push til master og manuelt. Den installerer Hugo Extended, bygger public/ og deployer med GitHub Pages. Filen angiver Hugo 0.165.0, Go 1.27.0, Node 24.19.0 og Dart Sass 1.102.0 samt Europe/Copenhagen. Dette er aflæste filværdier, ikke en ny kontrol af versionernes tilgængelighed eller et succesfuldt deployment.

Gamle logs viste desuden deprecated-advarsler og en ukendt footer-kind. Der er ikke kørt et nyt build under denne genfinding.

## Cyber Incident Tracker: idé og foreslået retning

I chatten blev Vulnerability Management Platform sammenlignet med Cyber Incident Management System/Tracker som semesterprojekt. Førstnævnte handlede om assets og CVE'er; sidstnævnte om hændelser, brugere, evidence og tidslinje.

Assistenten foreslog navnet SecureScope og at starte med incident management, med assets/vulnerabilities som mulig senere udvidelse. Brugeren bad derefter om trinvis hjælp til at komme i gang med GitHub og IntelliJ. Portfolioens nuværende navn er SM3-CIT; præcis navneændring og forholdet mellem navnene er ikke dokumenteret i de hentede uddrag.

Foreslået roadmap i chatten, ikke dokumenteret færdig implementering:

1. GitHub, Java/Maven i IntelliJ, packages og PostgreSQL.
2. JPA/Hibernate; Incident, User og Organization; DAO og service-lag.
3. REST API, endpointtests, kommentarer og tidslinje.
4. JWT-login og rollerne ADMIN, SECURITY_ANALYST og EMPLOYEE.
5. Threat intelligence, dashboard, audit logs, tests og Docker/deployment.

Java 21 blev foreslået i roadmapet. En separat JPA-template-chat viste Java 17 i en pom.xml. Java-versionen i det aktuelle SM3-CIT-projekt er derfor ikke fastslået her.

## Senest dokumenterede SM3-CIT-status

Kilde: content/development-log/sm3-cit/2026-09-19.md, retrospektivt tilføjet i Git den 24. september.

- Java, JPA/Hibernate og PostgreSQL.
- Incident med ID, titel og beskrivelse.
- IncidentDAO til create/find/list/update/delete, med commit og rollback ved ændringer.
- CRUD-flow afprøvet manuelt i Main. Loggen beskriver ikke en færdig automatiseret testsuite.
- Læringspunkter: JPQL bruger entity-navnet Incident, mens tabellen hedder incidents; persist kontra merge; en EntityManager åbnes og lukkes pr. DAO-metode.
- Status: OPEN, INVESTIGATING, CONTAINED, RESOLVED.
- Severity: LOW, MEDIUM, HIGH, CRITICAL.
- Enumnavne gemmes som tekst, med Java-standardværdier OPEN og MEDIUM.
- Åbent punkt ved sessionens slutning: eksisterende databaserækker skulle håndteres, før de nye obligatoriske felter kunne anvendes sikkert. Java-standardværdier udfylder ikke automatisk gamle rækker.
- Dokumenterede næste trin: håndter gamle rækker, gentest CRUD med nye felter, og tilføj automatiserede DAO-tests.

## Andre relevante læringsspor

JPA-template-chatten omhandlede EntityRegistry, HibernateBaseProperties, HibernateConfig og HibernateEmfBuilder. Study var en eksempel-entity fra en guide; konkrete entities som Incident skal registreres i det faktiske projekt. Brugeren ønskede hjælp til at genbruge templaten i nye projekter.

Git-chatten omhandlede forbindelse fra IntelliJ til GitHub for et separat JPA1-projekt og håndtering af lokale konfigurationsfiler via .gitignore. Den dokumenterer ikke, at alle foreslåede oprydninger blev udført.

Cyber Incident-chatten indeholdt også et separat MovieDAO-læringsspor med JPA CRUD, TMDB-import, relationer til actors/directors/genres, titelsøgning, rating og sortering. Det er baggrund for Java/JPA-erfaring, ikke funktioner i Portfolio eller SM3-CIT.

## Lokal tidslinje

- 26. august: initial commit; GitHub Pages-konfiguration; ændring af projekteksempler; senere workflow-rettelser.
- 24. september: About Me-opdateringer, SM3-CIT-projektside og udviklingslog med første indlæg om arbejdet 19. september.
- Seneste commit ved gennemgangen: b7c90ca, Add SM3-CIT development log and first entry.

## Filer at fortsætte fra

- content/home/home.md: forside og About me.
- content/projects/sm3-cit.md: projektoversigt.
- content/development-log/sm3-cit/2026-09-19.md: dokumenteret arbejdsstatus.
- layouts/shortcodes/sm3-cit-log.html: logoversigt, nyeste først.
- archetypes/cit-log.md: skabelon til nye logindlæg, draft som standard.
- hugo.toml: site-, sprog-, navigation- og temakonfiguration.
- .github/workflows/hugo.yaml: build og publicering.

Fremtidigt arbejde bør begynde med at kontrollere den aktuelle kode og status i det relevante repository. Dette notat giver historisk kontekst og må ikke bruges som bevis for, at planlagte funktioner er implementeret.

## Opdatering 3. oktober 2026: aftalt logbogsform

Portfolio dokumenterer arbejdet i `/Users/nyaw/IdeaProjects/SM3-projekt---Cyber-Incident-Tracker`; implementeringen foregår i CIT. Fokus er backend frem til eksamen i starten af november. Logbogen har ét indlæg pr. ISO-kalenderuge, som opdateres ved yderligere arbejde samme uge. Portfolioens indhold bevares på engelsk.

CIT-kode og Git-historik er nu læst direkte. Uge 38-indlægget er omarbejdet til ugeopsummering, og uge 40 er tilføjet med User, reporterrelation, DAO-interfaces, JPQL, testopsætning og IncidentService. Uge 40 er kun opdateret til 3. oktober. Ingen særskilt implementering er dokumenteret i uge 39. Undervisningsemner må ikke automatisk beskrives som implementerede funktioner.

Testen kontrollerer aktuelt kun database/container-opstart og åben EntityManagerFactory. Ingen CRUD-/servicetests eller REST-endpoints er dokumenteret færdige. Tests blev ikke kørt under rapportarbejdet. Ovenstående ældre status er historisk; de aktuelle ugeindlæg og CIT-koden er grundlaget for nye opdateringer.
