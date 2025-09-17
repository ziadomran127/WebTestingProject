package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductsPage;
import pages.ProductDetailPage;

public class VerifyAllProductsTest extends BaseTest {

    @Test
    public void testVerifyAllProductsPage() {
        HomePage home = new HomePage(driver);
        ProductsPage products = new ProductsPage(driver);
        ProductDetailPage productDetail = new ProductDetailPage(driver);

        // Step 3: Verify home page is visible
        Assert.assertTrue(home.isHomePageVisible(), "Home page is NOT visible!");

        // Step 4: Click on 'Products' button
        home.clickProductsButton();

        // Step 5: Verify user is navigated to ALL PRODUCTS page
        Assert.assertTrue(products.isAllProductsPageVisible(), "ALL PRODUCTS page is NOT visible!");

        // Step 6: Verify products list is visible
        Assert.assertTrue(products.isProductsListVisible(), "Products list is NOT visible!");

        // Step 7: Click on 'View Product' of first product
        products.clickFirstProductViewButton();

        // Step 9: Verify product details are visible
        Assert.assertTrue(productDetail.areProductDetailsVisible(), "Product details are NOT visible!");
    }
}
