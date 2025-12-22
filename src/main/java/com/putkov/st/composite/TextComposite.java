package com.putkov.st.composite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {

    private static final Logger logger = LogManager.getLogger();


    private static final String TEXT_DELIMITER = "\n    ";
    private static final String DEFAULT_DELIMITER = " ";

    private final List<TextComponent> components = new ArrayList<>();
    private final TextType type;

    public TextComposite(TextType type) {
        this.type = type;
    }

    @Override
    public String getText() {
        StringBuilder text = new StringBuilder();

        for (int i = 0; i < components.size(); i++) {
            TextComponent component = components.get(i);
            text.append(component.getText());

            if (i < components.size() - 1) {
                switch (type) {
                    case TEXT -> text.append(TEXT_DELIMITER);
                    case PARAGRAPH, SENTENCE, LEXEME -> text.append(DEFAULT_DELIMITER);
                }
            }
        }

        return text.toString();
    }

    public List<TextComponent> getComponents() {
        return components;
    }

    @Override
    public int count() {
        int counter = 0;
        for (TextComponent component : components) {
            counter += component.count();
        }
        return counter;
    }

    @Override
    public void add(TextComponent textComponent) {
        components.add(textComponent);
    }

    @Override
    public void remove(TextComponent textComponent) {
        components.remove(textComponent);
        logger.debug("Removed component: {}", textComponent.getText());
    }

    public TextType getType() {
        return type;
    }
}
