package tests.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginTest extends BaseTest {
    @Test
    public void testLogin() {
        open(baseUrl);
        $("[href='/login']").click();

        $("[href='/login']").shouldHave(Condition.text("Login"));
    }
}
