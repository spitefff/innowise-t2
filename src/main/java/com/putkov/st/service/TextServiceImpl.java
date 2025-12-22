package com.putkov.st.service;

import com.putkov.st.composite.TextComposite;
import com.putkov.st.composite.TextComponent;
import com.putkov.st.composite.TextType;

import java.util.*;

public class TextServiceImpl implements TextService{
    public int findMaxSentencesWithSameWords(TextComposite text) {
        List<TextComposite> sentences = getAllSentences(text);

        int maxCount = 0;

        for (int i = 0; i < sentences.size(); i++) {
            Set<String> wordsI = extractWords(sentences.get(i));
            int count = 1;

            for (int j = i + 1; j < sentences.size(); j++) {
                Set<String> wordsJ = extractWords(sentences.get(j));

                Set<String> intersection = new HashSet<>(wordsI);
                intersection.retainAll(wordsJ);

                if (!intersection.isEmpty()) {
                    count++;
                }
            }

            maxCount = Math.max(maxCount, count);
        }

        return maxCount;
    }

    private List<TextComposite> getAllSentences(TextComposite text) {
        List<TextComposite> sentences = new ArrayList<>();
        for (TextComponent paragraph : text.getComponents()) {
            for (TextComponent sentence : ((TextComposite) paragraph).getComponents()) {
                sentences.add((TextComposite) sentence);
            }
        }
        return sentences;
    }

    private Set<String> extractWords(TextComposite sentence) {
        Set<String> words = new HashSet<>();
        for (TextComponent lexeme : sentence.getComponents()) {
            for (TextComponent part : ((TextComposite) lexeme).getComponents()) {
                if (part instanceof TextComposite word && word.getType() == TextType.WORD) {
                    words.add(word.getText().toLowerCase());
                }
            }
        }
        return words;
    }

    public List<TextComposite> sortSentencesByLexemeCount(TextComposite text) {
        List<TextComposite> sentences = getAllSentences(text);

        sentences.sort(Comparator.comparingInt(s -> s.getComponents().size()));

        return sentences;
    }
    public void swapFirstAndLastLexeme(TextComposite text) {
        for (TextComponent paragraph : text.getComponents()) {
            for (TextComponent sentence : ((TextComposite) paragraph).getComponents()) {
                TextComposite s = (TextComposite) sentence;
                List<TextComponent> lexemes = s.getComponents();

                if (lexemes.size() > 1) {
                    TextComponent first = lexemes.get(0);
                    TextComponent last = lexemes.get(lexemes.size() - 1);

                    lexemes.set(0, last);
                    lexemes.set(lexemes.size() - 1, first);
                }
            }
        }
    }



}
