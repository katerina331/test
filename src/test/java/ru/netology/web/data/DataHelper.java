package ru.netology.web.data;

import lombok.Value;
import ru.netology.web.page.DashboardPage;


public class DataHelper {
  private DataHelper() {}

  @Value
  public static class AuthInfo {
    private String login;
    private String password;
  }

  public static AuthInfo getAuthInfo() {
    return new AuthInfo("vasya", "qwerty123");
  }

  public static AuthInfo getOtherAuthInfo(AuthInfo original) {
    return new AuthInfo("petya", "123qwerty");
  }

  @Value
  public static class VerificationCode {
    private String code;
  }

  public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
    return new VerificationCode("12345");
  }

  @Value
  public static class CardInfo {
    private String number;
    private int balance;
  }

  public static CardInfo getFirstCardInfo() {
    String card = "5559000000000001";
    int balance = 10000;
    return new CardInfo(card,balance);
  }

  public static CardInfo getSecondCardInfo() {
    String card = "5559000000000002";
    int balance = 10000;
    return new CardInfo(card,balance);
  }

  @Value
  public static class CardTransferInfo {
  private int sumTransfer;
}

  public static CardTransferInfo getFirstSumTransferInfo() {
    return new CardTransferInfo(1000);
  }

  public static CardTransferInfo getSecondSumTransferInfo() {
    return new CardTransferInfo(2000);
  }
}