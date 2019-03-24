package by.epam.javatraining.aksenov.task4.model.logic.separator;

import by.epam.javatraining.aksenov.task4.model.entity.CompositeItem;
import by.epam.javatraining.aksenov.task4.model.entity.Item;
import by.epam.javatraining.aksenov.task4.model.entity.ItemType;
import by.epam.javatraining.aksenov.task4.model.entity.SimpleItem;
import by.epam.javatraining.aksenov.task4.util.Parser;
import org.apache.log4j.Logger;

import java.util.regex.Pattern;

public class SentenceSeparator implements Separator {
    private static final Logger log = Logger.getRootLogger();

    public static final String REGEX_FOR_PUNCTUATION = "\\p{Punct}";

    public void separate(CompositeItem compositeItem) {
        String[] sentenceItemArr = Parser.parse(compositeItem.getText(), CompositeItem.REGEX_FOR_SENTENCE_ITEMS);

        for (String sentItem : sentenceItemArr) {
            Item item;
            if (Pattern.matches(REGEX_FOR_PUNCTUATION, sentItem)) {
                item = new SimpleItem(sentItem, ItemType.PUNCTUATION);
                log.trace("punctuation has been created");
            } else {
                item = new SimpleItem(sentItem, ItemType.WORD);
                log.trace("word has been created");
            }
            compositeItem.get().add(item);
        }
    }
}