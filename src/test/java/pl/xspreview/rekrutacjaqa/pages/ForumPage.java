package pl.xspreview.rekrutacjaqa.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ForumPage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@title=\"Post a new topic\"]") // TODO: Make sure this is KISS
    WebElement postNewTopicBtn;

    public ForumPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public NewTopicPage postNewTopic() {
        postNewTopicBtn.click();
        return new NewTopicPage(driver);
    }

    public boolean isForumEmpty() {

        String emptyForum = "There are no topics or posts in this forum";

        try {
            By byXpath = By.xpath("//*[contains(text(),\"" + emptyForum + "\")]");

            WebElement myDynamicElement = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.presenceOfElementLocated(byXpath));
            return true;
        }
        catch (Exception e) {
            Assert.fail("Text '" + emptyForum + "' not found");
            return false;
        }
    }
}
