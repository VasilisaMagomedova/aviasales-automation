package pages;

import common.DateUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import static common.Constants.*;

public class HomePage extends BasePage {

    @FindBy(xpath = "//input[@data-test-id='origin-input']")
    private WebElement originInput;

    @FindBy(xpath = "//input[@data-test-id='destination-input']")
    private WebElement destinationInput;

    @FindBy(xpath = "//button[@data-test-id='start-date-field']")
    private WebElement startDateField;

    @FindBy(xpath = "//button[@data-test-id='end-date-field']")
    private WebElement endDateField;

    @FindBy(xpath = "//button[@data-test-id='passengers-field']")
    private WebElement passengersField;

    @FindBy(xpath = "//button[@data-test-id='form-submit']")
    private WebElement searchTicketsBtn;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectDepartureDate() {
        startDateField.click();
        // В скобках константа - количество дней вперед от текущей даты
        String departureDate = DateUtils.getFutureDate(DAYS_TO_DEPARTURE);
        WebElement startDate = driver.findElement(By.xpath("//div[contains(@aria-label,'" + departureDate + "')]"));
        startDate.click();
    }

    public void selectArrivalDate() {
        endDateField.click();
        String arrivalDate = DateUtils.getFutureDate(DAYS_TO_ARRIVAL);
        WebElement endDate = driver.findElement(By.xpath("//div[contains(@aria-label,'" + arrivalDate + "')]"));
        endDate.click();
    }

    public HomePage searchTickets(String originValue, String destinationValue) {
        originInput.sendKeys(originValue);
        destinationInput.sendKeys(destinationValue);
        selectDepartureDate();
        selectArrivalDate();
        passengersField.click();

        searchTicketsBtn.click();
        return this;
    }

}
