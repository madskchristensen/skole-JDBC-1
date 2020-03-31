import java.io.*;
import java.util.Properties;

public class PropertyManager {
    private String projectPath = System.getProperty("user.dir");
    private String resourcePath = "\\src\\main\\resources\\";
    private String appConfigPath = projectPath + resourcePath + "app.properties";
    private Properties appProps;

    public PropertyManager() throws IOException {
        appProps = new Properties();
        appProps.load(new FileInputStream(appConfigPath));
    }

    public String getProperty(String property) {
        return appProps.getProperty(property);
    }
}