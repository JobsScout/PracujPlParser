package com.jobs_scout.pracujplparser.service.impl;

import com.jobs_scout.pracujplparser.entity.Vacancy;
import com.jobs_scout.pracujplparser.enums.Platform;
import com.jobs_scout.pracujplparser.service.UrlService;
import com.jobs_scout.pracujplparser.service.VacancyScanner;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VacancyScannerImpl implements VacancyScanner {
    private final UrlService urlService;

    @Override
    @SneakyThrows
    public List<Vacancy> scanForVacancy(String vacancyTag) {
        List<Vacancy> vacancies = new LinkedList<>();
        var document = Jsoup.connect(urlService.getUrl(vacancyTag)).get();

        var links = document.select("div[class=\"listing-it_c1ixxz5n\"]");

        for (Element link : links) {
            var elements = link.getAllElements();

            var linkAndVacancyName = getLinksAndVacancyNames(elements.select("a[class=\"listing-it_o1bdr2ew listing-it_n194fgoq\"]"));
            var companyName = getByElement(elements.select("h4[class=\"listing-it_e7j2err size-caption listing-it_t1rst47b\"]"));
            var location = getByElement(elements.select("h5[class=\"listing-it_r3tty89 size-caption listing-it_t1rst47b\"]"));
            var skills = getListOfStringByElement(elements.select("span.listing-it_ty5jbik"));
            var additional = getListOfStringByElement(elements.select("li[class=\"mobile-hidden listing-it_i1o7na83\"]"));
            var another_place = getByElement(elements.select("li[class=\"listing-it_i1o7na83\"]"));

            Optional.ofNullable(linkAndVacancyName)
                    .filter(containsTag(vacancyTag.toUpperCase()))
                    .ifPresent(vacancy -> vacancies.add(new Vacancy(vacancy.getKey(),
                            vacancy.getValue(),
                            additional.remove(0),
                            companyName,
                            location,
                            another_place,
                            String.join(", ", skills),
                            String.join(", ", additional),
                            Platform.PRACUJ_PL)));
        }

        return vacancies;
    }

    private Predicate<? super Map.Entry<String, String>> containsTag(String vacancyTag) {
        return stringStringEntry -> Arrays.stream(vacancyTag.split(" "))
                .allMatch(word -> stringStringEntry.getValue()
                        .toUpperCase()
                        .contains(word));
    }

    private List<String> getListOfStringByElement(Elements elements) {
        return elements.stream()
                .map(Element::text)
                .collect(Collectors.toList());
    }

    private String getByElement(Elements elements) {
        return elements.stream()
                .map(Element::text)
                .findFirst()
                .orElse("");
    }

    private Map.Entry<String, String> getLinksAndVacancyNames(Elements elements) {
        var iterator = elements.stream()
                .collect(Collectors.toMap(x -> x.attr("href"), Element::text))
                .entrySet()
                .iterator();
        return iterator.hasNext() ? iterator.next() : null;
    }
}