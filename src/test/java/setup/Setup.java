package setup;

import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Setup {

    public Properties properties;

    @BeforeTest
    public void initConfig() throws IOException {
        properties = new Properties();
        FileInputStream fileInputStream =new FileInputStream("./src/test/resources/config.properties");
        properties.load(fileInputStream);
    }
}