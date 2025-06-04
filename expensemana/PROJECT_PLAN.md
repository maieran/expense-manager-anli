
# ✅ Expense Intelligence Platform – Lernorientierter MVP Plan (10 Wochen)

Ein strukturierter Lernplan für dein Portfolio-Projekt mit wachsender Komplexität – von Spring Boot über Docker bis Kubernetes und CI/CD.

---------------------------------------------------------------------------------------------------------------------------------------------------------

## 📅 Woche 1 – Auth & Projektstart

- [✅] Monorepo `my-expense-platform` anlegen
- [✅] Spring Boot App `apps/expense-core` initialisieren
- [ ] `User` + `Role` Entity erstellen
- [ ] JWT-Authentifizierung implementieren
- [ ] `/register`, `/login` Endpunkte
- [ ] Auth-Middleware für geschützte Endpunkte (`@PreAuthorize`)
- [ ] Swagger/OpenAPI aktivieren
- [ ] Unit Tests für Auth schreiben


### REQUIREMENTS - WOCHE 1 
✅ 1. Monorepo expense-manager-anli anlegenMonorepo expense-manager-anli anlegen
    ZIEL:
    ### Git-Repository initialisieren
    ### Projektstruktur nach apps/, docs/, design/, k8s/, scripts/ usw.
        === DETAILS ===
        => [✅] Lokales Verzeichnis mit git init
        => [✅] .gitignore für Java, Python, Docker, k8s, GitHub CI/CD, Spring Boot, MacOs, Windows
        => [✅] Erste Commit-Message: initial monorepo structure

✅ 2. Spring Boot App apps/expense-core initialisieren
    ZIEL:
    ### Spring Boot-Projekt mit Maven aufsetzen
    === DETAILS ===
        => [✅] Java 17
        => [✅] Maven
            ==> [✅] apps/expense-core als Maven-Projekt anlegen
        => [✅] Abhängigkeiten: spring-boot-starter-web, spring-boot-starter-security, spring-boot-starter-data-jpa, jjwt, h2 oder mysql
        => [✅] application.yml oder .properties einrichten
        => [✅] REST Controller testen mit /hello


 ✅ 3. User + Role Entity erstellen
    ZIEL:
    ### Benutzer und Rollenmodell anlegen
    === DETAILS ===
        => [ ] Implementation:
            ==> [ ] Entities + JPA-Annotations definieren
            ==> [ ] UserRepository + RoleRepository
            ==> [ ] Service für Registrierung + Speicherung

 ✅ 4. JWT-Authentifizierung implementieren
    ZIEL:
    ### Nutzer sollen sich mit /login anmelden können
    ### Nach Login erhalten sie einen JWT-Token
    ### Verwende io.jsonwebtoken:jjwt
    ### Token enthält username, roles, exp
    === DETAILS ===
    => [ ] Implementation:
        ==> [ ] LoginController: /login
        ==> [ ] JWT-Generator + JWT-Validator Service
        ==> [ ] Token im Header Authorization: Bearer <token> zurückgeben


 ✅ 5. /register, /login Endpunkte
    ZIEL:
    ### Benutzer können sich registrieren und einloggen
    === DETAILS ===
    => [ ] Implementation: 
        ==> [ ] DTOs erstellen
        ==> [ ] Registrierung speichert User + Rolle "USER"
        ==> [ ] Login prüft Passwort (BCrypt) + gibt JWT zurück

 ✅ 6. Auth-Middleware für geschützte Endpunkte
    ZIEL:
    ### Nur authentifizierte Nutzer dürfen auf geschützte Routen zugreifen
    === DETAILS ===
    => [ ] Verwende OncePerRequestFilter oder AuthenticationProvider
        ==> [ ] JWT auslesen, validieren
        ==> [ ] SecurityConfig.java: Zugriff auf /api/** nur mit Auth
        ==> [ ] @PreAuthorize("hasRole('USER')") einsetzen

 ✅ 7. Swagger/OpenAPI aktivieren
    ZIEL:
    ### REST-API dokumentieren & testen
    === DETAILS ===
    => [ ] Implementation:
        ==> [ ] springdoc-openapi-ui in pom.xml aufnehmen
        ==> [ ]  Swagger unter /swagger-ui.html erreichbar machen



 📦📦📦 Output dieser Woche 📦📦📦
    📦 Authentifizierung (JWT-basiert)
    📦 Register/Login-Flow
    📦 Swagger zum Testen
    📦 Monorepo-Grundlage + saubere Paketstruktur
    📦 Code in GitHub + CI-Testlauf (optional)
    
---------------------------------------------------------------------------------------------------------------------------------------------------------

## 📅 Woche 2 – Expense Core & CRUD

- [ ] `ExpenseEntry`, `Product`, `Reoccurrence` Entities
- [ ] POST `/api/expenses` (authentifiziert)
- [ ] GET `/api/expenses/{userId}?range=daily|monthly`
- [ ] Filter & Aggregation (Summen pro Kategorie)
- [ ] Error Handling mit `@ControllerAdvice`
- [ ] Integrationstest für Einträge & Abfragen

---------------------------------------------------------------------------------------------------------------------------------------------------------

## 📅 Woche 3 – Badge Service (intern)

- [ ] Entity: `UserBadge`, Enum `BadgeType`
- [ ] Logik: Erste Ausgabe
- [ ] Logik: 2-Tage-Streak
- [ ] Logik: 4-Tage-Streak
- [ ] Logik: 7-Tage-Streak
- [ ] `BadgeService.evaluate(userId)`
- [ ] Trigger bei neuem ExpenseEntry
- [ ] GET `/api/badges/{userId}`
- [ ] Optional: Status-Badge im Dashboard anzeigen

---------------------------------------------------------------------------------------------------------------------------------------------------------

## 📅 Woche 4 – AI Agent (Flask)

- [ ] Projekt `apps/ai-agent` erstellen
- [ ] Flask App + Route `/analyze`
- [ ] AI-Agent ruft Spring Boot `/api/expenses/{userId}` auf
- [ ] Analysiert Top-Kategorie → Rückgabe als Empfehlung
- [ ] Rückgabe als JSON z. B. `{ recommendation: "Reduziere X" }`
- [ ] Logging & Tests

---------------------------------------------------------------------------------------------------------------------------------------------------------

## 📅 Woche 5 – Docker & Compose

- [ ] Dockerfile für `expense-core`
- [ ] Dockerfile für `ai-agent`
- [ ] `docker-compose.yml` mit gemeinsamen Netzwerk
- [ ] `.env`-Dateien vorbereiten
- [ ] Compose up/down lokal testen
- [ ] Health Checks und Logs prüfen
- [ ] Startskript oder Makefile hinzufügen

---------------------------------------------------------------------------------------------------------------------------------------------------------

## 📅 Woche 6 – GitHub CI/CD (Teil 1: Build & Test)

- [ ] `.github/workflows/ci.yml` anlegen
- [ ] Java-Setup mit Maven Build
- [ ] Testschritt einfügen (`mvn test`)
- [ ] Status-Badge in `README.md` integrieren
- [ ] Branch Protection Regeln setzen (optional)
- [ ] Testabdeckung prüfen

---------------------------------------------------------------------------------------------------------------------------------------------------------

## 📅 Woche 7 – Kubernetes Setup (Minikube)

- [ ] Minikube oder k3d installieren
- [ ] Deployment YAML für `expense-core`
- [ ] Service YAML für REST-Zugriff
- [ ] Ingress Controller (z. B. NGINX oder Traefik)
- [ ] Logs & Pod Status via `kubectl` prüfen
- [ ] Namespace `expense-dev` anlegen
- [ ] README um K8s-Hinweise erweitern

---------------------------------------------------------------------------------------------------------------------------------------------------------

## 📅 Woche 8 – GitHub CI/CD (Teil 2: Deployment)

- [ ] CI erweitern: Docker Build + Push zu DockerHub/GHCR
- [ ] GitHub Secrets (`DOCKER_USER`, `DOCKER_TOKEN`) setzen
- [ ] (Optional) Remote Deployment via SSH (DigitalOcean/Hetzner)
- [ ] Live-Demo-URL sichern (z. B. Subdomain)
- [ ] `k8s/` Ordner mit YAMLs dokumentieren

---

## 📅 Woche 9 – Design & UI Übergabe

- [ ] Finales Figma-Design reviewen
- [ ] Komponenten + API-Verträge validieren
- [ ] HTML-Clickdummy erstellen oder React-Starter einrichten
- [ ] Struktur für das UI-Modul vorbereiten
- [ ] Farbpalette & Fonts übernehmen
- [ ] Optional: Design-Doku im `design/` Ordner speichern

---------------------------------------------------------------------------------------------------------------------------------------------------------

## 📅 Woche 10 – Feinschliff & Portfolio-Demo

- [ ] Technisches README aktualisieren
- [ ] Screenshot(s) & GIFs für GitHub einbinden
- [ ] Video-Demo aufnehmen (OBS Studio)
- [ ] Projekt auf LinkedIn posten
- [ ] Optional: Veröffentlichung als GitHub Page
- [ ] Tech Stack & Lernreflexion dokumentieren

---------------------------------------------------------------------------------------------------------------------------------------------------------

## 📘 Bonus: Empfehlungen für später

- [ ] Kafka-Integration als Event-System zwischen Services
- [ ] Prometheus + Grafana für Metriken
- [ ] ArgoCD für GitOps Deployment
- [ ] Elasticsearch für Suche
- [ ] Feature Flags mit LaunchDarkly o. ä.
