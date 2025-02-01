package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static common.Constants.IMPLICIT_WAIT;
import static common.Constants.PAGE_LOAD_TIMEOUT;

public class CommonActions {

    public static WebDriver createDriver() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/chromedriver-win64/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\vasya\\OneDrive\\Рабочий стол\\IT\\AQA\\chrome-win64\\chrome.exe"); // Используем Chrome for Testing

        WebDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));

        return driver;
    }

}
