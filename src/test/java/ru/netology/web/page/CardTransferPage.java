package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static ru.netology.web.data.DataHelper.*;

public class CardTransferPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement transferSum = $("[data-test-id=amount] input");
    private SelenideElement transferCard = $("[data-test-id=from] input");
    private SelenideElement transferMoney = $("[data-test-id=action-transfer]");
    private SelenideElement errorInfo = $("[data-test-id=error-notification]");

    public CardTransferPage() {
        heading.shouldBe(visible);
    }

    public DashboardPage transferCashValid(CardInfo number, int sumTransfer) {
        transferCash(number, sumTransfer);
        return new DashboardPage();
    }

    public void transferCash(CardInfo number, int sumTransfer) {
        transferSum.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.DELETE));
        transferSum.setValue(String.valueOf(sumTransfer));
        transferCard.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.DELETE));
        transferCard.setValue(number.toString());
        transferMoney.click();
    }

    public void transferCashInvalid(CardInfo number, int sumInvalidTransfer) {
        transferCash(number, sumInvalidTransfer);
        errorInfo.shouldBe(exactText(getTransferErrorInfo().toString()), Duration.ofSeconds(15)).shouldBe(visible);
    }
}
