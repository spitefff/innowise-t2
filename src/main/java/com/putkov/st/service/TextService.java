package com.putkov.st.service;

import com.putkov.st.composite.TextComposite;

import java.util.List;

public interface TextService {
    int findMaxSentencesWithSameWords(TextComposite textComposite);
    List<TextComposite> sortSentencesByLexemeCount(TextComposite text);
    void swapFirstAndLastLexeme(TextComposite text);
}
