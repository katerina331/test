package ru.netology.web;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CallbackTest {

    @Test
    void shouldTestV1() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        assertEquals(1, 1);
    }
}

