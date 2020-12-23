package test.example.alpha.loader.parser;

import lombok.extern.slf4j.Slf4j;
import org.xml.sax.SAXException;
import test.example.alpha.loader.pojo.Box;
import test.example.alpha.loader.pojo.Item;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

/*
SAX парсер, по идее он должен аккуратно память потреблять.
Но есть особенность, что он хранить в памяти результат парсинга. Занимает конечно не так много как
DOM, но можно было бы и постепенно выгружать. Но тут конечно есть проблема - файл может быть не валидным
и сломаться при загрузке в БД и останутся тогда куски.
 */
@Slf4j
public class XMLParser {
    private final Handler handler;

    public XMLParser(String fileName) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        handler = new Handler();
        saxParser.parse(fileName, handler);
        log.info("Parsing was finished.");
    }

    public List<Item> getItems() {
        return handler.getItems();
    }

    public List<Box> getBoxes() {
        return handler.getBoxes();
    }
}





