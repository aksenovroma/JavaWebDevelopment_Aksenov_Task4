package by.epam.javatraining.aksenov.task4.model.logic;

import by.epam.javatraining.aksenov.task4.model.entity.CompositeItem;
import by.epam.javatraining.aksenov.task4.model.entity.Item;
import by.epam.javatraining.aksenov.task4.model.entity.ItemType;
import by.epam.javatraining.aksenov.task4.util.Parser;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static by.epam.javatraining.aksenov.task4.util.Parser.isCodeBlock;

public class TextHandler {
    private static Logger log = Logger.getRootLogger();

    /*6)В каждом предложении текста поменять местами первое слово с последним,
    не изменяя длины предложения.*/

    public static void swapFirstLastWordInAllSentence(CompositeItem item) {
        if (item != null) {
            for (Item paragraph : item.get()) {
                if (paragraph != null && !(Parser.isCodeBlock(paragraph.getText()))) {
                    for (Item sentence : ((CompositeItem) paragraph).get()) {
                        if (sentence != null) {
                            Item firstWord = ((CompositeItem) sentence).getItem(indexOfFirstWord(sentence));
                            Item lastWord = ((CompositeItem) sentence).getItem(indexOfLastWord(sentence));

                            String temp = firstWord.getText();
                            firstWord.setText(lastWord.getText());
                            lastWord.setText(temp);
                        }
                    }
                }
            }
        }
        log.info("method swapFirstLastWordInAllSentence - completed");
    }

    /*13)Из текста удалить все слова заданной длины, начинающиеся на согласную
    букву.*/

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

    /*20)Реверсировать (переставить предложение задом наперёд) все предложения
    текста.*/

    public static void reverseAllSentences(CompositeItem item) {
        if (item != null) {
            for (Item paragraph : item.get()) {
                if (paragraph != null && !(Parser.isCodeBlock(paragraph.getText()))) {
                    for (Item sentence : ((CompositeItem) paragraph).get()) {
                        if (sentence != null) {
                            Collections.reverse(((CompositeItem) sentence).get());

                            for (Item sentenceItem : ((CompositeItem) sentence).get()) {
                                String reversedText = new StringBuilder(sentenceItem.getText()).reverse().toString();

                                sentenceItem.setText(reversedText);
                            }
                        }
                    }
                }
            }
        }
        log.info("method reverseAllSentences - completed");
    }

    public static Item clone(Item item) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (ObjectOutputStream ous = new ObjectOutputStream(baos)) {
            ous.writeObject(item);
        } catch (IOException e) {
            log.error(e);
        }

        Item copyItem = null;

        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()))) {
            copyItem = (Item) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            log.error(e);
        }

        return copyItem;
    }

    public static int indexOfFirstWord(Item sentence) {
        List<Item> itemList = ((CompositeItem) sentence).get();

        for (Item sentenceItem : itemList) {
            if (sentenceItem.getItemType() == ItemType.WORD) {
                return itemList.indexOf(sentenceItem);
            }
        }
        return -1;
    }

    public static int indexOfLastWord(Item sentence) {
        List<Item> itemList = ((CompositeItem) sentence).get();

        for (int i = itemList.size() - 1; i >= 0; i--) {
            if (itemList.get(i).getItemType() == ItemType.WORD) {
                return i;
            }
        }
        return -1;
    }

    public static boolean isSuitableWord(Item item, int length) {
        String word = item.getText();

        if (word.length() == length && isVowel(word.charAt(0))) {
            return true;
        }
        return false;
    }

    public static boolean isVowel(char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }
}
