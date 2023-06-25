package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CardTransferPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement transferSum = $("[data-test-id=amount] input");
    private SelenideElement transferCard = $("[data-test-id=from] input");
    private SelenideElement transferMoney = $("[data-test-id=action-transfer]");

    public CardTransferPage() {
        heading.shouldBe(visible);
    }

    public DashboardPage transferCash(DataHelper.CardInfo number, DataHelper.CardTransferInfo sumTransfer) {
        transferSum.sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME,Keys.DELETE));
        transferSum.setValue(sumTransfer.toString());
        transferCard.sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME,Keys.DELETE));
        transferCard.setValue(number.toString());
        transferMoney.click();
        return new DashboardPage();
    }
}
