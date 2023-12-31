package com.jobs_scout.pracujplparser.service.impl;

import com.jobs_scout.pracujplparser.service.UrlService;
import org.springframework.stereotype.Service;

@Service
public class UrlServiceImpl implements UrlService {
    @Override
    public String getUrl(String tag) {
        return "https://it.pracuj.pl/praca/" + tagConverter(tag);
    }

    private String tagConverter(String tag) {
        return String.join("%20", tag.split(" ")) + ";kw";
    }
}
