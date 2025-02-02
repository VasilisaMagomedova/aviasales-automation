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
        String testTicketDepartureDate = DateUtils.normalizeDate(searchResultsPage.getTicketPreviewDepartureDate());

        assertThat(searchResultsPage.getDepartureCity(testOrigin)).isEqualTo(testOrigin);
        assertThat(searchResultsPage.getDestinationCity(testDestination)).isEqualTo(testDestination);
        assertThat(testTicketDepartureDate).isEqualTo(testSearchDepartureDate);

    }

    @Test(description = "Сортировка найденных билетов по цене от дешевых к дорогим",
            dependsOnMethods = {"checkSearchTicketsForAdult"})
    public void sortTicketsByCheapestPriceFirst() throws InterruptedException {

        searchResultsPage.setAcceptCookiesBtn();
        searchResultsPage.sortCheapestFirst();
        Thread.sleep(1000); //ждем сортировку

        assertThat(searchResultsPage.isBadgeCheapestInsideTicket()).isTrue();
        assertThat(searchResultsPage.arePricesSortedAsc()).isTrue();

    }

    @Test(description = "Просмотр информации о билете",
            dependsOnMethods = {"checkSearchTicketsForAdult"})
    public void viewTicketInformation() {

        searchResultsPage.setAcceptCookiesBtn();
        searchResultsPage.selectTicket();

        assertThat(searchResultsPage.getTicketDepartureCity(testOrigin))
                .isEqualTo(searchResultsPage.getDepartureCity(testOrigin));
        assertThat(searchResultsPage.getTicketDestinationCity(testDestination))
                .isEqualTo(searchResultsPage.getDestinationCity(testDestination));

        assertThat(searchResultsPage.getTicketDepartureTime())
                .isEqualTo(searchResultsPage.getTicketPreviewDepartureTime());
        assertThat(searchResultsPage.getTicketDepartureDate())
                .isEqualTo(searchResultsPage.getTicketPreviewDepartureDate());

        assertThat(searchResultsPage.getTicketDestinationTime())
                .isEqualTo(searchResultsPage.getTicketPreviewDestinationTime());
        assertThat(searchResultsPage.getTicketDestinationDate())
                .isEqualTo(searchResultsPage.getTicketPreviewDestinationDate());

    }

}
