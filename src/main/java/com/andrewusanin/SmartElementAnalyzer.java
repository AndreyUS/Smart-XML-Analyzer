package com.andrewusanin;


import com.andrewusanin.pojo.MatchResult;
import com.andrewusanin.strategy.MatchingStrategy;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SmartElementAnalyzer {

    private final Element origin;
    private final Elements potentialElements;
    private final List<MatchingStrategy> strategies;

    public SmartElementAnalyzer(Element origin, Elements potentialElements, List<MatchingStrategy> strategies) {
        this.origin = origin;
        this.potentialElements = potentialElements;
        this.strategies = strategies;
    }

    /**
     * Tries find potential element from given elements with specific strategies.
     * @return {@link MatchResult} with potential element
     */
    public MatchResult findPotentialElement() {

        final List<MatchResult> matchResults = new ArrayList<>(potentialElements.size());

        for (Element potential : potentialElements) {
            final List<String> history = new ArrayList<>();
            final int matchingPoints = strategies
                    .stream()
                    .mapToInt(strategy -> strategy.match(origin, potential, history))
                    .sum();
            matchResults.add(new MatchResult(potential, matchingPoints, history));
        }

        matchResults.sort(Comparator.comparing(MatchResult::getPoints).reversed());
        return matchResults.get(0);
    }
}
