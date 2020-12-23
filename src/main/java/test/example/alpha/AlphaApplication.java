package test.example.alpha;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import test.example.alpha.db.SaveToBD;
import test.example.alpha.loader.FileLoader;
import test.example.alpha.loader.LoaderFactory;
import test.example.alpha.loader.LoaderInterface;
import test.example.alpha.loader.pojo.Box;
import test.example.alpha.loader.pojo.Item;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootApplication
public class AlphaApplication {
    @Autowired
    SaveToBD saveToBD;

    @Autowired
    Environment env;

    public static void main(String[] args) {
        for(String arg:args) {
            System.out.println(arg);
        }

        SpringApplication.run(AlphaApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        String filePath = env.getProperty("filepath");
        log.info("Run with filepath=" + filePath);
        if (filePath == null) {
            log.error("Must run with --filepath=1.xml");
            System.exit(3);
        }
        LoaderInterface loader = LoaderFactory.getLoader(filePath);
        try {
            loader.load();
        }
        catch (Exception exception) {
            log.error("Error for file parsing: " + exception.getMessage());
            log.info("Program will close");
            System.exit(1);
        }
        saveToBD.saveBoxes(loader.getBox());
        log.info("Saved boxes in BD");
        saveToBD.saveItems(loader.getItem());
        log.info("Saved items in BD");
    }
}
