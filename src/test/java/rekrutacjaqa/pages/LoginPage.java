package rekrutacjaqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private static final String loginPageURL = "http://rekrutacjaqa.02.xspreview.pl/";

    @FindBy(id = "username")
    WebElement usernameLoginInput;

    @FindBy(id="password")
    WebElement passwordLoginInput;

    @FindBy(name="login")
    WebElement loginSubmitBtn;

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginPage open() {
        driver.get(loginPageURL);
        return this;
    }

    public LoginPage enterUserLogin(String login) {
        usernameLoginInput.click();
        usernameLoginInput.sendKeys(login);
        return this;
    }

    public LoginPage enterUserPassword(String password) {
        passwordLoginInput.click();
        passwordLoginInput.sendKeys(password);
        return this;
    }

    public BoardIndexPage submitLoginCredentials() {
        loginSubmitBtn.click();
        return new BoardIndexPage(driver);
    }

}
