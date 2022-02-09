package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DefinitionSteps {

    private static final long DEFAULT_TIMEOUT = 30;
    PageFactoryManager pageFactoryManager;
    WebDriver driver;
    HomePage homePage;
    ProductPage productPage;
    SearchPage searchPage;
    BasketPage basketPage;
    PaymentPage paymentPage;

    @Before
    public void setUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }

    @And("User opens {string} page")
    public void userOpensHomePage(final String url) {
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
    }

    @And("^(?:User |)checks logo visibility$")
    public void userChecksLogoVisibility() {
        assertTrue(homePage.isLogoDisplayed());
    }

    @And("^(?:User |)checks Sign-in button visibility$")
    public void userChecksSignInButtonVisibility() {
        assertTrue(homePage.isSignInButtonDisplayed());
    }

    @And("^(?:User |)checks navigation menu visibility$")
    public void userChecksNavigationMenuVisibility() {
        assertTrue(homePage.isNavigationMenuDisplayed());
    }

    @And("^(?:User |)checks search field visibility$")
    public void userChecksSearchFieldVisibility() {
        assertTrue(homePage.isSearchFieldDisplayed());
    }

    @And("^(?:User |)checks banner visibility$")
    public void userChecksBannerVisibility() {
        assertTrue(homePage.isBannerDisplayed());
    }

    @And("^(?:User |)checks cart button visibility$")
    public void userChecksCartButtonVisibility() {
        assertTrue(homePage.isCartButtonDisplayed());
    }

    @Then("^(?:User |)search for (.*?)$")
    public void userSearchForProduct(String productName) {
        homePage.searchForProduct(productName);
    }

    @And("^(?:User |)clicks Add to cart button for first product$")
    public void userClickAddToCartButton() {
        searchPage = pageFactoryManager.getSearchPage();
        searchPage.clickAddToCartButton();
    }

    @And("^(?:User |)clicks Close on confirmation popup$")
    public void userClosesConfirmationPopup() {
        searchPage = pageFactoryManager.getSearchPage();
        searchPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchPage.getPopupCloseButtonElement());
        searchPage.clickPopupCloseButton();
    }

    @Then("Cart shows {string} item")
    public void cartShowsItem(String productCount) {
        searchPage = pageFactoryManager.getSearchPage();
        assertEquals(productCount, searchPage.getItemCountInCart());
    }

    @And("^(?:User |)clicks on first result product title$")
    public void userClicksOnProductTitle() {
        searchPage = pageFactoryManager.getSearchPage();
        searchPage.clickProductTitle();
    }

    @Then("^(?:User |)sees product description$")
    public void userSeesProductDescription() {
        productPage = pageFactoryManager.getProductPage();
        assertTrue(productPage.isProductDescriptionDisplayed());
    }

    @And("^(?:User |)clicks BasketCheckout button on confirmation popup$")
    public void clicksBasketCheckoutButtonOnConfirmationPopup() {
        searchPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchPage.getPopupBasketCheckoutElement());
        searchPage.clickPopupBasketCheckoutButton();
        basketPage = pageFactoryManager.getBasketPage();
    }

    @And("^(?:User |)clicks Checkout button on Basket page$")
    public void clicksCheckoutButtonOnBasketPage() {
        basketPage.clickCheckoutButton();
    }

    @Then("Item price and Total price are equal in Order Summary")
    public void itemPriceAndTotalPriceAreEqualInOrderSummary() {
        paymentPage = pageFactoryManager.getPaymentPage();
        paymentPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, paymentPage.getItemPriceElement());
        paymentPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, paymentPage.getTotalPriceElement());
        assertEquals("Item price and total price are not equal", paymentPage.getItemPrice(), paymentPage.getTotalPrice());
    }

    @When("^(?:User |)enters (.*?) email address$")
    public void enteringEmailAddress(String email) {
        paymentPage = pageFactoryManager.getPaymentPage();
        paymentPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, paymentPage.getEmailFieldElement());
        paymentPage.inputEmail(email);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
