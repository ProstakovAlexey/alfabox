package test.example.alpha.loader;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.xml.sax.SAXException;
import test.example.alpha.loader.parser.XMLParser;
import test.example.alpha.loader.pojo.Box;
import test.example.alpha.loader.pojo.Item;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
public class HttpLoader implements LoaderInterface {
    XMLParser parser;
    String fileName;

    public HttpLoader(String url) {
        try {
            fileName = getFile(url);
        }
        catch (Exception ex) {
            log.error("Error for loading file: " + ex);
            System.exit(2);
        }
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

    private String getFile(String url) throws ClientProtocolException, IOException{
        String fileName = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpResponse response = httpClient.execute(new HttpGet(url));
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            long len = entity.getContentLength();
            InputStream inputStream = entity.getContent();
            fileName = "temp.json";
            FileOutputStream fos = new FileOutputStream(new File(fileName));
            int inByte;
            while((inByte = inputStream.read()) != -1)
                fos.write(inByte);
            inputStream.close();
            fos.close();
        }
        return fileName;
    }

}
