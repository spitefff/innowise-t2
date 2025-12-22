package com.putkov.st.parser;

import com.putkov.st.composite.TextComposite;
import com.putkov.st.composite.TextType;

public class SentenceHandler extends AbstractTextHandler {

    @Override
    public TextComposite handle(String sentenceText) {
        TextComposite sentenceComposite = new TextComposite(TextType.SENTENCE);

        String[] lexemes = sentenceText.trim().split("\\s+");

        for (String lexeme : lexemes) {
            TextComposite lexemeComposite = new TextComposite(TextType.LEXEME);

            if (nextHandler != null) {
                lexemeComposite = nextHandler.handle(lexeme);
            }

            sentenceComposite.add(lexemeComposite);
        }

        return sentenceComposite;
    }
}
