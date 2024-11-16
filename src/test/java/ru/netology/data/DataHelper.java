package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private static final Faker fakerLangEn = new Faker(new Locale("en"));
    private static final Faker fakerLangRu = new Faker(new Locale("ru"));

    public static CardInfo getCardNumberForStatusApproved() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getCurrentYear(), getValidHolder(), getValidCVV());
    }

    public static CardInfo getCardNumberForStatusDeclined() {
        return new CardInfo("4444 4444 4444 4442", getValidMonth(), getCurrentYear(), getValidHolder(), getValidCVV());
    }

    public static CardInfo getCardNumberForEmptyField() {
        return new CardInfo("", getValidMonth(), getCurrentYear(), getValidHolder(), getValidCVV());
    }

    public static CardInfo getCardNumberOf14Digits() {
        return new CardInfo("4444 4444 4444 44", getValidMonth(), getCurrentYear(), getValidHolder(), getValidCVV());
    }

    public static CardInfo getCardNumberOfLetter() {
        return new CardInfo("hello", getValidMonth(), getCurrentYear(), getValidHolder(), getValidCVV());
    }

    public static CardInfo getNonStatusCardNumber() {
        return new CardInfo(getRandomCardNumber(), getValidMonth(), getCurrentYear(), getValidHolder(), getValidCVV());
    }

    public static CardInfo getCardNumberOfSpecialCharacters() {
        return new CardInfo("!#$%^", getValidMonth(), getCurrentYear(), getValidHolder(), getValidCVV());
    }

    public static CardInfo getMonthOfLetter() {
        return new CardInfo("4444 4444 4444 4441", "hi", getCurrentYear(), getValidHolder(), getValidCVV());
    }

    public static CardInfo getMonthWithOneDigit() {
        return new CardInfo("4444 4444 4444 4441", "9", getCurrentYear(), getValidHolder(), getValidCVV());
    }

    public static CardInfo getMonthOfZero() {
        return new CardInfo("4444 4444 4444 4441", "00", getCurrentYear(), getValidHolder(), getValidCVV());
    }

    public static CardInfo getMonthEmptyField() {
        return new CardInfo("4444 4444 4444 4441", "", getCurrentYear(), getValidHolder(), getValidCVV());
    }

    public static CardInfo getMonthNotValid() {
        return new CardInfo("4444 4444 4444 4441", "13", getAlwaysValidYear(), getValidHolder(), getValidCVV());
    }

    public static CardInfo getMonthWithSpecialCharacters() {
        return new CardInfo("4444 4444 4444 4441", "!%", getCurrentYear(), getValidHolder(), getValidCVV());
    }

    public static CardInfo getYearEmptyField() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), "", getValidHolder(), getValidCVV());
    }

    public static CardInfo getYearWithSpecialCharacters() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), "^&", getValidHolder(), getValidCVV());
    }

    public static CardInfo getYearWithOneDigit() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), "9", getValidHolder(), getValidCVV());
    }

    public static CardInfo getThePastValueInTheYearField() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getPastYear(), getValidHolder(), getValidCVV());
    }

    public static CardInfo getTheFutureValueInTheYearField() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getFutureYear(), getValidHolder(), getValidCVV());
    }

    public static CardInfo getYearWithLetters() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), "bye", getValidHolder(), getValidCVV());
    }

    public static CardInfo getHolderEmptyField() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getAlwaysValidYear(), "", getValidCVV());
    }

    public static CardInfo getHolderInCyrillic() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getAlwaysValidYear(), getHolderRusLang(), getValidCVV());
    }

    public static CardInfo getHolderFromDigits() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getAlwaysValidYear(), "123458", getValidCVV());
    }

    public static CardInfo getHolderFromSpecialCharacters() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getCurrentYear(), "!@#$$%^", getValidCVV());
    }

    public static CardInfo getValidCardHolderDoubleSurname() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getCurrentYear(), "Natalya Sizova-Veber", getValidCVV());
    }

    public static CardInfo getCVCEmptyField() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getCurrentYear(), getValidHolder(), "");
    }

    public static CardInfo getCVCOfSpecialCharacters() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getCurrentYear(), getValidHolder(), "^&*");
    }

    public static CardInfo getCVCTwoDigits() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getCurrentYear(), getValidHolder(), "88");
    }

    public static CardInfo getCVCOfLetters() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getCurrentYear(), getValidHolder(), "нло");
    }

    public static String getValidMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getPastYear() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getCurrentYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getAlwaysValidYear() {
        return LocalDate.now().plusYears(2).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getFutureYear() {
        return LocalDate.now().plusYears(6).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getValidHolder() {
        return fakerLangEn.name().firstName().toUpperCase() + " " + fakerLangEn.name().lastName().toUpperCase();
    }

    public static String getHolderRusLang() {
        return fakerLangRu.name().fullName();
    }

    public static String getRandomCardNumber() {
        return fakerLangEn.business().creditCardNumber();
    }

    public static String getValidCVV() {
        return fakerLangEn.number().digits(3);
    }

    @Data
    @AllArgsConstructor
    public static class CardInfo {
        private String cardNumber;
        private String month;
        private String year;
        private String holder;
        private String cvc;
    }
}