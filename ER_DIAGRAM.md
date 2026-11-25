# CareerBoost ER-диаграммасы



erDiagram
    USERS ||--|| PROFILES : has
    USERS ||--o{ RESUMES : creates
    USERS ||--o{ APPLICATIONS : submits
    USERS ||--o{ USER_SKILLS : has
    SKILLS ||--o{ USER_SKILLS : includes
    USERS ||--o{ VACANCIES : publishes
    VACANCIES ||--o{ APPLICATIONS : receives

    USERS {
        int id PK
        string full_name
        string email UK
        string password
    }

    PROFILES {
        int id PK
        int user_id FK
        string education
        string experience
        string phone
    }

    SKILLS {
        int id PK
        string skill_name
    }

    USER_SKILLS {
        int id PK
        int user_id FK
        int skill_id FK
        string skill_level
    }

    RESUMES {
        int id PK
        int user_id FK
        string title
        text personal_info
        text experience
    }

    VACANCIES {
        int id PK
        int employer_id FK
        string title
        string company_name
        text description
    }

    APPLICATIONS {
        int id PK
        int user_id FK
        int vacancy_id FK
        string status
        datetime applied_at
    }


## Байланыстар
- `companies` 1:N `jobs` — бір компания бірнеше вакансия жариялайды.
- `candidate_profiles` 1:N `applications` — бір кандидат бірнеше өтінім жібере алады.
- `jobs` 1:N `applications` — бір вакансияға бірнеше өтінім түсе алады.
- `candidate_profiles` M:N `skills` — кандидатта бірнеше дағды, бір дағды бірнеше кандидатта болуы мүмкін.
- `jobs` M:N `skills` — вакансия бірнеше дағды талап етеді, бір дағды бірнеше вакансияда кездеседі.
- `users` 1:0..1 `candidate_profiles` — пайдаланушының кандидат профилі болуы мүмкін.
