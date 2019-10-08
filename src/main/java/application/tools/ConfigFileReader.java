package application.tools;

import javax.el.PropertyNotFoundException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFileReader {

    public String getConfigProperty(String propertiesFile, String property) throws IOException {
        Properties prop = new Properties();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFile)) {
            prop.load(inputStream);
            return prop.getProperty(property);
        } catch (NullPointerException npe) {
            throw new FileNotFoundException("File: "+propertiesFile+" not found!");
        }
    }
}
