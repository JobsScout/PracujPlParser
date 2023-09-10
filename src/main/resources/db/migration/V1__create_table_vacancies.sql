CREATE TABLE IF NOT EXISTS vacancies
(
    url            VARCHAR(255) PRIMARY KEY,
    vacancy_name   VARCHAR(255) not null,
    vacancy_levels VARCHAR(255),
    company_name   VARCHAR(255),
    locations      VARCHAR(255),
    types_of_work  VARCHAR(255),
    skills         VARCHAR(255),
    additional     VARCHAR(255),
    platform       VARCHAR(255)
);