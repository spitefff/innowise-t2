package com.putkov.st.composite;

public interface TextComponent {
    String getText();
    int count();
    void add(TextComponent textComponent);
    void remove(TextComponent textComponent);
}
