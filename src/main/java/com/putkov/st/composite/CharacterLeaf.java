package com.putkov.st.composite;

public class CharacterLeaf implements TextComponent{
    private char character;

    public CharacterLeaf(char character){
        this.character = character;
    }

    @Override
    public String getText() {
        return String.valueOf(character);
    }

    @Override
    public int count() {
        return 1;
    }

    @Override
    public void add(TextComponent textComponent) {

    }

    @Override
    public void remove(TextComponent textComponent) {

    }
}
