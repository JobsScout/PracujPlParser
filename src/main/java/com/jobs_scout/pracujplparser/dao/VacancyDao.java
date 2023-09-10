package com.jobs_scout.pracujplparser.dao;

import com.jobs_scout.pracujplparser.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyDao extends JpaRepository<Vacancy, String> {
}
