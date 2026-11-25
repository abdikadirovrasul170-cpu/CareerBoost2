# CareerBoost — Web Resume Builder

CareerBoost is the browser-based resume builder module for the existing CareerBoost repository. It lives in `webapp/` so the original Java coursework/demo is preserved.

## Features in this first web milestone

- Responsive landing page and resume editor.
- Live A4 resume preview while typing.
- Modern and Classic resume themes (Strategy pattern in Java).
- Resume CRUD REST API: list, create, read, update and delete.
- H2 file database; records persist in `webapp/data/`.
- Swagger UI at `/swagger-ui.html`.
- Print dialog can save the resume as PDF.

## Requirements

- Java 17 or newer
- Maven 3.6.3 or newer

## Run in VS Code terminal

```powershell
cd webapp
mvn spring-boot:run
```

Open http://localhost:8080 in your browser.

## API

- `GET /api/resumes`
- `GET /api/resumes/{id}`
- `POST /api/resumes`
- `PUT /api/resumes/{id}`
- `DELETE /api/resumes/{id}`

Swagger: http://localhost:8080/swagger-ui.html  
H2 console: http://localhost:8080/h2-console  
JDBC URL: `jdbc:h2:file:./data/careerboost` · user: `sa` · password: empty

## Architecture note

The existing console MVP is intentionally left untouched. This module is the first browser-based increment. Authentication with persistent user roles, ownership-based access control, PostgreSQL migration, automated tests and CI are follow-up milestones before calling the coursework acceptance criteria complete.

## Design pattern

`ResumeTemplateStrategy` is the Strategy interface. `ModernResumeTemplate` and `ClassicResumeTemplate` provide interchangeable resume theme strategies; `ResumeController` selects the requested strategy by template name.
