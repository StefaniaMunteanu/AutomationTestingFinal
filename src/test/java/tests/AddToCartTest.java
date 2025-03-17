package tests;

import ShareDataBrowser.Hooks;
import org.testng.annotations.Test;
import pages.AddToCartPage;

import static org.testng.Assert.assertEquals;


public class AddToCartTest extends Hooks {
    AddToCartPage addToCartPage;

    @Test
    public void addToCartTestCorrectData() {
        addToCartPage = new AddToCartPage(getDriver());
        addToCartPage.gotoPageAndEnterData("1");

        assertEquals(addToCartPage.getConfirmationMessage(), "You added Sylvia Capri to your shopping cart.");
        assertEquals(addToCartPage.getCartCounter(), "1");
    }

    @Test
    public void addToCartTestMultipleProducts() {
        addToCartPage = new AddToCartPage(getDriver());
        addToCartPage.gotoPageAndEnterData("5");

        assertEquals(addToCartPage.getConfirmationMessage(), "You added Sylvia Capri to your shopping cart.");
        assertEquals(addToCartPage.getCartCounter(), "5");
    }

    @Test
    public void addToCartWithoutSizeColorFailTest() {
        addToCartPage = new AddToCartPage(getDriver());
        addToCartPage.gotoBuyProduct();
        addToCartPage.clickOnAddToCart();

        assertEquals(addToCartPage.getCartCounter(), "");
        assertEquals(addToCartPage.getColorErrorMessage(), "This is a required field.");
        assertEquals(addToCartPage.getSizeErrorMessage(), "This is a required field.");

    }
}
