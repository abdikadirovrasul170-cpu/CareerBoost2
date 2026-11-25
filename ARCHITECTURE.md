# CareerBoost architecture

## Architectural style and layers

The project uses a layered architecture. Dependencies should point inward toward the domain/business rules.

1. **Presentation layer** — CLI/API controllers; receives requests and returns responses.
2. **Application layer** — use cases and orchestration (registration, profile editing, resume creation, applications).
3. **Domain layer** — core entities and business rules (User, Profile, Skill, Resume, Vacancy, Application).
4. **Infrastructure layer** — PostgreSQL repositories, external integrations, configuration and logging.

## C4 Container diagram

```mermaid
C4Container
    Person(user, "Job seeker", "Uses CareerBoost to manage a profile and apply for vacancies")
    System_Boundary(cb, "CareerBoost") {
        Container(app, "CareerBoost Java application", "Java 17 / Maven", "Application and domain logic")
        ContainerDb(db, "PostgreSQL", "PostgreSQL 16", "Users, profiles, skills, resumes, vacancies and applications")
    }
    Rel(user, app, "Uses")
    Rel(app, db, "Reads/writes data", "SQL")
```

## Runtime

Docker Compose starts the Java application container and PostgreSQL database. The initial schema is mounted into PostgreSQL's initialization directory and is applied when the database volume is first created.
