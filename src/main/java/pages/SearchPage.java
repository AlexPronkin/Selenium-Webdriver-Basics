package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {
    @FindBy(css = ".btn-wrap *.add-to-basket")
    private WebElement addToCartButton;

    @FindBy(css = ".modal-content .close")
    private WebElement popupCloseButton;

    @FindBy(xpath = "//div[@class='right-section']//span[@class='item-count']")
    private WebElement itemCountInCart;

    @FindBy(css = ".book-item .title a")
    private WebElement productTitle;

    public void clickAddToCartButton() {
        addToCartButton.click();
    }

    public void clickClosePopupButton() {
        popupCloseButton.click();
    }

    public void clickProductTitle() {
        productTitle.click();
    }

    public String getItemCountInCart() {
        return itemCountInCart.getText();
    }

    public WebElement getPopupCloseButtonElement(){return popupCloseButton;}

    public SearchPage(WebDriver driver) {
        super(driver);
    }
}
