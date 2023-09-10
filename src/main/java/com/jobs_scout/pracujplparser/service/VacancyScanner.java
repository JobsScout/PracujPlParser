package com.jobs_scout.pracujplparser.service;

import com.jobs_scout.pracujplparser.entity.Vacancy;

import java.util.List;

public interface VacancyScanner {
    List<Vacancy> scanForVacancy(String vacancyTag);
}
