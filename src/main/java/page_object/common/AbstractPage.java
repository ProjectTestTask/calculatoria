package page_object.common;

import lombok.Data;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

@Data
public abstract class AbstractPage {

    private WebDriver webDriver;
    private WebDriverWait webDriverWait;
    private JavascriptExecutor javascriptExecutor;

    public AbstractPage(final WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriverWait = new WebDriverWait(webDriver, 20);
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        PageFactory.initElements(webDriver, this);
        javascriptExecutor = (JavascriptExecutor) webDriver;
        scrollDownPage();
    }

    private void scrollDownPage() {
        javascriptExecutor.executeScript("window.scrollTo(0, 100);");
    }
}
