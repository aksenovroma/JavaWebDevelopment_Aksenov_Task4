package by.epam.javatraining.aksenov.task4.model.logic.parser;

import by.epam.javatraining.aksenov.task4.model.entity.CompositeItem;
import by.epam.javatraining.aksenov.task4.model.entity.ItemType;
import by.epam.javatraining.aksenov.task4.model.logic.Separator;
import org.apache.log4j.Logger;

/**
 * @author aksenov
 * @version 1.0
 * @date 24.03.2019
 */

public class ParagraphParser implements Parser {
    private static final Logger log = Logger.getRootLogger();

    public static final String REGEX_FOR_SENTENCES;

    static {
        REGEX_FOR_SENTENCES = "(?<!\\w\\.\\w.)(?<![A-Z][a-z]\\.)(?<=\\.|\\?|\\!|:)\\s";
    }

    private Parser nextParser = new SentenceParser();

    public void setNextParser(Parser nextParser) {
        if (nextParser != null) {
            this.nextParser = nextParser;
        }
    }

    public void parse(CompositeItem compositeItem) {
        if (compositeItem != null) {
            String[] sentencesArr = Separator.separate(compositeItem.getText(), REGEX_FOR_SENTENCES);

            for (String sentence : sentencesArr) {
                CompositeItem sent = new CompositeItem(sentence, ItemType.SENTENCE);
                nextParser.parse(sent);
                log.trace("sentence has been created");
                compositeItem.get().add(sent);
            }
        }
    }
}
