package by.epam.javatraining.aksenov.task4.model.logic.separator;

import by.epam.javatraining.aksenov.task4.model.entity.CompositeItem;
import by.epam.javatraining.aksenov.task4.model.entity.ItemType;
import by.epam.javatraining.aksenov.task4.util.Parser;
import org.apache.log4j.Logger;

public class ParagraphSeparator implements Separator{
    private static final Logger log = Logger.getRootLogger();

    public void separate(CompositeItem compositeItem) {
        String[] sentencesArr = Parser.parse(compositeItem.getText(), CompositeItem.REGEX_FOR_SENTENCES);
        SentenceSeparator sentenceSeparator = new SentenceSeparator();

        for (String sentence : sentencesArr) {
            CompositeItem sent = new CompositeItem(sentence, ItemType.SENTENCE);
            sentenceSeparator.separate(sent);
            log.trace("sentence has been created");
            compositeItem.get().add(sent);
        }
    }
}
