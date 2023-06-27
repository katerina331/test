package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
  private SelenideElement heading = $("[data-test-id=dashboard]");
  private SelenideElement reload = $("[data-test-id=action-reload]");
  private ElementsCollection cards = $$("[data-test-id=action-deposit]");
  private ElementsCollection cardsText = $$(".list__item div");
  private final String balanceStart = "баланс: ";
  private final String balanceFinish = " р.";

  public DashboardPage() {
    heading.shouldBe(visible);
  }

  public DashboardPage reloadDashboardPage() {
    reload.click();
    return new DashboardPage();
  }

  public int getCardBalance(DataHelper.CardInfo numberCard) {
    return extractBalance(cardsText.get(getCardInd(numberCard)).text());
  }

  private int extractBalance(String text) {
    val start = text.indexOf(balanceStart);
    val finish = text.indexOf(balanceFinish);
    val value = text.substring(start + balanceStart.length(), finish);
    return Integer.parseInt(value);
  }

  private int getCardInd(DataHelper.CardInfo numberCard) {
    int number=0;
    for (int num=0; num < cardsText.size();num++){
      if (cardsText.get(num).getAttribute("data-test-id").equals(numberCard.getIdCard())) {
        number=num;
      }
    }
    return number;
  }

  public CardTransferPage transfer(DataHelper.CardInfo numberCard) {
    cards.get(getCardInd(numberCard)).click();
    return new CardTransferPage();
  }
}
