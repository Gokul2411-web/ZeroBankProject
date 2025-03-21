package capstoneproject.ZeroBank.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    
    @BeforeTest
    @Parameters({"browser"}) 
    public static void setup(String browser) {
        if (driver.get() == null) {
        	if (browser.equalsIgnoreCase("edge")) {
            	EdgeOptions options = new EdgeOptions();
                options.setAcceptInsecureCerts(true);  // Accept insecure SSL certificates
                driver.set(new EdgeDriver(options));
            }
        	else if (browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
            }
            else {
                throw new IllegalArgumentException("Unsupported browser: " + browser);
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
