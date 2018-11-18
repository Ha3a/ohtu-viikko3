package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Stepdefs {

    WebDriver driver = new ChromeDriver();
    String baseUrl = "http://localhost:4567";

    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
    }

    @Given("^command new user is selected$")
    public void newuser_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }
//    Given user with username "lea" with password "salainen1" is successfully created
        @Given("^user with username \"([^\"]*)\" with password \"([^\"]*)\" is successfully created$")
    public void newuser_selected(String user, String pwd) throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        createWith(user, pwd);
//        driver.get(baseUrl);
//        
//        try{ Thread.sleep(120000); } catch(Exception e){}  // suoritus pysähtyy 120 sekunniksi
        
    }
    
//    user with username "aa" and password "bad" is tried to be created
        @Given("^user with username \"([^\"]*)\" and password \"([^\"]*)\" is tried to be created")
    public void newuser_selected_fail(String user, String pwd) throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        createWith(user, pwd);
//        driver.get(baseUrl);
//        
//        try{ Thread.sleep(120000); } catch(Exception e){}  // suoritus pysähtyy 120 sekunniksi
        
    }
    
    
    @When("^username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_and_password_are_given(String username, String password) throws Throwable {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }

    @Then("^system will respond \"([^\"]*)\"$")
    public void system_will_respond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }

    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^incorrect username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void incorrectusername_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^a valid username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered$")
    public void valid_username_and_password_are_given(String username, String password) throws Throwable {
        createWith(username, password);
    }

    @When("^an invalid username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered$")
    public void an_invalid_username_and_password_are_given(String username, String password) throws Throwable {
        createWith(username, password);
    }

        @When("^a valid username \"([^\"]*)\" and a too short password \"([^\"]*)\" and matching password confirmation are entered$")
    public void a_valid_username_and_a_too_short_password_are_given(String username, String password) throws Throwable {
        createWith(username, password);
    }
//    a valid username "liisa" and password "salainen1" and nonmatching password confirmation "salainen2" are entered
            @When("^a valid username \"([^\"]*)\" and password \"([^\"]*)\" and nonmatching password confirmation \"([^\"]*)\" are entered$")
    public void a_valid_username_and_nonmatching_password_are_given(String username, String password, String pwd) throws Throwable {
        createWithFail(username, password, pwd);
    }
    
    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }
//    Welcome to Ohtu Application!

    @Then("^a new user is created$")
    public void user_is_creted() throws Throwable {
        pageHasContent("Welcome to Ohtu Application!");
    }
//    user is not created and error

    @Then("^user is not created and error \"([^\"]*)\" is reported$")
    public void user_is_not_creted(String error) throws Throwable {
        pageHasContent("Create username and give password");
        pageHasContent(error);
        
        
    }

    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    /* helper methods */
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }

    private void createWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
    
        private void createWithFail(String username, String password,String pwd) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(pwd);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
}
