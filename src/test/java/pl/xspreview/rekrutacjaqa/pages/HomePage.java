package pl.xspreview.rekrutacjaqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    @FindBy(id="username_logged_in")
    WebElement usernameLoggedIn;

    @FindBy(className="forumtitle")
    WebElement forumLink;

    public HomePage(WebDriver driver) {
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

}
