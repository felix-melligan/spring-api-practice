package application.tools;

import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ConfigFileReaderTest {
    private ConfigFileReader fr;
    private String propertiesFile = "config.properties";
    private String validProperty = "database";
    private String invalidProperty = "notAProperty";

    @Before
    public void setUp() {
        fr = new ConfigFileReader();
    }

    @Test
    public void fileReaderReturnsStringIfPresent() {
        String expectedResult = "SPRING_PRACTICE";

        try {
            assertEquals(expectedResult, fr.getConfigProperty(propertiesFile, validProperty));
        } catch (IOException ioe) {
            fail();
        }
    }

    @Test
    public void fileReaderReturnsNullIfPropertyNotPresent() {
        try {
            assertEquals(null, fr.getConfigProperty(propertiesFile, invalidProperty));
        } catch (IOException ioe) {
            fail();
        }
    }

    @Test
    public void fileReaderErrorsIfFileNotPresent() {
        String notAFile = "notAFile";
        String errorMessage = "File: "+notAFile+" not found!";

        try {
            fr.getConfigProperty(notAFile, validProperty);
        } catch (FileNotFoundException fileNotFoundException) {
            assertEquals(errorMessage, fileNotFoundException.getMessage());
        } catch (IOException ioe) {
            fail();
        }
    }
}
