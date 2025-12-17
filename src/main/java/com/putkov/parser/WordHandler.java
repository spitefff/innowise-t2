package com.putkov.parser;

import com.putkov.composite.TextComposite;
import com.putkov.composite.TextType;
import com.putkov.composite.CharacterLeaf;

public class WordHandler extends AbstractTextHandler {

    @Override
    public TextComposite handle(String wordText) {
        TextComposite wordComposite = new TextComposite(TextType.WORD);

        for (char character : wordText.toCharArray()) {
            wordComposite.add(new CharacterLeaf(character));
        }

        return wordComposite;
    }
}
