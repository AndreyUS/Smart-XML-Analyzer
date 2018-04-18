package com.andrewusanin;

import com.andrewusanin.pojo.MatchResult;
import com.andrewusanin.strategy.AttributesMatchingStrategy;
import com.andrewusanin.strategy.ElementTextMatchingStrategy;
import com.andrewusanin.strategy.MatchingStrategy;
import com.andrewusanin.writer.ConsoleMessageWriter;
import com.andrewusanin.writer.MessageWriter;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SmartAnalyzerApp {

    private static final String DEFAULT_CHARSET_NAME = "UTF-8";
    private static final List<MatchingStrategy> MATCHING_STRATEGIES = Arrays.asList(new AttributesMatchingStrategy(), new ElementTextMatchingStrategy());
    private static final MessageWriter MESSAGE_WRITER = new ConsoleMessageWriter();

    public static void main(String[] args) {
        final String firstPath = args[0];
        final String secondPath = args[1];
        final String elementId = args[2];

        final FileParser fileParser = new FileParser(DEFAULT_CHARSET_NAME);

        final Document originDocument = fileParser.parse(new File(firstPath));
        final Element origin = findElementById(originDocument, elementId);

        final Document secondDocument = fileParser.parse(new File(secondPath));
        final Elements elementsByTag = findElementsByTag(secondDocument, origin.tagName());

        if (elementsByTag.isEmpty()) {
            MESSAGE_WRITER.write("Document doesn't contain any potential variant.");
            return;
        }

        final SmartElementAnalyzer smartElementAnalyzer = new SmartElementAnalyzer(origin, elementsByTag, MATCHING_STRATEGIES);
        final MatchResult potentialElement = smartElementAnalyzer.findPotentialElement();
        MESSAGE_WRITER.write(buildPath(potentialElement.getElement()));
        MESSAGE_WRITER.write("This element was chosen because:");
        MESSAGE_WRITER.write(potentialElement.getHistory().stream().collect(Collectors.joining("\n")));
    }

    private static Element findElementById(Document source, String elementId) {
        return Optional.ofNullable(source.body().getElementById(elementId))
                .orElseThrow(() -> new RuntimeException(String.format("Element with id: '%s' is not found", elementId)));
    }

    private static Elements findElementsByTag(Document source, String tag) {
        return source.body().getElementsByTag(tag);
    }

    private static String buildPath(Element element) {
        final List<String> path = element.parents()
                .stream()
                .map(SmartAnalyzerApp::formatPath)
                .collect(Collectors.toList());
        final StringBuilder builder = new StringBuilder();
        for (int i = path.size() - 1; i >= 0; i--) {
            final String s = path.get(i);
            builder.append(s).append(" > ");
        }
        builder.append(formatPath(element));
        return builder.toString();
    }

    private static String formatPath(Element element) {
        return String.format("%s[%s] ", element.tagName(), element.elementSiblingIndex());
    }
}
