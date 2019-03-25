package by.epam.javatraining.aksenov.task4.model.logic.separator;

import by.epam.javatraining.aksenov.task4.model.entity.CompositeItem;
import by.epam.javatraining.aksenov.task4.model.entity.Item;
import by.epam.javatraining.aksenov.task4.model.entity.ItemType;
import by.epam.javatraining.aksenov.task4.model.entity.SimpleItem;
import by.epam.javatraining.aksenov.task4.model.logic.Parser;
import org.apache.log4j.Logger;

import java.util.regex.Pattern;

/**
 * @author aksenov
 * @version 1.0
 * @date 24.03.2019
 */

public class SentenceSeparator implements Separator {
    private static final Logger log = Logger.getRootLogger();

    public static final String REGEX_FOR_PUNCTUATION;
    public static final String REGEX_FOR_SPACE_1;
    public static final String REGEX_FOR_SPACE_2;
    public static final String REGEX_FOR_SENTENCE_ITEMS;

    static {
        REGEX_FOR_SENTENCE_ITEMS = "(?<=[\\s])|(?<=[\\p{Punct}&&[^']])(?!\\s)|(?=[\\p{Punct}&&[^']])(?<!\\s)"
                + "|(?<=[\\s\\p{Punct}]['])(?!\\s)|(?=['][\\s\\p{Punct}])(?<!\\s)|([\\s])";
        REGEX_FOR_PUNCTUATION = "\\p{Punct}";
        REGEX_FOR_SPACE_1 = "";
        REGEX_FOR_SPACE_2 = " ";
    }

    public void separate(CompositeItem compositeItem) {
        if (compositeItem != null) {
            String[] sentenceItemArr = Parser.parse(compositeItem.getText(), REGEX_FOR_SENTENCE_ITEMS);

            for (String sentItem : sentenceItemArr) {
                Item item;

                if (Pattern.matches(REGEX_FOR_PUNCTUATION, sentItem)) {
                    item = new SimpleItem(sentItem, ItemType.PUNCTUATION);
                    log.trace("punctuation has been created");

                } else if (sentItem.equals(REGEX_FOR_SPACE_1) || sentItem.equals(REGEX_FOR_SPACE_2)) {
                    item = new SimpleItem(REGEX_FOR_SPACE_2, ItemType.SPACE);
                    log.trace("space has been created");
                }

                else {
                    item = new SimpleItem(sentItem, ItemType.WORD);
                    log.trace("word has been created");
                }

                compositeItem.get().add(item);
            }
        }
    }
}
