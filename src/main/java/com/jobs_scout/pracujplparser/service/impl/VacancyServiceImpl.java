package com.jobs_scout.pracujplparser.service.impl;

import com.jobs_scout.pracujplparser.dao.VacancyDao;
import com.jobs_scout.pracujplparser.service.VacancyScanner;
import com.jobs_scout.pracujplparser.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyDao dao;
    private final VacancyScanner scanner;

    @Override
    public String saveAll(String tag) {
        var vacancies = scanner.scanForVacancy(tag);
        return "The number of vacancies is saved in the database: " + dao.saveAll(vacancies).size();
    }
}
