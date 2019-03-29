package by.epam.javatraining.aksenov.task4.model.logic.parser;

import by.epam.javatraining.aksenov.task4.model.entity.CompositeItem;
import by.epam.javatraining.aksenov.task4.model.entity.Item;
import by.epam.javatraining.aksenov.task4.model.entity.ItemType;
import by.epam.javatraining.aksenov.task4.model.entity.SimpleItem;
import by.epam.javatraining.aksenov.task4.model.logic.TextHandler;
import by.epam.javatraining.aksenov.task4.model.logic.Separator;
import org.apache.log4j.Logger;

/**
 * @author aksenov
 * @version 1.0
 * @date 24.03.2019
 */

public class TextParser implements Parser {
    private static final Logger log = Logger.getRootLogger();

    public static final String REGEX_FOR_PARAGRAPH;

    static {
        REGEX_FOR_PARAGRAPH = "\\n{2,}";
    }

    private Parser nextParser = new ParagraphParser();

    public void setNextParser(Parser nextParser) {
        if (nextParser != null) {
            this.nextParser = nextParser;
        }
    }

    public void parse(CompositeItem compositeItem) {
        if (compositeItem != null) {
            String[] paragraphsArr = Separator.separate(compositeItem.getText(), REGEX_FOR_PARAGRAPH);

            for (String paragraph : paragraphsArr) {
                Item par;

                if (!TextHandler.isCodeBlock(paragraph)) {
                    par = new CompositeItem(paragraph, ItemType.PARAGRAPH);
                    nextParser.parse((CompositeItem) par);
                } else {
                    par = new SimpleItem(paragraph, ItemType.CODE_BLOCK);
                }

                log.trace("paragraph has been created");
                compositeItem.get().add(par);
            }
        }
    }
}
