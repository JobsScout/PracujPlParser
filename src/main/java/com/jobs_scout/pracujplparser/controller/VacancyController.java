package com.jobs_scout.pracujplparser.controller;

import com.jobs_scout.pracujplparser.dao.VacancyDao;
import com.jobs_scout.pracujplparser.entity.Vacancy;
import com.jobs_scout.pracujplparser.service.VacancyScanner;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class VacancyController {
    private final VacancyScanner scanner;
    private final VacancyDao vacancyDao;

    @GetMapping("scan")
    public List<Vacancy> scanner(@RequestParam String tag) {
        var vacancy = scanner.scanForVacancy(tag);
        vacancyDao.saveAll(vacancy);
        return vacancy;
    }
}
