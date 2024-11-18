package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DebitPage {
    private final SelenideElement heading = $$("h3").find(text("Оплата по карте"));
    private final SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $("[placeholder='08']");
    private final SelenideElement yearField = $("[placeholder='22']");
    private final SelenideElement holderField = $(byText("Владелец")).parent().$(".input__control");
    private final SelenideElement cvcField = $("[placeholder='999']");
    private final SelenideElement buttonContinue = $$("button").find(exactText("Продолжить"));

    private final SelenideElement successNOTIF = $(".notification_status_ok");
    private final SelenideElement errorNOTIF = $(".notification_status_error");
    private final SelenideElement requiredField = $(byText("Поле обязательно для заполнения"));
    private final SelenideElement invalidFormat = $(byText("Неверный формат"));
    private final SelenideElement invalidCharMessage = $(byText("Ошибка! Банк отказал в проведении операции."));
    private final SelenideElement invalidCardExpirationDateMessage = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement cardExpiredMessage = $(byText("Истёк срок действия карты"));
    private final SelenideElement bankApproved = $$(".notification__content").findBy(text("Операция одобрена Банком."));
    private final SelenideElement bankRefusal = $$(".notification__content").findBy(text("Ошибка! Банк отказал в проведении операции."));

    public DebitPage() {
        heading.shouldBe(visible);
    }

    public void fillInCardInfo(DataHelper.CardInfo cardInfo) {
        cardNumberField.sendKeys(cardInfo.getCardNumber());
        monthField.sendKeys(cardInfo.getMonth());
        yearField.sendKeys(cardInfo.getYear());
        holderField.sendKeys(cardInfo.getHolder());
        cvcField.sendKeys(cardInfo.getCvc());
        buttonContinue.click();
    }

    public void setSuccessNotificationVisible() {
        successNOTIF.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void setErrorNotificationVisible() {
        errorNOTIF.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void setRequiredFieldVisible() {
        requiredField.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void setInvalidFormatVisible() {
        invalidFormat.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void setInvalidCharMessageVisible() {
        invalidCharMessage.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void setInvalidCardExpirationDateMessageVisible() {
        invalidCardExpirationDateMessage.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void setCardExpiredMessageVisible() {
        cardExpiredMessage.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void setBankApproved() {
        bankApproved.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void setBankRefusal() {
        bankRefusal.shouldBe(visible, Duration.ofSeconds(15));
    }
}