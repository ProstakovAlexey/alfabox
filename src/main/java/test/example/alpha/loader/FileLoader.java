package test.example.alpha.loader;

import org.xml.sax.SAXException;
import test.example.alpha.loader.parser.XMLParser;
import test.example.alpha.loader.pojo.Box;
import test.example.alpha.loader.pojo.Item;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;


public class FileLoader implements LoaderInterface {
    XMLParser parser;
    String fileName;

    public FileLoader(String file) {
        fileName = file;
    }

    @Override
    public void load() throws IOException, SAXException, ParserConfigurationException {
        parser = new XMLParser(fileName);
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
