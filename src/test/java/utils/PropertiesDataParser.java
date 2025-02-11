package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesDataParser {

    public static Properties loadProperties(String fileName) {
        Properties properties = new Properties();
        File folderDir = new File("./src/test/java/resource");
        File propertiesFile = new File(folderDir, fileName);
        FileInputStream fis;
        try {
            fis = new FileInputStream(propertiesFile.getAbsolutePath());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

}