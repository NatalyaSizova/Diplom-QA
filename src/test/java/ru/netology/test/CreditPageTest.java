package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.CreditPage;
import ru.netology.page.HomePage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CreditPageTest {
    private CreditPage creditPage;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setupTest() {

        open("http://localhost:8080/");
        HomePage HomePage = new HomePage();
        creditPage = HomePage.goToCreditPage();
    }

    @AfterEach
    void cleanDataBase() {
        SQLHelper.cleanDataBase();
    }

    @Test
    @DisplayName("Покупка картой со статусом APPROVED")
    void shouldTestBuyCardForStatusApproved() {

        creditPage.fillInCardInfo(DataHelper.getCardNumberForStatusApproved());
        creditPage.setSuccessNotificationVisible();
        creditPage.setBankApproved();
        assertEquals("APPROVED", SQLHelper.getCreditPaymentStatus());
    }

    @Test
    @DisplayName("Покупка картой со статусом DECLINED")
    void shouldTestBuyCardForStatusDeclined() {

        creditPage.fillInCardInfo(DataHelper.getCardNumberForStatusDeclined());
        creditPage.setErrorNotificationVisible();
        creditPage.setBankRefusal();
        assertEquals("DECLINED", SQLHelper.getCreditPaymentStatus());
    }

    @Test
    @DisplayName("Покупка картой без статуса")
    void shouldTestBuyForCardOfNotStatus() {

        creditPage.fillInCardInfo(DataHelper.getNonStatusCardNumber());
        creditPage.setErrorNotificationVisible();
        assertNull(SQLHelper.getCreditPaymentStatus());
    }

    @Test
    @DisplayName("Покупка картой с пустым полем 'номер карты'")
    void shouldTestThePurchaseWithAnEmptyCardNumberField() {

        creditPage.fillInCardInfo(DataHelper.getCardNumberForEmptyField());
        creditPage.setRequiredFieldVisible();
    }

    @Test
    @DisplayName("Покупка картой со спецсимволами в поле 'номер карты'")
    void shouldTestThePurchaseWithSpecialCharactersInCardNumberField() {

        creditPage.fillInCardInfo(DataHelper.getCardNumberOfSpecialCharacters());
        creditPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Покупка картой с буквами в поле 'номер карты'")
    void shouldTestThePurchaseWithLettersInCardNumberField() {

        creditPage.fillInCardInfo(DataHelper.getCardNumberOfLetter());
        creditPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Покупка картой с номером из 14 цифр")
    void shouldTestTheBuyWithA14DigitCard() {

        creditPage.fillInCardInfo(DataHelper.getCardNumberOf14Digits());
        creditPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Покупка картой с нулевым полем 'Месяц'")
    void shouldTestMonthFieldOfZero() {

        creditPage.fillInCardInfo(DataHelper.getMonthOfZero());
        creditPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Покупка картой с буквами в поле 'Месяц'")
    void shouldTestMonthFieldWithLetters() {

        creditPage.fillInCardInfo(DataHelper.getMonthOfLetter());
        creditPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Проверка пустого поля 'Месяц'")
    void shouldTestEmptyMonthField() {

        creditPage.fillInCardInfo(DataHelper.getMonthEmptyField());
        creditPage.setRequiredFieldVisible();
    }

    @Test
    @DisplayName("Покупка картой с одной цифрой в поле 'Месяц'")
    void shouldTestMonthFieldWithOneDigit() {

        creditPage.fillInCardInfo(DataHelper.getMonthWithOneDigit());
        creditPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Покупка картой со спец.символами в поле 'Месяц'")
    void shouldTestMonthFieldWithSpecialCharacters() {

        creditPage.fillInCardInfo(DataHelper.getMonthWithSpecialCharacters());
        creditPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Проверка со значением > 12 в поле 'Месяц'")
    void shouldTestNotValidMonthField() {

        creditPage.fillInCardInfo(DataHelper.getMonthNotValid());
        creditPage.setInvalidCardExpirationDateMessageVisible();
    }

    @Test
    @DisplayName("Проверка пустого поля 'Год'")
    void shouldTestEmptyYearField() {

        creditPage.fillInCardInfo(DataHelper.getYearEmptyField());
        creditPage.setRequiredFieldVisible();
    }

    @Test
    @DisplayName("Покупка с указанием прошлого года")
    void shouldTestPatsValueForYearField() {

        creditPage.fillInCardInfo(DataHelper.getThePastValueInTheYearField());
        creditPage.setCardExpiredMessageVisible();
    }

    @Test
    @DisplayName("Покупка с буквами в поле 'Год'")
    void shouldTestYearFieldWithLetters() {

        creditPage.fillInCardInfo(DataHelper.getYearWithLetters());
        creditPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Покупка c указанием в поле 'год' более 5 лет от текущего")
    void shouldTestFutureValueForYearField() {

        creditPage.fillInCardInfo(DataHelper.getTheFutureValueInTheYearField());
        creditPage.setInvalidCardExpirationDateMessageVisible();
    }

    @Test
    @DisplayName("Покупка со спец.символами в поле 'Год'")
    void shouldTestYearFieldWithSpecialCharacters() {

        creditPage.fillInCardInfo(DataHelper.getYearWithSpecialCharacters());
        creditPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Покупка с одной цифрой в поле 'Год'")
    void shouldTestYearFieldWithOneDigit() {

        creditPage.fillInCardInfo(DataHelper.getYearWithOneDigit());
        creditPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Проверка пустого поля 'Владелец'")
    void shouldTestEmptyHolderField() {

        creditPage.fillInCardInfo(DataHelper.getHolderEmptyField());
        creditPage.setRequiredFieldVisible();
    }

    @Test
    @DisplayName("Значение поля 'Владелец' состоит из кириллицы")
    void shouldTestHolderInCyrillic() {

        creditPage.fillInCardInfo(DataHelper.getHolderInCyrillic());
        creditPage.setInvalidCharMessageVisible();
    }

    @Test
    @DisplayName("Значение поля 'Владелец' состоит из цифр")
    void shouldTestHolderForDigits() {

        creditPage.fillInCardInfo(DataHelper.getHolderFromDigits());
        creditPage.setInvalidCharMessageVisible();
    }

    @Test
    @DisplayName("Значение поля 'Владелец' состоит из спецсимволов")
    void shouldTestHolderForSpecialCharacters() {

        creditPage.fillInCardInfo(DataHelper.getHolderFromSpecialCharacters());
        creditPage.setInvalidCharMessageVisible();
    }

    @Test
    @DisplayName("Значение поля 'Владелец' фамилия через дефис")
    void shouldTestValidCardHolderDoubleSurname() {

        creditPage.fillInCardInfo(DataHelper.getValidCardHolderDoubleSurname());
        creditPage.setBankApproved();
    }

    @Test
    @DisplayName("Проверка пустого поля 'CVC/CVV'")
    void shouldTestEmptyCVCField() {

        creditPage.fillInCardInfo(DataHelper.getCVCEmptyField());
        creditPage.setRequiredFieldVisible();
    }

    @Test
    @DisplayName("Значение поля состоит из букв")
    void shouldTestCVCOfLetters() {

        creditPage.fillInCardInfo(DataHelper.getCVCOfLetters());
        creditPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Значение поля 'CVC/CVV' состоит из 2 цифр")
    void shouldTestCVCTwoDigits() {

        creditPage.fillInCardInfo(DataHelper.getCVCTwoDigits());
        creditPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Значение поля состоит из спец.символов")
    void shouldTestCVCSpecialCharacters() {

        creditPage.fillInCardInfo(DataHelper.getCVCOfSpecialCharacters());
        creditPage.setInvalidFormatVisible();
    }
}