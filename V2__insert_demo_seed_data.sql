-- Demo data for CareerBoost
-- Password values below are demo placeholders, not production password hashes.
-- Replace with properly BCrypt-hashed values before using authentication.

INSERT INTO users (id, full_name, email, password_hash, role) VALUES
(1, 'Aruzhan Sadykova', 'aruzhan@example.com', 'DEMO_HASH_CANDIDATE_1', 'CANDIDATE'),
(2, 'Dias Nurlanov', 'dias@example.com', 'DEMO_HASH_CANDIDATE_2', 'CANDIDATE'),
(3, 'Tech Solutions HR', 'hr@techsolutions.example', 'DEMO_HASH_EMPLOYER', 'EMPLOYER'),
(4, 'CareerBoost Admin', 'admin@careerboost.example', 'DEMO_HASH_ADMIN', 'ADMIN');

INSERT INTO candidate_profiles (id, user_id, specialty, bio) VALUES
(1, 1, 'Java Developer', 'Junior Java developer interested in backend development.'),
(2, 2, 'Frontend Developer', 'Frontend developer familiar with modern web technologies.');

INSERT INTO companies (id, name, description, website) VALUES
(1, 'Tech Solutions', 'Software development company.', 'https://example.com/tech-solutions'),
(2, 'Digital Kazakhstan', 'Digital products and IT services.', 'https://example.com/digital-kazakhstan');

INSERT INTO skills (id, name) VALUES
(1, 'Java'),
(2, 'SQL'),
(3, 'HTML'),
(4, 'CSS'),
(5, 'Testing');

INSERT INTO jobs (id, company_id, title, description, salary, status) VALUES
(1, 1, 'Java Developer', 'Develop and maintain Java backend services.', 450000.00, 'OPEN'),
(2, 2, 'Frontend Developer', 'Build accessible and responsive user interfaces.', 400000.00, 'OPEN'),
(3, 1, 'QA Engineer', 'Prepare and execute software tests.', 350000.00, 'OPEN');

INSERT INTO candidate_skills (candidate_id, skill_id) VALUES
(1, 1), (1, 2), (1, 5),
(2, 3), (2, 4);

INSERT INTO job_skills (job_id, skill_id) VALUES
(1, 1), (1, 2),
(2, 3), (2, 4),
(3, 2), (3, 5);

INSERT INTO applications (id, candidate_id, job_id, status) VALUES
(1, 1, 1, 'SUBMITTED'),
(2, 2, 2, 'REVIEWING'),
(3, 1, 3, 'SUBMITTED');

-- Keep identity sequences in sync after explicit demo IDs.
SELECT setval(pg_get_serial_sequence('users', 'id'), (SELECT MAX(id) FROM users));
SELECT setval(pg_get_serial_sequence('candidate_profiles', 'id'), (SELECT MAX(id) FROM candidate_profiles));
SELECT setval(pg_get_serial_sequence('companies', 'id'), (SELECT MAX(id) FROM companies));
SELECT setval(pg_get_serial_sequence('skills', 'id'), (SELECT MAX(id) FROM skills));
SELECT setval(pg_get_serial_sequence('jobs', 'id'), (SELECT MAX(id) FROM jobs));
SELECT setval(pg_get_serial_sequence('applications', 'id'), (SELECT MAX(id) FROM applications));
