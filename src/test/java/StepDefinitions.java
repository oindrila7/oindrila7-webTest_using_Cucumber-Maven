import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Login;

import java.util.concurrent.TimeUnit;


public class StepDefinitions {
    public WebDriver driver;
    WebDriverWait wait;
    Login login;
    @Given("^User visits e-commerce website$")
    public void user_visits_e_commerce_website() throws Exception {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver");
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--headed"); //uncomment if you want to run in headless mode
        driver = new ChromeDriver(ops);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://parabank.parasoft.com/parabank/index.htm");

    }

    @When("^User enters valid email \\\"([^\\\"]*)\\\" and valid password \\\"([^\\\"]*)\\\"$")
    public void user_enters_valid_credentials(String email,String password) throws Exception {
        login=new Login(driver);
        login.doLogin(email, password);
    }

    @Then("^User can logged in successfully$")
    public void user_can_logged_in_successfully() throws Exception {
        wait=new WebDriverWait(driver,40);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"rightPanel\"]/div/div/h1")));
        WebElement lblUserName = driver.findElement(By.xpath("//*[@id=\"rightPanel\"]/div/div/h1"));
        Assert.assertEquals(lblUserName.getText(), "Accounts Overview");
    }
    @When("^User enters invalid email \\\"([^\\\"]*)\\\" and invalid password \\\"([^\\\"]*)\\\"$")
    public void user_enters_invalid_email_and_invalid_password(String email,String password) throws Exception {
        login=new Login(driver);
        login.doLoginForWrongPassword(email, password);
    }
    @Then("^User can not logged in$")
    public void user_can_not_logged_in() throws Exception {
        wait=new WebDriverWait(driver,40);
        WebElement lblInvalidEmail = driver.findElement(By.xpath("//*[@id=\"rightPanel\"]/h1"));
        Assert.assertEquals(lblInvalidEmail.getText(), "Error!");
    }
    @After
    public void closeBrowser() {
        driver.quit();
    }
}