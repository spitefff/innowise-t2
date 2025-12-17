package com.putkov.parser;

import com.putkov.composite.TextComposite;

public abstract class AbstractTextHandler {
    protected AbstractTextHandler nextHandler;

    public void setNextHandler(AbstractTextHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
    public abstract TextComposite handle(String text);
}
