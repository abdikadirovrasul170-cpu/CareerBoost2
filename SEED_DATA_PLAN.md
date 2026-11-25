# Demo Seed Data жоспары

`V2__insert_demo_seed_data.sql` миграциясы демонстрацияға арналған деректерді енгізеді.

| Кесте | Жоспарланған саны | Мазмұны |
|---|---:|---|
| users | 4 | 2 кандидат, 1 жұмыс беруші, 1 әкімші |
| candidate_profiles | 2 | Java Developer және Frontend Developer |
| companies | 2 | Tech Solutions және Digital Kazakhstan |
| jobs | 3 | Java Developer, Frontend Developer, QA Engineer |
| skills | 5 | Java, SQL, HTML, CSS, Testing |
| candidate_skills | 5 | Кандидаттардың дағдылары |
| job_skills | 6 | Вакансияларға қажетті дағдылар |
| applications | 3 | Әртүрлі мәртебедегі өтінімдер |

Демо сценарийі:
1. Кандидат жүйеге тіркелген.
2. Профильге Java және SQL дағдылары қосылған.
3. Компания Java Developer вакансиясын жариялаған.
4. Жүйе кандидаттың дағдыларына сәйкес вакансияны көрсете алады.
5. Кандидат өтінім жібереді; өтінім мәртебесі `SUBMITTED`.
