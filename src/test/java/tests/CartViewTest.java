package tests;

import ShareDataBrowser.Hooks;
import org.testng.annotations.Test;
import pages.AddToCartPage;
import pages.CartViewPage;

import java.text.DecimalFormat;

import static org.testng.Assert.assertEquals;

public class CartViewTest extends Hooks {

    CartViewPage cartViewPage;

    @Test
    public void productInCartViewTest() {
        int count = 2;
        new AddToCartPage(getDriver()).gotoPageAndEnterData(count + "");

        cartViewPage = new CartViewPage(getDriver());

        cartViewPage.clickOnCart();
        cartViewPage.waitForCartToShow();

        assertEquals(cartViewPage.getCheckOutButtonText(), "Proceed to Checkout");
        assertEquals(cartViewPage.getProductPrice(), "$" + new DecimalFormat("#.00").format(33.60 * count));


    }
}
