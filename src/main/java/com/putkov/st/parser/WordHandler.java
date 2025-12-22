package com.putkov.st.parser;

import com.putkov.st.composite.TextComposite;
import com.putkov.st.composite.TextType;
import com.putkov.st.composite.CharacterLeaf;

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
