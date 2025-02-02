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

    @FindBy(xpath = "//div[@data-test-id='ticket-preview']//button[@data-test-id='button']")
    private WebElement selectTicketBtn;

    @FindBy(xpath = "//div[@data-test-id='origin-endpoint']/div[3]/span[@data-test-id]")
    private WebElement ticketPreviewDepartureDate;

    @FindBy(xpath = "//div[@data-test-id='origin-endpoint']//span[@data-test-id='text']")
    private WebElement ticketPreviewDepartureTime;

    @FindBy(xpath = "//div[@data-test-id='destination-endpoint']/div[3]/span[@data-test-id]")
    private WebElement ticketPreviewDestinationDate;

    @FindBy(xpath = "//div[@data-test-id='destination-endpoint']//span[@data-test-id='text']")
    private WebElement ticketPreviewDestinationTime;

    @FindBy(xpath = "//div[@data-test-id='filter-group-sort_side_group']/div[@role='button']")
    private WebElement sortDropdown;

    @FindBy(xpath = "//div[@data-test-id='single-choice-filter-sort-price_asc']/label/span")
    private WebElement sortPriceAscRadioBtn;

    @FindBy(xpath = "//button[@data-test-id='accept-cookies-button']")
    private WebElement acceptCookiesBtn;

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

    public String getTicketPreviewDepartureDate() {
        return ticketPreviewDepartureDate.getText();
    }

    public String getTicketPreviewDepartureTime() {
        return ticketPreviewDepartureTime.getText();
    }

    public String getTicketPreviewDestinationDate() {
        return ticketPreviewDestinationDate.getText();
    }

    public String getTicketPreviewDestinationTime() {
        return ticketPreviewDestinationTime.getText();
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
        WebElement strongElement = selectTicketBtn.findElement(By.xpath(".//strong"));
        return strongElement.getText().equals(ticketBadgeCheapest.getText());

    }

    public boolean arePricesSortedAsc() {

        List<WebElement> ticketPrices = driver.findElements(By.xpath
                ("//div[@data-test-id='ticket-preview']/div/div/div/div[contains(text(), '₽')]"));

        for (int i = 0; i < ticketPrices.size() - 1; i++) {
            int price1 = Integer.parseInt(ticketPrices.get(i).getText().replaceAll("[^\\d.]", ""));
            int price2 = Integer.parseInt(ticketPrices.get(i + 1).getText().replaceAll("[^\\d.]", ""));
            if (price1 > price2) return false;
        }
        return true;
    }

    public void selectTicket() {
        Actions actions = new Actions(driver);
        actions.scrollToElement(selectTicketBtn);
        selectTicketBtn.click();
    }

    public String getTicketDepartureCity(String departure) {
        WebElement ticketDepartureCity = new WebDriverWait(driver, Duration.ofSeconds(IMPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                        ("//div[@data-test-id='selected-fare-card']/following-sibling::div//div[text()='"
                                + departure + "']")));
        return ticketDepartureCity.getText();
    }

    public String getTicketDepartureTime() {
        WebElement ticketDepartureTime = new WebDriverWait(driver, Duration.ofSeconds(IMPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                        ("//div[@data-test-id='selected-fare-card']/following-sibling::div//div[contains(text(),':')]")));
        return ticketDepartureTime.getText();
    }

    public String getTicketDepartureDate() {
        WebElement ticketDepartureDate = new WebDriverWait(driver, Duration.ofSeconds(IMPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                        ("//div[@data-test-id='selected-fare-card']/following-sibling::div//div[contains(text(),':')]//following-sibling::div")));
        return ticketDepartureDate.getText();
    }

    public String getTicketDestinationCity(String destination) {
        WebElement ticketDestinationCity = new WebDriverWait(driver, Duration.ofSeconds(IMPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                        ("//div[@data-test-id='selected-fare-card']/following-sibling::div//div[text()='"
                                + destination + "']")));
        return ticketDestinationCity.getText();
    }

    public String getTicketDestinationTime() {
        WebElement ticketDestinationTime = new WebDriverWait(driver, Duration.ofSeconds(IMPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                        ("//div[@data-test-id='selected-fare-card']/following-sibling::div/div[2]/div/div[2]/div[2]//div[contains(text(),':')]")));
        return ticketDestinationTime.getText();
    }

    public String getTicketDestinationDate() {
        WebElement ticketDestinationDate = new WebDriverWait(driver, Duration.ofSeconds(IMPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                        ("//div[@data-test-id='selected-fare-card']/following-sibling::div/div[2]/div/div[2]/div[2]//div[contains(text(),':')]//following-sibling::div")));
        return ticketDestinationDate.getText();
    }

}
