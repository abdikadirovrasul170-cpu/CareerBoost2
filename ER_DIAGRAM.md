# CareerBoost ER-диаграммасы

```mermaid
erDiagram
    USERS ||--o| CANDIDATE_PROFILES : has
    COMPANIES ||--o{ JOBS : publishes
    CANDIDATE_PROFILES ||--o{ APPLICATIONS : submits
    JOBS ||--o{ APPLICATIONS : receives
    CANDIDATE_PROFILES ||--o{ CANDIDATE_SKILLS : has
    SKILLS ||--o{ CANDIDATE_SKILLS : assigned
    JOBS ||--o{ JOB_SKILLS : requires
    SKILLS ||--o{ JOB_SKILLS : required

    USERS {
        BIGINT id PK
        VARCHAR full_name
        VARCHAR email UK
        VARCHAR password_hash
        VARCHAR role
    }
    CANDIDATE_PROFILES {
        BIGINT id PK
        BIGINT user_id FK
        VARCHAR specialty
        TEXT bio
    }
    COMPANIES {
        BIGINT id PK
        VARCHAR name
        TEXT description
        VARCHAR website
    }
    JOBS {
        BIGINT id PK
        BIGINT company_id FK
        VARCHAR title
        TEXT description
        DECIMAL salary
        VARCHAR status
    }
    SKILLS {
        BIGINT id PK
        VARCHAR name UK
    }
    CANDIDATE_SKILLS {
        BIGINT candidate_id PK,FK
        BIGINT skill_id PK,FK
    }
    JOB_SKILLS {
        BIGINT job_id PK,FK
        BIGINT skill_id PK,FK
    }
    APPLICATIONS {
        BIGINT id PK
        BIGINT candidate_id FK
        BIGINT job_id FK
        VARCHAR status
        TIMESTAMP applied_at
    }
```

## Байланыстар
- `companies` 1:N `jobs` — бір компания бірнеше вакансия жариялайды.
- `candidate_profiles` 1:N `applications` — бір кандидат бірнеше өтінім жібере алады.
- `jobs` 1:N `applications` — бір вакансияға бірнеше өтінім түсе алады.
- `candidate_profiles` M:N `skills` — кандидатта бірнеше дағды, бір дағды бірнеше кандидатта болуы мүмкін.
- `jobs` M:N `skills` — вакансия бірнеше дағды талап етеді, бір дағды бірнеше вакансияда кездеседі.
- `users` 1:0..1 `candidate_profiles` — пайдаланушының кандидат профилі болуы мүмкін.
