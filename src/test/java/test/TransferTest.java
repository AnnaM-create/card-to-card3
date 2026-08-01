package test;

import data.DataHelper;
import org.junit.jupiter.api.Test;
import pages.DashboardPage;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferTest {

    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        var loginPage = LoginPage.openPage();

        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo.getLogin(), authInfo.getPassword());

        var code = DataHelper.getVerificationCode();
        DashboardPage dashboardPage = verificationPage.validVerify(code.getCode());

        int firstCardBalanceBefore = dashboardPage.getCardBalance(0);
        int secondCardBalanceBefore = dashboardPage.getCardBalance(1);

        int transferAmount = 5000;
        var transferInfo = DataHelper.getTransferInfo(transferAmount, DataHelper.getSecondCardNumber());

        var transferPage = dashboardPage.selectCardToReplenish(0);
        dashboardPage = transferPage.transferMoney(transferInfo.getAmount(), transferInfo.getFromCard());

        int firstCardBalanceAfter = dashboardPage.getCardBalance(0);
        int secondCardBalanceAfter = dashboardPage.getCardBalance(1);

        assertEquals(firstCardBalanceBefore + transferAmount, firstCardBalanceAfter);
        assertEquals(secondCardBalanceBefore - transferAmount, secondCardBalanceAfter);
    }
}