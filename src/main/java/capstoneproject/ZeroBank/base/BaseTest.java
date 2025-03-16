package capstoneproject.ZeroBank.base;

import java.time.Duration;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;

public class BaseTest {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    
    @Parameters("browser")
    public static void setup() {
    	String browser = System.getProperty("browser", "chrome");
        if (driver.get() == null) {  
            if (browser.equalsIgnoreCase("chrome")) {
                driver.set(new ChromeDriver());
            } else if (browser.equalsIgnoreCase("firefox")) {
                driver.set(new FirefoxDriver());
            }
            driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get().manage().window().maximize();
        }
    }

    public static WebDriver getDriver() {
        return driver.get();  
    }

    public static void teardown() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove(); 
        }
    }
}
