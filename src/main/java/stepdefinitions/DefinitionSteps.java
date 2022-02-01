package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.ProductPage;
import pages.SearchPage;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DefinitionSteps {

    private static final long DEFAULT_TIMEOUT = 30  ;
    PageFactoryManager pageFactoryManager;
    WebDriver driver;
    HomePage homePage;
    ProductPage productPage;
    SearchPage searchPage;

    @Before
    public void testsSetUp() {
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

    @And("User checks logo visibility")
    public void userChecksLogoVisibility() {
        assertTrue(homePage.isLogoDisplayed());
    }

    @And("User checks Sign-in button visibility")
    public void userChecksSignInButtonVisibility() {
        assertTrue(homePage.isSignInButtonDisplayed());
    }

    @And("User checks navigation menu visibility")
    public void userChecksNavigationMenuVisibility() {
        assertTrue(homePage.isNavigationMenuDisplayed());
    }

    @And("User checks search field visibility")
    public void userChecksSearchFieldVisibility() {
        assertTrue(homePage.isSearchFieldDisplayed());
    }

    @And("User checks banner visibility")
    public void userChecksBannerVisibility() {
        assertTrue(homePage.isBannerDisplayed());
    }

    @And("User checks cart button visibility")
    public void userChecksCartButtonVisibility() {
        assertTrue(homePage.isCartButtonDisplayed());
    }

    @Then("User search for {string}")
    public void userSearchForProduct(String productName) {
        homePage.searchForProduct(productName);
    }

    @And("User click Add to cart button for first product")
    public void userClickAddToCartButton() {
        searchPage = pageFactoryManager.getSearchPage();
        searchPage.clickAddToCartButton();
    }

    @And("User closes confirmation popup")
    public void userClosesConfirmationPopup() {
        searchPage = pageFactoryManager.getSearchPage();
        searchPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchPage.getPopupCloseButtonElement());
        searchPage.clickClosePopupButton();
    }

    @Then("Cart shows {string} item")
    public void cartShowsItem(String productCount) {
        searchPage = pageFactoryManager.getSearchPage();
        assertEquals(productCount, searchPage.getItemCountInCart());
    }

    @And("User clicks on first result product title")
    public void userClicksOnProductTitle() {
        searchPage = pageFactoryManager.getSearchPage();
        searchPage.clickProductTitle();
    }

    @Then("User sees product description")
    public void userSeesProductDescription() {
        productPage = pageFactoryManager.getProductPage();
        assertTrue(productPage.isProductDescriptionDisplayed());
    }

    /*
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);

        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getMenuButton());

        searchPage = pageFactoryManager.getSearchPage();
    */

    @After
    public void tearDown() {
        driver.quit();
    }

}
