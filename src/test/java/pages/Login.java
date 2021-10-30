package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {
    public WebDriver driver;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div[1]/div/form/div[1]/input")
    WebElement txtName;
    @FindBy(xpath = "//*[@id=\"loginPanel\"]/form/div[2]/input")
    WebElement txtPassword;
    @FindBy(xpath="//*[@id=\"loginPanel\"]/form/div[3]/input")
    WebElement btnSubmitLogin;

    public Login(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void doLogin(String email,String password) throws InterruptedException {
        txtName.sendKeys(email);
        txtPassword.sendKeys(password);
        btnSubmitLogin.click();


    }
    public void doLoginForWrongPassword(String email,String password) throws InterruptedException {
        txtName.sendKeys(email);
        txtPassword.sendKeys(password);
        btnSubmitLogin.click();


    }

}
