package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.DebitPage;
import ru.netology.page.HomePage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DebitPageTest {
    private DebitPage debitPage;

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
        debitPage = HomePage.goToDebitPage();
    }

    @AfterEach
    void cleanDataBase() {
        SQLHelper.cleanDataBase();
    }

    @Test
    @DisplayName("Покупка картой со статусом APPROVED")
    void shouldTestBuyCardForStatusApproved() {

        debitPage.fillInCardInfo(DataHelper.getCardNumberForStatusApproved());
        debitPage.setSuccessNotificationVisible();
        debitPage.setBankApproved();
        assertEquals("APPROVED", SQLHelper.getCreditPaymentStatus());
    }

    @Test
    @DisplayName("Покупка картой со статусом DECLINED")
    void shouldTestBuyCardForStatusDeclined() {

        debitPage.fillInCardInfo(DataHelper.getCardNumberForStatusDeclined());
        debitPage.setErrorNotificationVisible();
        debitPage.setBankRefusal();
        assertEquals("DECLINED", SQLHelper.getCreditPaymentStatus());
    }

    @Test
    @DisplayName("Покупка картой без статуса")
    void shouldTestBuyForCardOfNotStatus() {

        debitPage.fillInCardInfo(DataHelper.getNonStatusCardNumber());
        debitPage.setErrorNotificationVisible();
        assertNull(SQLHelper.getCreditPaymentStatus());
    }

    @Test
    @DisplayName("Покупка картой с пустым полем 'номер карты'")
    void shouldTestThePurchaseWithAnEmptyCardNumberField() {

        debitPage.fillInCardInfo(DataHelper.getCardNumberForEmptyField());
        debitPage.setRequiredFieldVisible();
    }

    @Test
    @DisplayName("Покупка картой со спецсимволами в поле 'номер карты'")
    void shouldTestThePurchaseWithSpecialCharactersInCardNumberField() {

        debitPage.fillInCardInfo(DataHelper.getCardNumberOfSpecialCharacters());
        debitPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Покупка картой с буквами в поле 'номер карты'")
    void shouldTestThePurchaseWithLettersInCardNumberField() {

        debitPage.fillInCardInfo(DataHelper.getCardNumberOfLetter());
        debitPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Покупка картой с номером из 14 цифр")
    void shouldTestTheBuyWithA14DigitCard() {

        debitPage.fillInCardInfo(DataHelper.getCardNumberOf14Digits());
        debitPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Покупка картой с нулевым полем 'Месяц'")
    void shouldTestMonthFieldOfZero() {

        debitPage.fillInCardInfo(DataHelper.getMonthOfZero());
        debitPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Покупка картой с буквами в поле 'Месяц'")
    void shouldTestMonthFieldWithLetters() {

        debitPage.fillInCardInfo(DataHelper.getMonthOfLetter());
        debitPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Проверка пустого поля 'Месяц'")
    void shouldTestEmptyMonthField() {

        debitPage.fillInCardInfo(DataHelper.getMonthEmptyField());
        debitPage.setRequiredFieldVisible();
    }

    @Test
    @DisplayName("Покупка картой с одной цифрой в поле 'Месяц'")
    void shouldTestMonthFieldWithOneDigit() {

        debitPage.fillInCardInfo(DataHelper.getMonthWithOneDigit());
        debitPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Покупка картой со спец.символами в поле 'Месяц'")
    void shouldTestMonthFieldWithSpecialCharacters() {

        debitPage.fillInCardInfo(DataHelper.getMonthWithSpecialCharacters());
        debitPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Проверка со значением > 12 в поле 'Месяц'")
    void shouldTestNotValidMonthField() {

        debitPage.fillInCardInfo(DataHelper.getMonthNotValid());
        debitPage.setInvalidCardExpirationDateMessageVisible();
    }

    @Test
    @DisplayName("Проверка пустого поля 'Год'")
    void shouldTestEmptyYearField() {

        debitPage.fillInCardInfo(DataHelper.getYearEmptyField());
        debitPage.setRequiredFieldVisible();
    }

    @Test
    @DisplayName("Покупка с указанием прошлого года")
    void shouldTestPatsValueForYearField() {

        debitPage.fillInCardInfo(DataHelper.getThePastValueInTheYearField());
        debitPage.setCardExpiredMessageVisible();
    }

    @Test
    @DisplayName("Покупка с буквами в поле 'Год'")
    void shouldTestYearFieldWithLetters() {

        debitPage.fillInCardInfo(DataHelper.getYearWithLetters());
        debitPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Покупка c указанием в поле 'год' более 5 лет от текущего")
    void shouldTestFutureValueForYearField() {

        debitPage.fillInCardInfo(DataHelper.getTheFutureValueInTheYearField());
        debitPage.setInvalidCardExpirationDateMessageVisible();
    }

    @Test
    @DisplayName("Покупка со спец.символами в поле 'Год'")
    void shouldTestYearFieldWithSpecialCharacters() {

        debitPage.fillInCardInfo(DataHelper.getYearWithSpecialCharacters());
        debitPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Покупка с одной цифрой в поле 'Год'")
    void shouldTestYearFieldWithOneDigit() {

        debitPage.fillInCardInfo(DataHelper.getYearWithOneDigit());
        debitPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Проверка пустого поля 'Владелец'")
    void shouldTestEmptyHolderField() {

        debitPage.fillInCardInfo(DataHelper.getHolderEmptyField());
        debitPage.setRequiredFieldVisible();
    }

    @Test
    @DisplayName("Значение поля 'Владелец' состоит из кириллицы")
    void shouldTestHolderInCyrillic() {

        debitPage.fillInCardInfo(DataHelper.getHolderInCyrillic());
        debitPage.setInvalidCharMessageVisible();
    }

    @Test
    @DisplayName("Значение поля 'Владелец' состоит из цифр")
    void shouldTestHolderForDigits() {

        debitPage.fillInCardInfo(DataHelper.getHolderFromDigits());
        debitPage.setInvalidCharMessageVisible();
    }

    @Test
    @DisplayName("Значение поля 'Владелец' состоит из спецсимволов")
    void shouldTestHolderForSpecialCharacters() {

        debitPage.fillInCardInfo(DataHelper.getHolderFromSpecialCharacters());
        debitPage.setInvalidCharMessageVisible();
    }

    @Test
    @DisplayName("Значение поля 'Владелец' фамилия через дефис")
    void shouldTestValidCardHolderDoubleSurname() {

        debitPage.fillInCardInfo(DataHelper.getValidCardHolderDoubleSurname());
        debitPage.setBankApproved();
    }

    @Test
    @DisplayName("Проверка пустого поля 'CVC/CVV'")
    void shouldTestEmptyCVCField() {

        debitPage.fillInCardInfo(DataHelper.getCVCEmptyField());
        debitPage.setRequiredFieldVisible();
    }

    @Test
    @DisplayName("Значение поля состоит из букв")
    void shouldTestCVCOfLetters() {

        debitPage.fillInCardInfo(DataHelper.getCVCOfLetters());
        debitPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Значение поля 'CVC/CVV' состоит из 2 цифр")
    void shouldTestCVCTwoDigits() {

        debitPage.fillInCardInfo(DataHelper.getCVCTwoDigits());
        debitPage.setInvalidFormatVisible();
    }

    @Test
    @DisplayName("Значение поля состоит из спец.символов")
    void shouldTestCVCSpecialCharacters() {

        debitPage.fillInCardInfo(DataHelper.getCVCOfSpecialCharacters());
        debitPage.setInvalidFormatVisible();
    }
}