package com.andrewusanin.strategy;


import org.jsoup.nodes.Element;

import java.util.List;

public class ElementTextMatchingStrategy implements MatchingStrategy {
    @Override
    public int match(Element origin, Element comparableElement, List<String> history) {
        if (origin.hasText() && comparableElement.hasText()
                && origin.text().equals(comparableElement.text())) {
            history.add("Elements text matched");
            return 1;
        }
        return 0;
    }
}
