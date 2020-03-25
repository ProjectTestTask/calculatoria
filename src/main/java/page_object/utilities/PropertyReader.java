package page_object.utilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private static final String PROPERTY_FILE_PATH = "./src/main/resources/properties/environment.properties";
    private Properties properties;

    public PropertyReader() {
        properties = new Properties();
        try {
            properties.load(new FileReader(new File(PROPERTY_FILE_PATH)));
        } catch (IOException e) {
            throw new IllegalStateException("Not possible to read property file", e);
        }
    }

    public String readProperty(final String key) {
        return properties.getProperty(key);
    }
}
