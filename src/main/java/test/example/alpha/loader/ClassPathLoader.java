package test.example.alpha.loader;

import lombok.extern.slf4j.Slf4j;
import org.xml.sax.SAXException;
import test.example.alpha.loader.parser.XMLParser;
import test.example.alpha.loader.pojo.Box;
import test.example.alpha.loader.pojo.Item;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
public class ClassPathLoader implements LoaderInterface {
    XMLParser parser;
    String file;

    public ClassPathLoader(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            file = classLoader.getResource(fileName).getPath();
        }
        catch (Exception e) {
            log.error("Can not find file in class path: " + fileName);
            System.exit(2);
        }

    }

    @Override
    public void load() throws IOException, SAXException, ParserConfigurationException {

        parser = new XMLParser(file);
    }

    @Override
    public List<Box> getBox() {
        return parser.getBoxes();
    }

    @Override
    public List<Item> getItem() {
        return parser.getItems();
    }

}
