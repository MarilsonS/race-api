# race-api

Réalisé par Marilson SOUZA DE JESUS FILHO

---

## Lancer le projet

### 1. Démarrer la base de données

Lancer Docker Desktop, puis :

```
docker compose up -d
```

### 2. Lancer l'application

```
mvn spring-boot:run
```

API disponible sur http://localhost:8080

### 3. Tester avec Postman

Importer le fichier postman-export.json dans Postman pour avoir toutes les requêtes prêtes.

---

## Endpoints implémentés

### Coureurs

```
Get all runners
GET    /runners

Get runner by ID
GET    /runners/{id}

Create runner
POST   /runners

Update runner
PUT    /runners/{id}

Delete runner
DELETE /runners/{id}

Get all races of a runner
GET    /runners/{id}/races
```

### Courses

```
Get all races
GET  /races

Get all races (location filtered)
GET  /races?location={location}

Get race by ID
GET  /races/{id}

Create race
POST /races

Update race
PUT  /races/{id}

Count participants of a race
GET  /races/{id}/participants/count
```

### Inscriptions

```
Register runner to race
POST /races/{raceId}/registrations

Get all runners of a race
GET  /races/{raceId}/registrations
```
