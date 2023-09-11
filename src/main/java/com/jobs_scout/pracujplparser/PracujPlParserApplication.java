package com.jobs_scout.pracujplparser;

import com.jobs_scout.pracujplparser.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.function.Function;

@SpringBootApplication
@RequiredArgsConstructor
@EnableJpaRepositories
public class PracujPlParserApplication {
    private final VacancyService vacancyService;

    public static void main(String[] args) {
        SpringApplication.run(PracujPlParserApplication.class, args);
    }

    @Bean
    public Function<String, String> saveFunction() {
        return vacancyService::saveAll;
    }
}