package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import dev.failsafe.internal.util.Assert;
import lombok.val;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
  private SelenideElement heading = $("[data-test-id=dashboard]");
  private SelenideElement reload = $("[data-test-id=action-reload]");
  private ElementsCollection cards = $$("[data-test-id=action-deposit]");
  private ElementsCollection cardsText = $$(".list__item");
  private final String balanceStart = "баланс: ";
  private final String balanceFinish = " р.";
  private final String numberStart = "**** **** **** ";
  private final String numberFinish = ", баланс:";

  public DashboardPage() {
    heading.shouldBe(visible);
  }

  public DashboardPage reloadDashboardPage() {
    reload.click();
    return new DashboardPage();
  }

  public int getCardBalance(String numberCard) {
    return extractBalance(cardsText.get(getCardNum(numberCard)).text());
  }

  private int extractBalance(String text) {
    val start = text.indexOf(balanceStart);
    val finish = text.indexOf(balanceFinish);
    val value = text.substring(start + balanceStart.length(), finish);
    return Integer.parseInt(value);
  }

  private String extractNumber(String text) {
    val start = text.indexOf(numberStart);
    val finish = text.indexOf(numberFinish);
    val value = text.substring(start + numberStart.length(), finish);
    return value;
  }

  private int getCardNum(String numberCard) {
    int number=0;
    String lastNum = numberCard.substring(12, 16);
    for (int num=0; num < cardsText.size();num++){
      if (extractNumber(cardsText.get(num).text()).equals(lastNum)){
        number=num;
      }
    }
    return number;
  }

  public CardTransferPage transfer(DataHelper.CardInfo numberCard) {
    cards.get(getCardNum(numberCard.getNumber())).click();
    return new CardTransferPage();
  }

  public void CheckBalance(DataHelper.CardInfo numberCard) {
    var a = getCardBalance(numberCard.getNumber());
    var b = DataHelper.getFirstCardInfo().getBalance();
    if (a!=b) {new RuntimeException("Баланс не соответствует " + a + " Текущий не равен данному " + b);}
  }
}
