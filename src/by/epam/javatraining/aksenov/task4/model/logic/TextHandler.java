package by.epam.javatraining.aksenov.task4.model.logic;

import by.epam.javatraining.aksenov.task4.model.entity.CompositeItem;
import by.epam.javatraining.aksenov.task4.model.entity.Item;
import by.epam.javatraining.aksenov.task4.model.entity.ItemType;
import by.epam.javatraining.aksenov.task4.model.exception.logic.WrongArgumentException;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author aksenov
 * @version 1.0
 * @date 25.03.2019
 */

public class TextHandler {
    private static Logger log = Logger.getRootLogger();

    /*6)In each sentence of the text swap the first word with the last,
     without changing the length of the sentence.*/

    public static void swapFirstLastWordInAllSentence(CompositeItem item) {
        if (item != null) {
            for (Item paragraph : item.get()) {
                if (paragraph != null && !(isCodeBlock(paragraph.getText()))) {
                    for (Item sentence : ((CompositeItem) paragraph).get()) {
                        if (sentence != null) {
                            Item firstWord = ((CompositeItem) sentence).getItem(indexOfFirstWord(sentence));
                            Item lastWord = ((CompositeItem) sentence).getItem(indexOfLastWord(sentence));

                            String temp = firstWord.getText();
                            try {
                                firstWord.setText(lastWord.getText());
                                lastWord.setText(temp);
                            } catch (WrongArgumentException e) {
                                log.error(e);
                            }
                        }
                    }
                }
            }
        }
        log.info("method swapFirstLastWordInAllSentence - completed");
    }

    /*13)Delete all words of a given length starting with a consonant from the text.
     the letter.*/

    public static void removeWordsWithFirstVowel(CompositeItem item, int length) {
        if (item != null) {
            for (Item paragraph : item.get()) {
                if (paragraph != null && !(isCodeBlock(paragraph.getText()))) {
                    for (Item sentence : ((CompositeItem) paragraph).get()) {
                        if (sentence != null) {

                            List<Item> sentenceItemList = ((CompositeItem) sentence).get();
                            Iterator<Item> it = sentenceItemList.iterator();

                            while (it.hasNext()) {
                                Item nextItem = it.next();
                                if (nextItem.getItemType() == ItemType.WORD && isSuitableWord(nextItem, length)) {
                                    it.remove();
                                }
                            }
                        }
                    }
                }
            }
        }
        log.info("method removeWordsWithFirstVowel - completed");
    }

    /*20)Reverse (rearrange the proposal back to front) all offers
     text.*/

    public static void reverseAllSentences(CompositeItem item) {
        if (item != null) {
            for (Item paragraph : item.get()) {
                if (paragraph != null && !(isCodeBlock(paragraph.getText()))) {
                    for (Item sentence : ((CompositeItem) paragraph).get()) {
                        if (sentence != null) {
                            Collections.reverse(((CompositeItem) sentence).get());

                            for (Item sentenceItem : ((CompositeItem) sentence).get()) {
                                String reversedText = new StringBuilder(sentenceItem.getText()).reverse().toString();

                                try {
                                    sentenceItem.setText(reversedText);
                                } catch (WrongArgumentException e) {
                                    log.error(e);
                                }
                            }
                        }
                    }
                }
            }
        }
        log.info("method reverseAllSentences - completed");
    }



    public static int indexOfFirstWord(Item sentence) {
        if (sentence != null) {
            List<Item> itemList = ((CompositeItem) sentence).get();

            for (Item sentenceItem : itemList) {
                if (sentenceItem.getItemType() == ItemType.WORD) {
                    return itemList.indexOf(sentenceItem);
                }
            }
        }
        return -1;
    }

    public static int indexOfLastWord(Item sentence) {
        if (sentence != null) {
            List<Item> itemList = ((CompositeItem) sentence).get();

            for (int i = itemList.size() - 1; i >= 0; i--) {
                if (itemList.get(i).getItemType() == ItemType.WORD) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static boolean isSuitableWord(Item item, int length) {
        if (item != null) {
            String word = item.getText();
            return word.length() == length && CharacterHandler.isVowel(word.charAt(0));
        }
        return false;
    }

    public static boolean isCodeBlock(String text) {
        if (text != null) {
            return text.contains(";") && (text.contains("=") || (text.contains("(") && text.contains(")")));
        }
        return false;
    }
}
