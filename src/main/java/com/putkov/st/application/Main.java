package com.putkov.st.application;

import com.putkov.st.composite.TextComposite;
import com.putkov.st.parser.TextHandler;
import com.putkov.st.parser.ParagraphHandler;
import com.putkov.st.parser.SentenceHandler;
import com.putkov.st.parser.LexemeHandler;
import com.putkov.st.parser.WordHandler;
import com.putkov.st.reader.TextReader;
import com.putkov.st.reader.TextReaderImpl;
import com.putkov.st.service.TextServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        TextReader reader = new TextReaderImpl();
        String text = "";

        try {
            text = reader.textReadFromFile("data/text.txt");
        } catch (Exception e) {
            LOGGER.error("Reading error: {}", e.getMessage());
            return;
        }

        TextHandler textHandler = new TextHandler();
        ParagraphHandler paragraphHandler = new ParagraphHandler();
        SentenceHandler sentenceHandler = new SentenceHandler();
        LexemeHandler lexemeHandler = new LexemeHandler();
        WordHandler wordHandler = new WordHandler();

        // связываем цепочку
        textHandler.setNextHandler(paragraphHandler);
        paragraphHandler.setNextHandler(sentenceHandler);
        sentenceHandler.setNextHandler(lexemeHandler);
        lexemeHandler.setNextHandler(wordHandler);

        // запускаем обработку
        TextComposite textComposite = textHandler.handle(text);

        // выводим результат
        LOGGER.info("Restored text:\n{}", textComposite.getText());
        LOGGER.info("Characters count: {}", textComposite.count());

        TextServiceImpl service = new TextServiceImpl();

        int max = service.findMaxSentencesWithSameWords(textComposite);
        LOGGER.info("Max sentences with same words: {}", max);

        LOGGER.info("Sentences sorted by lexeme count:");
        for (TextComposite s : service.sortSentencesByLexemeCount(textComposite)) {
            LOGGER.info(s.getText());
        }
        service.swapFirstAndLastLexeme(textComposite);
        LOGGER.info("Text after swapping first and last lexeme:\n{}", textComposite.getText());
    }
}
