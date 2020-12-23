package test.example.alpha.loader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoaderFactory {

    public static LoaderInterface getLoader(String str) {
        int index = str.indexOf(":");
        LoaderInterface loader = null;
        if (index > 0) {
            String prefixe = str.substring(0,index);
            String postfixe = str.substring(index+1);
            switch (prefixe) {
                case "file": {
                    log.debug("Loader factory select file");
                    loader = new FileLoader(postfixe);
                    break;
                }
                case "classpath": {
                    log.debug("Loader factory select classpath");
                    loader = new ClassPathLoader(postfixe);
                    break;
                }
                case "url": {
                    log.debug("Loader factory select https");
                    loader = new HttpLoader(postfixe);
                    break;
                }
                default: {
                    log.error("Can not loader for " + prefixe);
                }
            }
        }
        return loader;
    }
}
