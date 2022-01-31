import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BookRepositoryScenario {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get("https://www.bookdepository.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        WebElement logo = driver.findElement(By.cssSelector("img[alt='Bookdepository.com']"));
        WebElement signInButton = driver.findElement(By.cssSelector("[class='mob-nav-account'] a[href='/account/login/to/account']"));
        WebElement searchField = driver.findElement(By.cssSelector("input[name='searchTerm']"));
        WebElement navigationMenu = driver.findElement(By.cssSelector("[class*='mob-nav-shop dropdown']"));
        WebElement banner = driver.findElement(By.cssSelector("div[class*='lock-wrap promo']"));
        WebElement cartButton = driver.findElement(By.cssSelector("[class='right-section'] [href='/basket']"));


        try {
            logo.isDisplayed();
            signInButton.isDisplayed();
            searchField.isDisplayed();
            navigationMenu.isDisplayed();
            banner.isDisplayed();
            cartButton.isDisplayed();

            searchField.sendKeys("lord of the rings");
            searchField.submit();

            WebElement addToCartButton = driver.findElement(By.cssSelector(".btn-wrap *.add-to-basket"));
            addToCartButton.click();
            WebElement popUpCloseButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".modal-content .close")));
            popUpCloseButton.click();

            WebElement itemCountInCart = driver.findElement(By.xpath("//div[@class='right-section']//span[@class='item-count']"));
            Assertions.assertEquals("1", itemCountInCart.getText(), "Quantity of products in the cart is not as expected");

            WebElement product = driver.findElement(By.cssSelector(".book-item .title a"));
            product.click();
            WebElement productDescription = driver.findElement(By.cssSelector(".item-excerpt"));
            productDescription.isDisplayed();
        }
        finally {
            driver.quit();
        }
    }
}
