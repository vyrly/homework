package pl.xspreview.rekrutacjaqa.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeletePostConfirmationPage {
    private WebDriver driver;

    @FindBy(id = "delete_permanent")
    WebElement deletePermamentCb;

    @FindBy(name = "confirm")
    WebElement confirmBtn;

    public DeletePostConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void performPermanentDeletion(){
        deletePermamentCb.click();
        confirmBtn.click();
    }
}
