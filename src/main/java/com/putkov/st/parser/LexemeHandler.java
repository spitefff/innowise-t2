package com.putkov.st.parser;

import com.putkov.st.composite.TextComposite;
import com.putkov.st.composite.TextType;
import com.putkov.st.composite.CharacterLeaf;

public class LexemeHandler extends AbstractTextHandler {

    @Override
    public TextComposite handle(String lexemeText) {
        TextComposite lexemeComposite = new TextComposite(TextType.LEXEME);

        StringBuilder wordBuilder = new StringBuilder();

        for (char character : lexemeText.toCharArray()) {
            if (isPunctuation(character)) {
                if (wordBuilder.length() > 0) {
                    TextComposite wordComposite = new TextComposite(TextType.WORD);
                    if (nextHandler != null) {
                        wordComposite = nextHandler.handle(wordBuilder.toString());
                    }
                    lexemeComposite.add(wordComposite);
                    wordBuilder.setLength(0);
                }
                lexemeComposite.add(new CharacterLeaf(character));
            } else {
                wordBuilder.append(character);
            }
        }

        if (wordBuilder.length() > 0) {
            TextComposite wordComposite = new TextComposite(TextType.WORD);
            if (nextHandler != null) {
                wordComposite = nextHandler.handle(wordBuilder.toString());
            }
            lexemeComposite.add(wordComposite);
        }

        return lexemeComposite;
    }

    private boolean isPunctuation(char c) {
        return ",.!?;'-\"".indexOf(c) >= 0;
    }
}
