package com.putkov.st.parser;

import com.putkov.st.composite.TextComposite;
import com.putkov.st.composite.TextType;

public class TextHandler extends AbstractTextHandler {

    @Override
    public TextComposite handle(String text) {
        TextComposite textComposite = new TextComposite(TextType.TEXT);


        String[] paragraphs = text.split("\\n+");

        for (String paragraph : paragraphs) {
            TextComposite paragraphComposite = new TextComposite(TextType.PARAGRAPH);
            if (nextHandler != null) {
                paragraphComposite = nextHandler.handle(paragraph);
            }

            textComposite.add(paragraphComposite);
        }

        return textComposite;
    }
}
