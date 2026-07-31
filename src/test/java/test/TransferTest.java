package test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.DashboardPage;
import pages.LoginPage;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferTest {

    @BeforeAll
    public static void setUp() {
        Configuration.holdBrowserOpen = true;

        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        prefs.put("autofill.profile_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-popup-blocking", "--disable-notifications");
        Configuration.browserCapabilities = options;
    }

    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        var loginPage = LoginPage.openPage();
        var verificationPage = loginPage.validLogin("vasya", "qwerty123");
        DashboardPage dashboardPage = verificationPage.validVerify("12345");

        int firstCardBalanceBefore = dashboardPage.getCardBalance(0);
        int secondCardBalanceBefore = dashboardPage.getCardBalance(1);

        int transferAmount = 5000;

        var transferPage = dashboardPage.selectCardToReplenish(0);

        dashboardPage = transferPage.transferMoney(transferAmount, "5559 0000 0000 0002");

        int firstCardBalanceAfter = dashboardPage.getCardBalance(0);
        int secondCardBalanceAfter = dashboardPage.getCardBalance(1);

        assertEquals(firstCardBalanceBefore + transferAmount, firstCardBalanceAfter);
        assertEquals(secondCardBalanceBefore - transferAmount, secondCardBalanceAfter);
    }
}