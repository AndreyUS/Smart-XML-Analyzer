package com.andrewusanin.pojo;


import org.jsoup.nodes.Element;

import java.util.List;

public class MatchResult {
    private final Element element;
    private final int points;
    private final List<String> history;

    public MatchResult(Element element, int points, List<String> history) {
        this.element = element;
        this.points = points;
        this.history = history;
    }

    public int getPoints() {
        return points;
    }

    public Element getElement() {
        return element;
    }

    public List<String> getHistory() {
        return history;
    }
}
