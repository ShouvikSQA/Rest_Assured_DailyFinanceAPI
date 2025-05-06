package config;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SetUp {
    public Properties props;
    public FileInputStream file;

    @BeforeTest
    public void setup() throws IOException {
        props = new Properties();
        file = new FileInputStream("./src/test/resources/config.properties");
        props.load(file);
    }

    @AfterMethod
    public void refresh() throws IOException {
        props = new Properties();
        file = new FileInputStream("./src/test/resources/config.properties");
        props.load(file);

    }
}
