package tests;

import org.testng.annotations.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static common.Constants.AVIASALES_HOME_PAGE;
import static utils.TestValues.testOrigin;

public class NegativeTest extends BaseTest {

    @Test(description = "Поиск билетов без пункта назначения")
    public void searchTicketsWithoutDestination() throws InterruptedException {

        basePage.open(AVIASALES_HOME_PAGE);

        homePage.enterDepartureCity(testOrigin)
                .enterDateOfDeparture()
                .selectNoReturnDate()
                .searchTickets();

        assertThat(homePage.showPromptEnterDestination()).isTrue();

    }

}
