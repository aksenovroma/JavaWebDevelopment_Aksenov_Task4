package by.epam.javatraining.aksenov.task4.model.logic.separator;

import by.epam.javatraining.aksenov.task4.model.entity.CompositeItem;
import by.epam.javatraining.aksenov.task4.model.entity.ItemType;
import by.epam.javatraining.aksenov.task4.model.logic.Parser;
import org.apache.log4j.Logger;

public class ParagraphSeparator implements Separator{
    private static final Logger log = Logger.getRootLogger();

    public static final String REGEX_FOR_SENTENCES;

    static {
        REGEX_FOR_SENTENCES = "(?<!\\w\\.\\w.)(?<![A-Z][a-z]\\.)(?<=\\.|\\?|\\!|:)\\s";
    }

    public void separate(CompositeItem compositeItem) {
        String[] sentencesArr = Parser.parse(compositeItem.getText(), REGEX_FOR_SENTENCES);
        SentenceSeparator sentenceSeparator = new SentenceSeparator();

        for (String sentence : sentencesArr) {
            CompositeItem sent = new CompositeItem(sentence, ItemType.SENTENCE);
            sentenceSeparator.separate(sent);
            log.trace("sentence has been created");
            compositeItem.get().add(sent);
        }
    }
}
