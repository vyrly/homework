package rekrutacjaqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rekrutacjaqa.Utils.DateTime;

public class ViewTopicPage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@class=\"topic-title\"]/a")
    WebElement topicTitle;

    // Post Content
    @FindBy(xpath = "//*[@class=\"content\"]")
    WebElement topicContent;

    @FindBy(className = "author")
    WebElement topicdateTime;

    @FindBy(xpath = "//*[@class=\"author\"]//*[@class=\"username\"]")
    WebElement postUsername;

    // Post profile
    @FindBy(xpath = "//*[@class=\"postprofile\"]//*[@class=\"username\"]")
    WebElement postProfileUsername;

    @FindBy(xpath = "//*[@class=\"profile-posts\"]/a")
    WebElement profilePostsNumber;

    // Go back to ForumPage
    @FindBy(xpath = "//*[@class=\"jumpbox-return\"]/a")
    WebElement returnToForumLink;

    // Delete Topic
    @FindBy(xpath = "//*[@title=\"Delete post\"]")
    WebElement deleteTopicBtn;

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

    public boolean checkPostAuthor(String username) {
        if (postUsername.getText().contains(username) && postProfileUsername.getText().contains(username)){
            return true;
        } else {
            return false;
        }
    }

    public long getNumberOfUserPosts() {
        return Long.parseLong(profilePostsNumber.getText());
    }

    public ForumPage returnToForum() {
        returnToForumLink.click();
        return new ForumPage(driver);
    }

    public String getTopicDateTime() {
        DateTime dateTime = new DateTime();
        return dateTime.getDateTime(topicdateTime.getText());
    }
}
