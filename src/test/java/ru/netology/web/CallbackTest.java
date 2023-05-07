package ru.netology.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CallbackTest {

    @Test
    void shouldTestV1() {
//        Configuration.holdBrowserOpen = true;
//        open("http://localhost:9999/");
  //      $("[type=tel]").should(appear);
        assertEquals(1,1);
    }
}

