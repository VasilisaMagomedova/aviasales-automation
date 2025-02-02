package tests;
import common.DateUtils;
import io.qameta.allure.Step;
import org.testng.annotations.Test;
import utils.RetryAnalyzer;

import static common.Constants.AVIASALES_HOME_PAGE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static utils.TestValues.testDestination;
import static utils.TestValues.testOrigin;

public class SearchFlightsTest extends BaseTest {

    @Test(description = "Поиск билетов между городами для 1 взрослого", retryAnalyzer = RetryAnalyzer.class)
    public void checkSearchTicketsForAdultTest() throws InterruptedException {
        openAviasalesStep();
        enterDepartureCityStep();
        enterDestinationCityStep();
        selectDepartureDateStep();
        selectNoReturnDateStep();
        uncheckOpenOstrovokStep();
        searchTicketsStep();
        checkEqualityInformationOnPreview();
    }

    @Step("Открыть главную страницу Aviasales")
    public void openAviasalesStep() {
        basePage.open(AVIASALES_HOME_PAGE);
    }

    @Step("Ввести город отправления в поле 'Откуда'")
    public void enterDepartureCityStep() throws InterruptedException {
        homePage.enterDepartureCity(testOrigin);
    }

    @Step("Ввести город назначения в поле 'Куда'")
    public void enterDestinationCityStep() throws InterruptedException {
        homePage.enterDestinationCity(testDestination);
    }

    @Step("Выбрать дату вылета в будущем")
    public void selectDepartureDateStep() {
        homePage.enterDateOfDeparture();
    }

    @Step("Выбрать 'Обратный билет не нужен' в дропдауне 'Обратно'")
    public void selectNoReturnDateStep() {
        homePage.selectNoReturnDate();
    }

    @Step("Снять чекбокс с 'Открыть Ostrovok.ru в новой вкладке'")
    public void uncheckOpenOstrovokStep() {
        homePage.uncheckOpenOstrovok();
    }

    @Step("Нажать на кнопку 'Найти билеты'")
    public void searchTicketsStep() throws InterruptedException {
        homePage.searchTickets();
        Thread.sleep(10000); // время на прохождение капчи и отработки поиска
    }

    @Step("Проверить совпадение введенной информации с информацией на превью билета:")
    public void checkEqualityInformationOnPreview() {
        checkEqualityDepartureCityOnPreviewSubStep();
        checkEqualityDestinationCityOnPreviewSubStep();
        checkEqualityDepartureDateOnPreviewSubStep();
    }

    @Step("- города отправления")
    public void checkEqualityDepartureCityOnPreviewSubStep() {
        assertThat(searchResultsPage.getDepartureCity(testOrigin)).isEqualTo(testOrigin);
    }

    @Step("- города прибытия")
    public void checkEqualityDestinationCityOnPreviewSubStep() {
        assertThat(searchResultsPage.getDestinationCity(testDestination)).isEqualTo(testDestination);
    }

    @Step("- даты отправления")
    public void checkEqualityDepartureDateOnPreviewSubStep() {
        String testSearchDepartureDate = DateUtils.normalizeDate(searchResultsPage.getSearchDepartureDate());
        String testTicketDepartureDate = DateUtils.normalizeDate(searchResultsPage.getTicketPreviewDepartureDate());
        assertThat(testTicketDepartureDate).isEqualTo(testSearchDepartureDate);
    }



    @Test(description = "Сортировка найденных билетов по цене от дешевых к дорогим",
            dependsOnMethods = {"checkSearchTicketsForAdultTest"}, retryAnalyzer = RetryAnalyzer.class)
    public void sortTicketsByCheapestPriceFirstTest() throws InterruptedException {
        setAcceptCookiesBtnStep();
        sortCheapestFirstStep();
        checkBadgeCheapestDisplayedStep();
        checkPricesAreSortedAscStep();
    }

    @Step("Принять куки Aviasales")
    public void setAcceptCookiesBtnStep() {
        searchResultsPage.setAcceptCookiesBtn();
    }

    @Step("Нажать на дропдаун 'Сортировка' и выбрать 'Сначала дешевые'")
    public void sortCheapestFirstStep() throws InterruptedException {
        searchResultsPage.sortCheapestFirst();
        Thread.sleep(2000); //ждем сортировку
    }

    @Step("Проверить наличие над превью первого билета плашки 'Самый дешевый'")
    public void checkBadgeCheapestDisplayedStep() {
        assertThat(searchResultsPage.isBadgeCheapestInsideTicket()).isTrue();
    }

    @Step("Проверить сортировку цен на превью билетов по возрастанию")
    public void checkPricesAreSortedAscStep() {
        assertThat(searchResultsPage.arePricesSortedAsc()).isTrue();
    }



    @Test(description = "Просмотр информации о билете",
            dependsOnMethods = {"checkSearchTicketsForAdultTest"}, retryAnalyzer = RetryAnalyzer.class)
    public void viewTicketInformationTest() {
        setAcceptCookiesBtnStep();
        selectTicketStep();
        checkEqualityDepartureInformationStep();
        checkEqualityDestinationInformationStep();
    }

    @Step("Нажать на превью первого билета")
    public void selectTicketStep() {
        searchResultsPage.selectTicket();
    }

    @Step("Проверить совпадение информации об отправлении на превью билета с информацией об отправлении на странице билета:")
    public void checkEqualityDepartureInformationStep() {
        checkEqualityDepartureCitySubStep();
        checkEqualityDepartureTimeSubStep();
        checkEqualityDepartureDateSubStep();
    }

    @Step("- города отправления")
    public void checkEqualityDepartureCitySubStep() {
        assertThat(searchResultsPage.getTicketDepartureCity(testOrigin))
                .isEqualTo(searchResultsPage.getDepartureCity(testOrigin));
    }

    @Step("- времени отправления")
    public void checkEqualityDepartureTimeSubStep() {
        assertThat(searchResultsPage.getTicketDepartureTime())
                .isEqualTo(searchResultsPage.getTicketPreviewDepartureTime());
    }

    @Step("- даты отправления")
    public void checkEqualityDepartureDateSubStep() {
        assertThat(searchResultsPage.getTicketDepartureDate())
                .isEqualTo(searchResultsPage.getTicketPreviewDepartureDate());
    }

    @Step("Проверить совпадение информации о прибытии на превью билета с информацией о прибытии на странице билета:")
    public void checkEqualityDestinationInformationStep() {
        checkEqualityDestinationCitySubStep();
        checkEqualityDestinationTimeSubStep();
        checkEqualityDestinationDateStep();
    }

    @Step("- города прибытия")
    public void checkEqualityDestinationCitySubStep() {
        assertThat(searchResultsPage.getTicketDestinationCity(testDestination))
                .isEqualTo(searchResultsPage.getDestinationCity(testDestination));
    }

    @Step("- времени прибытия")
    public void checkEqualityDestinationTimeSubStep() {
        assertThat(searchResultsPage.getTicketDestinationTime())
                .isEqualTo(searchResultsPage.getTicketPreviewDestinationTime());
    }

    @Step("- даты прибытия")
    public void checkEqualityDestinationDateStep() {
        assertThat(searchResultsPage.getTicketDestinationDate())
                .isEqualTo(searchResultsPage.getTicketPreviewDestinationDate());
    }

}
