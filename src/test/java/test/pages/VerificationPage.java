package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeInput = $("[data-test-id='code'] input");
    private SelenideElement verifyButton = $("[data-test-id='action-verify']");

    public pages.DashboardPage validVerify(String verificationCode) {
        codeInput.setValue(verificationCode);
        verifyButton.click();
        return new pages.DashboardPage();
    }
}