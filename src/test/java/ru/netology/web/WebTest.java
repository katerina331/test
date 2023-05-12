package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import entities.RegistrationInfo;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import utils.DataGenerator;
import utils.DateDay;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class WebTest {
    private static Faker faker;

    @Test
    void shouldTestV1() {
        Configuration.holdBrowserOpen = false;
        open("http://localhost:9999");
        RegistrationInfo info = DataGenerator.Registration.generationInfo("ru");
        String date = DateDay.NewDatePlusFormat(4, "dd.MM.yyyy");
        $("[data-test-id=city] input").setValue(info.getCity());
        $("[data-test-id=date] input").doubleClick().sendKeys(date);
        $("[data-test-id=name] input").setValue(info.getName());
        $("[data-test-id=phone] input").setValue(info.getPhone());
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=notification]").should(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldTestV2() {
        Configuration.holdBrowserOpen = false;
        open("http://localhost:9999");
        RegistrationInfo info = DataGenerator.Registration.generationInfo("ru");
        String date = DateDay.NewDatePlusFormat(4, "dd.MM.yyyy");
        $("[data-test-id=city] input").setValue(info.getCity().substring(0, 3));
        $x("//*[contains(text(),'" + info.getCity() + "')]").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.ARROW_DOWN, Keys.ARROW_RIGHT, Keys.ENTER);
        $("[data-test-id=name] input").setValue(info.getName());
        $("[data-test-id=phone] input").setValue(info.getPhone());
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=notification]").shouldHave(Condition.text("Встреча успешно забронирована на " + date), Duration.ofSeconds(15)).should(visible);
    }
}
