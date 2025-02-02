package tests;

import io.qameta.allure.Step;
import org.testng.annotations.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static common.Constants.AVIASALES_HOME_PAGE;
import static utils.TestValues.testWrongDestination;

public class NegativeTest extends BaseTest {

    @Test(description = "Поиск билетов без пункта назначения")
    public void searchTicketsWithoutDestinationTest() {
        openAviasalesStep();
        selectDepartureDateStep();
        searchTicketsStep();
        checkPromptEnterDestinationDisplayedStep();
    }

    @Step("Открыть главную страницу Aviasales")
    public void openAviasalesStep() {
        basePage.open(AVIASALES_HOME_PAGE);
    }

    @Step("Выбрать дату вылета в будущем")
    public void selectDepartureDateStep() {
        homePage.enterDateOfDeparture();
    }

    @Step("Нажать на кнопку 'Найти билеты'")
    public void searchTicketsStep() {
        homePage.searchTickets();
    }

    @Step("Проверить наличие плашки 'Укажите город прибытия'")
    public void checkPromptEnterDestinationDisplayedStep() {
        assertThat(homePage.showPromptEnterDestination()).isTrue();
    }



    @Test(description = "Поиск билетов с несуществующим пунктом назначения")
    public void searchTicketsWithWrongDestinationTest() {
        openAviasalesStep();
        enterWrongDestinationCityStep();
        checkPromptNothingFoundDisplayedStep();
    }

    @Step("Ввести в поле 'Куда' некорректные данные")
    public void enterWrongDestinationCityStep() {
        homePage.enterWrongDestinationCity(testWrongDestination);
    }

    @Step("Проверить отображение сообщения 'Ничего не нашлось'")
    public void checkPromptNothingFoundDisplayedStep() {
        assertThat(homePage.showPromptNothingFound()).isTrue();
    }

}
