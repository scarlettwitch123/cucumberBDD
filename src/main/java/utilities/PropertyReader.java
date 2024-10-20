package utilities;

import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyReader {
    private Properties properties;
    private static final Logger logger = LoggerFactory.getLogger(PropertyReader.class);

    public PropertyReader(String fileName) {
        properties = new Properties();
        loadProperties(fileName);
    }

    private void loadProperties(String fileName) {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                logger.warn("Sorry, unable to find {}", fileName);
                return;
            }
            properties.load(input);
            logger.info("Properties loaded from file: {}", fileName);
        } catch (Exception e) {
            logger.error("Error loading properties from file {}: {}", fileName, e.getMessage());
        }
    }

    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            logger.warn("Property {} not found", key);
        }
        return value;
    }
}
