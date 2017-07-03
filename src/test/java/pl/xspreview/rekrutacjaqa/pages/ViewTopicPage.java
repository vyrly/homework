package pl.xspreview.rekrutacjaqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewTopicPage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@title=\"Delete post\"]") // TODO: Make sure this is KISS
    WebElement deleteTopicBtn;

    @FindBy(xpath = "//*[@class=\"topic-title\"]/a")
    WebElement topicTitle;

    @FindBy(xpath = "//*[@class=\"content\"]")
    WebElement topicContent;

    public ViewTopicPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public DeletePostConfirmationPage deleteTopic(){
        deleteTopicBtn.click();
        return new DeletePostConfirmationPage(driver);
    }

    public String getTopicTitle() {
        return topicTitle.getText();
    }

    public String getTopicContent() {
        return topicContent.getText();
    }
}
