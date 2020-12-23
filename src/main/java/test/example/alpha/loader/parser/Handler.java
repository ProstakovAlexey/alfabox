package test.example.alpha.loader.parser;

import lombok.extern.slf4j.Slf4j;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import test.example.alpha.loader.pojo.Box;
import test.example.alpha.loader.pojo.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
Реализует методы необходимые для SAX  парсинга.
Сохраняет результат парсинга в 2-х списках getBoxes() и getItems()
Для хранание иерархии используется стек parentStack
 */
@Slf4j
public class Handler extends DefaultHandler {
    private static final String BOX = "Box";
    private static final String ITEM = "Item";
    private Stack<Long> parentStack;
    private List<Box> boxes;
    private List<Item> items;

    @Override
    public void startDocument() throws SAXException {
        log.debug("Start document parsing");
        boxes = new ArrayList<>();
        items = new ArrayList<>();
        parentStack = new Stack<>();
        parentStack.push(null);
    }

    @Override
    public void startElement(String uri, String lName, String qName, Attributes attr) throws SAXException {
        log.debug("Start element " + qName);
        String idStr;
        switch (qName) {
            case BOX:
                Box newBox = new Box();
                idStr = attr.getValue("id");
                if (idStr != null) {
                    newBox.setId(Long.parseLong(idStr));
                }
                newBox.setContained_in(parentStack.peek());
                boxes.add(newBox);
                parentStack.push(newBox.getId());
                break;
            case ITEM:
                Item newItem = new Item();
                idStr = attr.getValue("id");
                if (idStr != null) {
                    newItem.setId(Long.parseLong(idStr));
                }
                newItem.setColor(attr.getValue("color"));
                newItem.setContained_in(parentStack.peek());
                items.add(newItem);
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        log.debug("End element " + qName);
        switch (qName) {
            case BOX:
                parentStack.pop();
                break;
            case ITEM:
                break;
        }
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public List<Item> getItems() {
        return items;
    }
}
