package test.example.alpha.loader;

import org.xml.sax.SAXException;
import test.example.alpha.loader.pojo.Box;
import test.example.alpha.loader.pojo.Item;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface LoaderInterface {

    public void load() throws IOException, SAXException, ParserConfigurationException;
    public List<Box> getBox();
    public List<Item> getItem();
}
