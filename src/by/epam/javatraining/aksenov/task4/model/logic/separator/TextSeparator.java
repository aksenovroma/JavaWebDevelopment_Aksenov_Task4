package by.epam.javatraining.aksenov.task4.model.logic.separator;

import by.epam.javatraining.aksenov.task4.model.entity.CompositeItem;
import by.epam.javatraining.aksenov.task4.model.entity.Item;
import by.epam.javatraining.aksenov.task4.model.entity.ItemType;
import by.epam.javatraining.aksenov.task4.model.entity.SimpleItem;
import by.epam.javatraining.aksenov.task4.util.Parser;
import org.apache.log4j.Logger;

public class TextSeparator implements Separator{
    private static final Logger log = Logger.getRootLogger();

    public void separate(CompositeItem compositeItem) {
        String[] paragraphsArr = Parser.parse(compositeItem.getText(), CompositeItem.REGEX_FOR_PARAGRAPH);
        ParagraphSeparator paragraphSeparator = new ParagraphSeparator();

        for (String paragraph : paragraphsArr) {
            Item par;

            if (!Parser.isCodeBlock(paragraph)) {
                par = new CompositeItem(paragraph, ItemType.PARAGRAPH);
                paragraphSeparator.separate((CompositeItem) par);
            } else {
                par = new SimpleItem(paragraph, ItemType.CODE_BLOCK);
            }

            log.trace("paragraph has been created");
            compositeItem.get().add(par);
        }
    }
}
