package rekrutacjaqa.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

public class NewTopicPage {
    private WebDriver driver;

    @FindBy(id = "subject")
    WebElement subjectInput;

    @FindBy(id = "message")
    WebElement messageInput;

    @FindBy(name = "preview")
    WebElement previewBtn;

    @FindBy(name = "post")
    WebElement postBtn;

    @FindBy(className = "error")
    WebElement errorText;

    public NewTopicPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public NewTopicPage enterSubject(String subject) {
        subjectInput.sendKeys(subject);
        return this;
    }

    public NewTopicPage enterContent(String message) {
        messageInput.sendKeys(message);
        return this;
    }

    public ViewTopicPage submitTopic() {
        postBtn.click();
        return new ViewTopicPage(driver);
    }

    public NewTopicPage previewTopic() {
        previewBtn.click();
        return this;
    }

    public boolean isInvalidForm() {
        try {
            By byClass = By.className("error");

            WebElement dynamicElement = (new WebDriverWait(driver, 4))
                    .until(ExpectedConditions.presenceOfElementLocated(byClass));
            if (dynamicElement.getText().contains("The submitted form was invalid. Try submitting again")) {
                return true;
            }
            else {
                Assert.fail("Unknown error while submitting Topic");
                return false;
            }
        }
        catch (TimeoutException e) {
           return false;
        }
    }
}
