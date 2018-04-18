package com.andrewusanin.strategy;


import org.jsoup.nodes.Element;

import java.util.List;

public interface MatchingStrategy {

    /**
     * Makes match for given elements with specific logic.
     * @param origin, original element
     * @param comparableElement, match with original
     * @param history, history of matching
     * @return points of matching
     */
    int match(Element origin, Element comparableElement, List<String> history);
}
