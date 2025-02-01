package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import static common.Constants.IMPLICIT_WAIT;

public class SearchResultsPage extends BasePage {

    @FindBy(xpath = "//div[@data-test-id='start-date-value']")
    private WebElement searchDepartureDate;

    @FindBy(xpath = "//div[@data-test-id='origin-endpoint']/div[3]/span[@data-test-id]")
    private WebElement ticketDepartureDate;

    @FindBy(xpath = "//div[@data-test-id='filter-group-sort_side_group']/div[@role='button']")
    private WebElement sortDropdown;

    @FindBy(xpath = "//div[@data-test-id='single-choice-filter-sort-price_asc']/label/span")
    private WebElement sortPriceAscRadioBtn;

    @FindBy(xpath = "//button[@data-test-id='accept-cookies-button']")
    private WebElement acceptCookiesBtn;

    @FindBy(xpath = "//div[@data-test-id='ticket-preview']")
    private WebElement ticketPreview;

    @FindBy(xpath = "//div[@data-test-id='ticket-preview']//strong[@data-test-id='ticket-preview-badge-cheapest']")
    private WebElement ticketBadgeCheapest;

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

    public void setAcceptCookiesBtn() {
        acceptCookiesBtn.click();
    }

    public void sortCheapestFirst() {

        Actions actions = new Actions(driver);
        actions.scrollToElement(sortDropdown);
        sortDropdown.click();
        sortPriceAscRadioBtn.click();

    }

    public boolean isBadgeCheapestInsideTicket() {
        WebElement strongElement = ticketPreview.findElement(By.xpath(".//strong"));
        return strongElement.getText().equals(ticketBadgeCheapest.getText());

    }

    public boolean arePricesSortedAsc() {
        List<WebElement> ticketPrices = driver.findElements(By.xpath
                ("//div[@data-test-id='ticket-preview']/div/div/div/div[contains(text(), '₽')]"));

        for (int i = 0; i < ticketPrices.size() - 1; i++) {
            double price1 = Double.parseDouble(ticketPrices.get(i).getText().replaceAll("[^\\d.]", ""));
            double price2 = Double.parseDouble(ticketPrices.get(i + 1).getText().replaceAll("[^\\d.]", ""));
            if (price1 > price2) return false;
        }
        return true;
    }

}
