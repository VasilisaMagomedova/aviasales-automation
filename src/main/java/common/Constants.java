package common;

public class Constants {

    public static final int PAGE_LOAD_TIMEOUT = 30;
    public static final int IMPLICIT_WAIT = 10;

    public static final String AVIASALES_HOME_PAGE = "https://www.aviasales.ru/";

    // В скобках количество дней вперед от текущей даты для выбора даты отправления
    public static final String DEPARTURE_DATE = DateUtils.getFutureDate(10);

}
