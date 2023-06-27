package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.*;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.DataHelper.*;

class MoneyTransferTest {
  DashboardPage dashboardPage;
  @BeforeEach
  void setup() {
    LoginPage loginPage = open("http://localhost:9999", LoginPage.class);
    var authInfo = DataHelper.getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    dashboardPage = verificationPage.validVerify(verificationCode);
  }

  @Test
  void shouldTransferMoneyBetweenCards() {
    var numberFirstCard = getFirstCardInfo();
    var numberSecondCard = getSecondCardInfo();
    var balanceFirstCard = dashboardPage.getCardBalance(numberFirstCard);
    var balanceSecondCard = dashboardPage.getCardBalance(numberSecondCard);
    var sum = getSumTransferInfo(balanceFirstCard);
    var expectedBalanceFirstCard = balanceFirstCard + sum;
    var expectedBalanceSecondCard = balanceSecondCard - sum;
    var cardTransferPage = dashboardPage.transfer(numberFirstCard);
    cardTransferPage.transferCashValid(numberSecondCard,sum);
    var actualBalanceFirstCard = dashboardPage.getCardBalance(numberFirstCard);
    var actualBalanceSecondCard = dashboardPage.getCardBalance(numberSecondCard);
    assertEquals(expectedBalanceFirstCard,actualBalanceFirstCard);
    assertEquals(expectedBalanceSecondCard,actualBalanceSecondCard);
  }

  @Test
  void shouldTransferMoneyBetweenCardsBack() {
    var numberFirstCard = getFirstCardInfo();
    var numberSecondCard = getSecondCardInfo();
    var balanceFirstCard = dashboardPage.getCardBalance(numberFirstCard);
    var balanceSecondCard = dashboardPage.getCardBalance(numberSecondCard);
    var sum = getSumTransferInfo(balanceFirstCard);
    var expectedBalanceSecondCard = balanceSecondCard + sum;
    var expectedBalanceFirstCard = balanceFirstCard - sum;
    var cardTransferPage = dashboardPage.transfer(numberFirstCard);
    cardTransferPage.transferCashValid(numberSecondCard,sum);
    var actualBalanceSecondCard = dashboardPage.getCardBalance(numberSecondCard);
    var actualBalanceFirstCard = dashboardPage.getCardBalance(numberFirstCard);
    assertEquals(expectedBalanceSecondCard,actualBalanceSecondCard);
    assertEquals(expectedBalanceFirstCard,actualBalanceFirstCard);
  }
}

