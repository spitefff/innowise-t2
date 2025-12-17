package com.putkov.parser;

import com.putkov.composite.TextComposite;
import com.putkov.composite.TextType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphHandler extends AbstractTextHandler {
    private static final Pattern SENTENCE_PATTERN = Pattern.compile("[^.!?]+(?:[.!?]|$)");

    @Override
    public TextComposite handle(String paragraphText) {
        TextComposite paragraphComposite = new TextComposite(TextType.PARAGRAPH);
        Matcher matcher = SENTENCE_PATTERN.matcher(paragraphText);
        while (matcher.find()) {
            String sentenceText = matcher.group();
            TextComposite sentenceComposite = new TextComposite(TextType.SENTENCE);

            if (nextHandler != null) {
                sentenceComposite = nextHandler.handle(sentenceText);
            }

            paragraphComposite.add(sentenceComposite);
        }

        return paragraphComposite;
    }
}
