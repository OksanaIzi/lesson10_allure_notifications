package tests;

import com.codeborne.selenide.Configuration;
import config.DriverConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.AttachmentHelper.*;

public class TestBase {

    static DriverConfig driverConfig = ConfigFactory.create(DriverConfig.class);

    @BeforeAll
    static void setup(){
//        System.out.println("a");
//        System.out.println(System.getProperties());
//        gradle clean test -Da=b
//        System.out.println(System.getProperty("a"));
//        System.out.println(System.getProperty("a", "c"));
        Configuration.startMaximized = true;
        addListener("AllureSelenide", new AllureSelenide());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;


//        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub/";
//        gradle clean test
//        gradle clean test -Dremote.web.driver="https://user1:1234@selenoid.autotests.cloud/wd/hub/"
//        gradle clean test -Dremote.web.driver="https://%s:%s@selenoid.autotests.cloud/wd/hub/"

        /*
        !!!!!!String.format("https://%s:%s@selenoid.autotests.cloud/wd/hub/", "hello", "world")
            ->
            "https://hello:world@selenoid.autotests.cloud/wd/hub/"
         */

        Configuration.browser = System.getProperty("web.browser", "chrome");

        String remoteWebDriver = System.getProperty("remote.web.driver");

        if(remoteWebDriver != null) {

            String user = driverConfig.remoteWebUser();
            String password = driverConfig.remoteWebPassword();
            Configuration.remote = String.format(remoteWebDriver, user, password);

            System.out.println("User: " + user);
            System.out.println("Password: " + password);
            System.out.println("Remote web driver: " + remoteWebDriver);
            System.out.println("Configuration remote: " + String.format(remoteWebDriver, user, password));
        }
    }

    @AfterEach
    void afterEach(){
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());

        if(System.getProperty("video.storage") != null)
            attachVideo();

        closeWebDriver();
    }
}

