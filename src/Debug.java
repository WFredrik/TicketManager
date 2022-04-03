import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Debug {
    // Empty privat constructor to prevent instances from beeing created (singleton)
    private Debug() {
    }

    private static boolean debugAtive = false;
    static final String NL = System.getProperty("line.separator");

    static void on() {
        debugAtive = true;
    }

    static void off() {
        debugAtive = false;
    }

    static void readConfig() {
        String isActive = "";
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream("app.config")) {
            prop.load(fis);
            isActive = prop.getProperty("debugActive");
        } catch (IOException ignored) {
        }
        debugAtive = isActive != null && isActive.matches("true");
    }

    static void console(String msg) {
        if (debugAtive) {
            System.out.println(msg);
        }
    }
}
