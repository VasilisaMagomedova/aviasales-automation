package tests;
import common.DateUtils;
import org.testng.annotations.Test;

import static common.Constants.AVIASALES_HOME_PAGE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static utils.TestValues.testDestination;
import static utils.TestValues.testOrigin;

public class SearchFlightsTest extends BaseTest {

    @Test(description = "Поиск билетов между городами для 1 взрослого")
    public void checkSearchTicketsForAdult() throws InterruptedException {

        basePage.open(AVIASALES_HOME_PAGE);

        homePage.enterCitiesOfDepartureAndDestination(testOrigin, testDestination)
                .enterDateOfDeparture()
                .selectNoReturnDate()
                .uncheckOpenOstrovok()
                .searchTickets();

        Thread.sleep(10000); // время на капчу
        String testSearchDepartureDate = DateUtils.normalizeDate(searchResultsPage.getSearchDepartureDate());
        String testTicketDepartureDate = DateUtils.normalizeDate(searchResultsPage.getTicketDepartureDate());

        assertThat(searchResultsPage.getDepartureCity(testOrigin)).isEqualTo(testOrigin);
        System.out.println("Город отправления совпадает с введенным");

        assertThat(searchResultsPage.getDestinationCity(testDestination)).isEqualTo(testDestination);
        System.out.println("Город прибытия совпадает с введенным");

        assertThat(testTicketDepartureDate).isEqualTo(testSearchDepartureDate);
        System.out.println("Дата отправления в билете совпадает с введенной");

    }

    @Test(description = "Сортировка найденных билетов по цене от дешевых к дорогим",
            dependsOnMethods = {"checkSearchTicketsForAdult"})
    public void sortTicketsByCheapestPriceFirst() throws InterruptedException {

        searchResultsPage.setAcceptCookiesBtn();
        searchResultsPage.sortCheapestFirst();
        Thread.sleep(1000); //ждем сортировку

        assertThat(searchResultsPage.isBadgeCheapestInsideTicket()).isTrue();
        assertThat(searchResultsPage.arePricesSortedAsc()).isTrue();

        System.out.println("Билеты отсортированы по цене - от дешевых к дорогим");

    }

}
