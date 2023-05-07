package ru.netology.web;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebTest {
    @Test
    void shouldTestV1() {
        Configuration.holdBrowserOpen = false;
        Configuration.timeout = 10;
        open("localhost:7777");
        $("[type=tel]").should(appear);
        assertEquals(1, 1);
    }
}
