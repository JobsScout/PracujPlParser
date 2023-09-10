package com.jobs_scout.pracujplparser;

import com.jobs_scout.pracujplparser.dao.VacancyDao;
import com.jobs_scout.pracujplparser.entity.Vacancy;
import com.jobs_scout.pracujplparser.service.VacancyScanner;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication
@RequiredArgsConstructor
public class PracujPlParserApplication {
    private final ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(PracujPlParserApplication.class, args);
    }


    @Bean
    public Function<String, String> save() {
        var scanner = applicationContext.getBean(VacancyScanner.class);
        var dao = applicationContext.getBean(VacancyDao.class);
        return tag -> {
            var vacancies = scanner.scanForVacancy(tag);
            dao.saveAll(vacancies);
            return vacancies.stream()
                    .map(Vacancy::toString)
                    .collect(Collectors.joining("\n"));
        };
    }
}