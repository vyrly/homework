package rekrutacjaqa.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import rekrutacjaqa.Utils.DateTime;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForumPage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@title=\"Post a new topic\"]")
    WebElement postNewTopicBtn;

    @FindBy(className = "topictitle")
    WebElement topicTitle;

    @FindBy(css = ".topic-poster.responsive-hide")
    WebElement topicdateTime;

    @FindBy(css = ".topiclist.topics > li > dl > .lastpost > span")
    WebElement lastPostdateTime;

    @FindBy(css = ".topiclist.topics > li > dl > .views")
    WebElement numberOfViews;

    @FindBy(xpath = "//*[@class=\"jumpbox-return\"]/a")
    WebElement returnToBoardIndexLink;

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

            WebElement dynamicElement = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.presenceOfElementLocated(byXpath));
            return true;
        }
        catch (Exception e) {
            Assert.fail("Text '" + emptyForum + "' not found");
            return false;
        }
    }

    public String getTopicTitle() {
        return topicTitle.getText();
    }

    public ViewTopicPage openTopic() {
        topicTitle.click();
        return new ViewTopicPage(driver);
    }

    public String getTopicDateTime() {
        DateTime dateTime = new DateTime();
        return dateTime.getDateTime(topicdateTime.getText());
    }

    public String getLastPostDateTime() {
        DateTime dateTime = new DateTime();
        return dateTime.getDateTime(lastPostdateTime.getText());
    }

    public long getNumberOfViews() {
        String pattern = "\\d+";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(numberOfViews.getText());
        Assert.assertTrue(m.find());
        return Long.parseLong(m.group(0));
    }

    public BoardIndexPage returnToBoardIndex() {
        returnToBoardIndexLink.click();
        return new BoardIndexPage(driver);
    }

}
