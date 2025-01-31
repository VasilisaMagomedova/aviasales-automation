package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static common.Constants.IMPLICIT_WAIT;

public class SearchResultsPage extends BasePage {

    @FindBy(xpath = "//div[@data-test-id='start-date-value']")
    private WebElement searchDepartureDate;

    @FindBy(xpath = "//div[@data-test-id='origin-endpoint']/div[3]/span[@data-test-id]")
    private WebElement ticketDepartureDate;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getDepartureCity(String departure) {
        WebElement departureCity = new WebDriverWait(driver, Duration.ofSeconds(IMPLICIT_WAIT)).until
                (ExpectedConditions.visibilityOfElementLocated(By.xpath
                        ("//div[@data-test-id='origin-endpoint']//span[text()='" + departure + "']")));
        return departureCity.getText();
    }

    public String getDestinationCity(String destination) {
        WebElement destinationCity = new WebDriverWait(driver, Duration.ofSeconds(IMPLICIT_WAIT)).until
                (ExpectedConditions.visibilityOfElementLocated(By.xpath
                        ("//div[@data-test-id='destination-endpoint']//span[text()='" + destination + "']")));
        return destinationCity.getText();
    }

    public String getSearchDepartureDate() {
        return searchDepartureDate.getText();
    }

    public String getTicketDepartureDate() {
        return ticketDepartureDate.getText();
    }

}
