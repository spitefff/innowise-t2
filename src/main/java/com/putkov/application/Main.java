package com.putkov.application;

import com.putkov.composite.TextComposite;
import com.putkov.composite.TextType;
import com.putkov.parser.TextHandler;
import com.putkov.parser.ParagraphHandler;
import com.putkov.parser.SentenceHandler;
import com.putkov.parser.LexemeHandler;
import com.putkov.parser.WordHandler;
import com.putkov.reader.TextReader;
import com.putkov.reader.TextReaderImpl;
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
    }
}
