package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static common.Constants.DEPARTURE_DATE;

public class HomePage extends BasePage {

    @FindBy(xpath = "//input[@data-test-id='origin-input']")
    private WebElement departureInput;

    @FindBy(xpath = "//ul[@id='avia_form_origin-menu']//li")
    private WebElement departureSuggested;

    @FindBy(xpath = "//input[@data-test-id='destination-input']")
    private WebElement destinationInput;

    @FindBy(xpath = "//ul[@id='avia_form_destination-menu']//li")
    private WebElement destinationSuggested;

    @FindBy(xpath = "//button[@data-test-id='start-date-field']")
    private WebElement departureDateField;

    private final By departureDate = By.xpath("//div[contains(@aria-label,'" + DEPARTURE_DATE + "')]");

    @FindBy(xpath = "//button[@data-test-id='end-date-field']")
    private WebElement returnDateField;

    @FindBy(xpath = "//button[@data-test-id='calendar-action-button']")
    private WebElement noReturnDateBtn;

    @FindBy(xpath = "//label[@data-test-id='checkbox']/span")
    private WebElement openOstrovokCheckbox;

    @FindBy(xpath = "//button[@data-test-id='form-submit']")
    private WebElement searchTicketsBtn;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public HomePage enterCitiesOfDepartureAndDestination(String origin, String destination) throws InterruptedException {
        departureInput.click();
        departureInput.sendKeys(origin, Keys.ENTER);
        Thread.sleep(500);
        departureSuggested.click();
        destinationInput.sendKeys(destination, Keys.ENTER);
        Thread.sleep(500);
        destinationSuggested.click();
        return this;
    }

    public HomePage enterDateOfDeparture() {
        departureDateField.click();
        driver.findElement(departureDate).click();
        return this;
    }

    public HomePage selectNoReturnDate() {
        returnDateField.click();
        noReturnDateBtn.click();
        return this;
    }

    public HomePage uncheckOpenOstrovok() {
        openOstrovokCheckbox.click();
        return this;
    }

    public HomePage searchTickets() {
        searchTicketsBtn.click();
        return this;
    }

}
