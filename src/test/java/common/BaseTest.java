package common;

import lombok.Getter;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@Getter
public abstract class BaseTest {
    private WebDriver webDriver;

    @Before
    public void setUpDriver() {
        webDriver = new ChromeDriver();
        clearCookiesForTest();
    }

    @After
    public void tearDownDriver() {
        clearCookiesForTest();
        webDriver.close();
        webDriver.quit();
    }

    private void clearCookiesForTest() {
        webDriver.manage().deleteAllCookies();
    }
}
