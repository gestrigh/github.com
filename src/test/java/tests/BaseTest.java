package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.DriverConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class BaseTest {
    @BeforeAll
    static void beforeAll() {
        DriverConfig driverConfig = ConfigFactory.create(DriverConfig.class);
        Configuration.browserSize = driverConfig.browserSize();
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
        //Configuration.remote = driverConfig.browserRemoteUrl();
        //Configuration.browser = driverConfig.browserName();
        //Configuration.browserVersion = driverConfig.browserVersion();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }
}

