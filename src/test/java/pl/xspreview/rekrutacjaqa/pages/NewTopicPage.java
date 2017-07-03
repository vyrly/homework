package pl.xspreview.rekrutacjaqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewTopicPage {
    private WebDriver driver;

    @FindBy(id = "subject")
    WebElement subjectInput;

    @FindBy(id = "message")
    WebElement messageInput;

    @FindBy(name = "post")
    WebElement postBtn;

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
}
