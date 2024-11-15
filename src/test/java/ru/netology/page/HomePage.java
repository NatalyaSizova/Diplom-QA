package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    private SelenideElement buttonBuy = $(byText("Купить"));
    private SelenideElement buttonCredit = $(byText("Купить в кредит"));


    public DebitPage goToDebitPage() {
        buttonBuy.click();
        return new DebitPage();
    }

    public CreditPage goToCreditPage() {
        buttonCredit.click();
        return new CreditPage();
    }
}