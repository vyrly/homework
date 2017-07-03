package pl.xspreview.rekrutacjaqa.tests;

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
import pl.xspreview.rekrutacjaqa.pages.*;

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
    public void logInTest() {
        // Given
        String user = "user20";
        String pass = "6PS2jYvFR";

        String subject = "Zażółć gęślą jaźń";
        String content = "Lorem ipsum dolor sit amet enim";

        // When
        HomePage homePage = loginPage.enterUserLogin(user)
                .enterUserPassword(pass)
                .submitLoginCredentials();

        Assert.assertEquals(user, homePage.getLoggedInUser());

        ForumPage forum20Page = homePage.openForum();
        NewTopicPage newTopicPage = forum20Page.postNewTopic();
        ViewTopicPage viewTopicPage = newTopicPage.enterSubject(subject)
                .enterContent(content)
                .submitTopic();
        // Then
        Assert.assertEquals(subject, viewTopicPage.getTopicTitle());
        Assert.assertEquals(content, viewTopicPage.getTopicContent());
        // Author
        // TODO
        // DateTime
        // TODO

        // Clean
        viewTopicPage.deleteTopic().performPermanentDeletion();
        Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("This post has been deleted successfully"));

        Assert.assertTrue(forum20Page.isForumEmpty());
    }
}
