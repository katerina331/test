package ru.netology.web;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebTest {
    @Test
    void shouldTestV1() {
        Configuration.holdBrowserOpen = false;
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Воронеж");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String date = dateFormat.format(Date.valueOf(LocalDate.now().plusDays(4)));
        $("[data-test-id=date] input").doubleClick().sendKeys(date);
        $("[data-test-id=name] input").setValue("Васильев-Иванов Василий");
        $("[data-test-id=phone] input").setValue("+78002225577");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=notification]").should(visible, Duration.ofSeconds(15));
    }
}
