package com.jobs_scout.pracujplparser.entity;

import com.jobs_scout.pracujplparser.enums.Platform;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "vacancies")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Vacancy {
    @Id
    private String url;
    private String vacancyName;
    private String vacancyLevels;
    private String companyName;
    private String locations;
    private String typesOfWork;
    private String skills;
    private String additional;
    private Platform platform;
}