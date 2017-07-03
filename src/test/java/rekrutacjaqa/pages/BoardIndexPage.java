package rekrutacjaqa.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BoardIndexPage {
    private WebDriver driver;

    @FindBy(id="username_logged_in")
    WebElement usernameLoggedIn;

    @FindBy(className="forumtitle")
    WebElement forumLink;

    @FindBy(xpath = "//*[@class=\"topiclist forums\"]//*[@class=\"topics\"]")
    WebElement numberOfTopics;

    @FindBy(xpath = "//*[@class=\"topiclist forums\"]//*[@class=\"topics\"]")
    WebElement numberOfPosts;

    public BoardIndexPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getLoggedInUser(){
        return usernameLoggedIn.getText();
    }

    public ForumPage openForum(){
        forumLink.click();
        return new ForumPage(driver);
    }

    public long getNumberOfTopics() {
        String pattern = "\\d+";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(numberOfTopics.getText());
        Assert.assertTrue(m.find());
        return Long.parseLong(m.group(0));
    }

    public long getNumberOfPosts() {
        String pattern = "\\d+";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(numberOfPosts.getText());
        Assert.assertTrue(m.find());
        return Long.parseLong(m.group(0));
    }
}
