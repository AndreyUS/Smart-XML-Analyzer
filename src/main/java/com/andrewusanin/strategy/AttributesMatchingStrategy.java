package com.andrewusanin.strategy;


import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AttributesMatchingStrategy implements MatchingStrategy {

    @Override
    public int match(Element origin, Element comparableElement, List<String> history) {
        final Map<String, String> originAttributes = extractAttributes(origin);
        final Map<String, String> comparableAttributes = extractAttributes(comparableElement);

        return originAttributes
                .keySet()
                .stream()
                .filter(comparableAttributes::containsKey)
                .mapToInt(key -> {
                    final String comparableValue = comparableAttributes.get(key);
                    final String originValue = originAttributes.get(key);
                    if (comparableValue.equals(originValue)) {
                        history.add(String.format("Attribute '%s' with value: '%s' matched", key, comparableValue));
                        return 1;
                    }
                    return 0;
                })
                .sum();
    }

    private Map<String, String> extractAttributes(Element element) {
        return element.attributes()
                .asList()
                .stream()
                .collect(Collectors.toMap(Attribute::getKey, Attribute::getValue));
    }
}
