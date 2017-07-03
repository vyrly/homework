package rekrutacjaqa.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import rekrutacjaqa.pages.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class NewTopicTest {
    @Parameters
    public static Collection getBrowser(){
        return Arrays.asList(new Object[][] {{"Firefox"},{"Chrome"}});
    }

    @Parameter
    public String browser;

    private WebDriver driver;
    private LoginPage loginPage;

    @Before
    public void setUpTest() {
        System.out.println("Browser:"+ browser);
        if(browser.equalsIgnoreCase("Firefox")){
            driver = new FirefoxDriver();
        } else if(browser.equalsIgnoreCase("Chrome")){
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

        loginPage = new LoginPage(driver).open();
    }

    @After
    public void tearDownTest() {
        driver.quit();
    }

    @Test
    public void addNewTopicTest() {
        // Given
        String user = "user20";
        String pass = "6PS2jYvFR";

        String subject = "Zażółć gęślą jaźń";
        String content = "Lorem ipsum dolor sit amet enim";

        // When
        BoardIndexPage boardIndexPage = loginPage.enterUserLogin(user)
                .enterUserPassword(pass)
                .submitLoginCredentials();

        Assert.assertEquals(user, boardIndexPage.getLoggedInUser());

        ForumPage forumPage = boardIndexPage.openForum();
        NewTopicPage newTopicPage = forumPage.postNewTopic();

        newTopicPage.enterSubject(subject)
                     .enterContent(content);

        newTopicPage.previewTopic();

        ViewTopicPage viewTopicPage = newTopicPage.submitTopic();
        while (newTopicPage.isInvalidForm()) { // Submit again - sometimes error my be displayed if submitting too fast
            viewTopicPage = newTopicPage.submitTopic();
        }

        Assert.assertEquals(subject, viewTopicPage.getTopicTitle());
        Assert.assertEquals(content, viewTopicPage.getTopicContent());
        Assert.assertTrue(viewTopicPage.checkPostAuthor(user));
        Assert.assertEquals(1, viewTopicPage.getNumberOfUserPosts());

        String dateTime = viewTopicPage.getTopicDateTime();

        forumPage = viewTopicPage.returnToForum();

        Assert.assertEquals(subject, forumPage.getTopicTitle());
        Assert.assertEquals(dateTime, forumPage.getLastPostDateTime());
        Assert.assertTrue(forumPage.getTopicDateTime().contains(dateTime));
        Assert.assertEquals(1, forumPage.getNumberOfViews());

        boardIndexPage = forumPage.returnToBoardIndex();

        Assert.assertEquals(1, boardIndexPage.getNumberOfTopics());
        Assert.assertEquals(1, boardIndexPage.getNumberOfPosts());

        forumPage = boardIndexPage.openForum();

        viewTopicPage = forumPage.openTopic();

        // Clean
        viewTopicPage.deleteTopic().performPermanentDeletion();
        Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("This post has been deleted successfully"));

        Assert.assertTrue(forumPage.isForumEmpty());
    }
}
