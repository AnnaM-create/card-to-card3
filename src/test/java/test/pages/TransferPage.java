package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement amountInput = $("[data-test-id='amount'] input");
    private SelenideElement fromCardInput = $("[data-test-id='from'] input");
    private SelenideElement transferButton = $("[data-test-id='action-transfer']");

    public pages.DashboardPage transferMoney(int amount, String fromCardNumber) {
        amountInput.setValue(String.valueOf(amount));
        fromCardInput.setValue(fromCardNumber);
        transferButton.click();
        return new pages.DashboardPage();
    }
}