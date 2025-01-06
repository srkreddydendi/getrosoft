package com.getrosoft.getrosoft.util;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class LocaleValidator {

    private Set<String> locales;

    @PostConstruct
    private void init() {
        locales = Arrays.stream(Locale.getAvailableLocales())
                .map(Locale::getCountry)
                .collect(Collectors.toSet());
    }

    public boolean areValidCountryCodes(String origin, String destination) {
        return Objects.isNull(origin) || Objects.isNull(destination) || locales.contains(origin) && locales.contains(destination);
    }
}
