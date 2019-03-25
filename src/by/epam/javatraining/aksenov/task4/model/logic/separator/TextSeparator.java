package by.epam.javatraining.aksenov.task4.model.logic.separator;

import by.epam.javatraining.aksenov.task4.model.entity.CompositeItem;
import by.epam.javatraining.aksenov.task4.model.entity.Item;
import by.epam.javatraining.aksenov.task4.model.entity.ItemType;
import by.epam.javatraining.aksenov.task4.model.entity.SimpleItem;
import by.epam.javatraining.aksenov.task4.model.logic.TextHandler;
import by.epam.javatraining.aksenov.task4.model.logic.Parser;
import org.apache.log4j.Logger;

public class TextSeparator implements Separator {
    private static final Logger log = Logger.getRootLogger();

    public static final String REGEX_FOR_PARAGRAPH;

    static {
        REGEX_FOR_PARAGRAPH = "\\n{2,}";
    }

    public void separate(CompositeItem compositeItem) {
        if (compositeItem != null) {
            String[] paragraphsArr = Parser.parse(compositeItem.getText(), REGEX_FOR_PARAGRAPH);
            ParagraphSeparator paragraphSeparator = new ParagraphSeparator();

            for (String paragraph : paragraphsArr) {
                Item par;

                if (!TextHandler.isCodeBlock(paragraph)) {
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
}
