# Demo seed data plan

For a repeatable classroom demo, insert data in this order so foreign keys are valid:

1. **Users** — 3 accounts: `aliya@example.com`, `dias@example.com`, `arman@example.com` (use clearly fake password hashes).
2. **Profiles** — one profile for each user, with names and short bios.
3. **Skills** — Java, SQL, Docker, Git, Communication.
4. **User_Skills** — assign 2–3 skills to each user; include different proficiency values.
5. **Resumes** — create 1–2 resumes per user.
6. **Vacancies** — add 3 sample roles: Junior Java Developer, QA Intern, Backend Developer.
7. **Applications** — create 4–5 applications across users and vacancies with statuses `SUBMITTED`, `REVIEWING`, and `ACCEPTED`.

All records are fictional. Keep seed inserts in a separate script such as `database/seed/V1__demo_data.sql`; do not use real personal data or production credentials.
