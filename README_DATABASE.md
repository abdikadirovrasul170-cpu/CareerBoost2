# CareerBoost — Database Design

Бұл пакет CareerBoost жобасының дерекқорды жобалау тапсырмасына арналған.

## Құрамы
- `docs/ER_DIAGRAM.md` — ER-диаграмма (Mermaid).
- `docs/SEED_DATA_PLAN.md` — демо деректер жоспары.
- `src/main/resources/db/migration/V1__create_initial_schema.sql` — бірінші миграция.
- `src/main/resources/db/migration/V2__insert_demo_seed_data.sql` — демо деректерді енгізу миграциясы.
- `pom.xml` — PostgreSQL және Flyway тәуелділіктерінің үлгісі.

## Дерекқор
PostgreSQL 15+ қолданылады. Миграциялар Flyway арқылы орындалады.
Maven командаларын орындау үшін Java 25 LTS JDK қажет.

## Іске қосу
1. PostgreSQL ішінде `careerboost` атты дерекқор жасаңыз.
2. `pom.xml` ішіндегі `flyway-maven-plugin` конфигурациясындағы URL, username және password мәндерін өз ортаңызға сәйкестендіріңіз.
3. Терминалда жоба түбірінде орындаңыз:

   `mvn flyway:migrate`

Ескерту: PostgreSQL сервері іске қосулы болуы және Maven орнатылған болуы керек.
Құпиясөзді GitHub-қа жарияламаңыз. Нақты жобада оны environment variable арқылы берген дұрыс.
psql --version