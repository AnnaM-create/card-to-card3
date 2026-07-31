package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {
    private SelenideElement loginInput = $("[data-test-id='login'] input");
    private SelenideElement passwordInput = $("[data-test-id='password'] input");
    private SelenideElement loginButton = $("[data-test-id='action-login']");

    public static LoginPage openPage() {
        open("http://localhost:9999");
        return new LoginPage();
    }

    public VerificationPage validLogin(String login, String password) {
        loginInput.setValue(login);
        passwordInput.setValue(password);
        loginButton.click();
        return new VerificationPage();
    }
}