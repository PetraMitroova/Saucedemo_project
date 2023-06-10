package starter.wikipedia;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SerenityJUnit5Extension.class)
class WhenSearchingForTerms {

    /**
     * Define the webdriver instance to be used for these tests
     */
    @Managed(driver = "chrome")
    WebDriver driver;

    /**
     * Navigation actions. This is a UIInteraction class so it will be instantiated automatically by Serenity.
     */
    NavigateActions navigate;

    /**
     * Actions related to searches. This is a UIInteraction class so it will be instantiated automatically by Serenity.
     */
    SearchActions actions;

    /**
     * A page object representing a Wikipedia article that is currently appearing in the browser.
     * Page Objects are automatically initialised by Serenity.
     */
    DisplayedArticle displayedArticle;

    private final String expectedUrlAfterLogin = "https://www.saucedemo.com/inventory.html";
    private final String homeURL = "https://www.saucedemo.com/";
    private final String expectedURLCart = "https://www.saucedemo.com/cart.html";
    private final String expectedURLCheckout = "https://www.saucedemo.com/checkout-step-one.html";
    @Test
    void loginPageValid() {
        navigate.toTheHomePage();
        actions.sendKeysUsername("standard_user");

        actions.sendKeysPassword("secret_sauce");
        actions.clickLoginButton();

        Assertions.assertEquals(expectedUrlAfterLogin,driver.getCurrentUrl());
    }
    @Test
    void loginPageTitle() {
        navigate.toTheHomePage();
        Assertions.assertEquals("Swag Labs",driver.getTitle());

    }
    @Test
    void loginPageProblemUser() {

        navigate.toTheHomePage();
        actions.sendKeysUsername("problem_user");

        actions.sendKeysPassword("secret_sauce");
        actions.clickLoginButton();

        Assertions.assertEquals(expectedUrlAfterLogin, driver.getCurrentUrl());

    }
    @Test
    void logoutPage() {

        navigate.toTheHomePage();
        actions.sendKeysUsername("problem_user");

        actions.sendKeysPassword("secret_sauce");
        actions.clickLoginButton();
        actions.clickMenuButton();
        actions.clickLogoutButton();

        Assertions.assertEquals(homeURL, driver.getCurrentUrl());

    }
    @Test
    void existOfCartItem() {

        navigate.toTheHomePage();
        actions.sendKeysUsername("problem_user");

        actions.sendKeysPassword("secret_sauce");
        actions.clickLoginButton();
        actions.clickCartButton();


        Assertions.assertEquals(expectedURLCart, driver.getCurrentUrl());

    }
    @Test

    void existOfContinueShopping() {

        navigate.toTheHomePage();
        actions.sendKeysUsername("problem_user");

        actions.sendKeysPassword("secret_sauce");
        actions.clickLoginButton();
        actions.clickCartButton();
        actions.clickContinueShopping();


        Assertions.assertEquals(expectedUrlAfterLogin, driver.getCurrentUrl());

    }
    @Test

    void existOfCheckOutButton() {

        navigate.toTheHomePage();
        actions.sendKeysUsername("problem_user");

        actions.sendKeysPassword("secret_sauce");
        actions.clickLoginButton();
        actions.clickCartButton();
        actions.clickCheckout();


        Assertions.assertEquals(expectedURLCheckout, driver.getCurrentUrl());

    }

    @Test
    void shoppingCartUncompleetedForm() {

        navigate.toTheHomePage();
        actions.sendKeysUsername("standard_user");

        actions.sendKeysPassword("secret_sauce");
        actions.clickLoginButton();
        actions.clickCartButton();
        actions.clickCheckout();
        actions.putfirstName("");
        actions.clickContinue();
        Assertions.assertEquals(expectedURLCheckout, driver.getCurrentUrl());
        actions.putfirstName("Petra");
        actions.putLastName("");
        actions.clickContinue();
        Assertions.assertEquals(expectedURLCheckout, driver.getCurrentUrl());
        actions.putLastName("Mitroova");
        actions.putPostalCode("");
        actions.clickContinue();
        Assertions.assertEquals(expectedURLCheckout, driver.getCurrentUrl());
        actions.putPostalCode("040 11");
        actions.clickContinue();


        Assertions.assertEquals("https://www.saucedemo.com/checkout-step-two.html", driver.getCurrentUrl());

    }
    @Test
    void checkPriceInCart() {

        navigate.toTheHomePage();
        actions.sendKeysUsername("problem_user");
        actions.sendKeysPassword("secret_sauce");
        actions.clickLoginButton();
        actions.clickToAddToCartSauceLabsBikeLight();
        actions.clickCartButton();
        Assertions.assertEquals("$9.99",actions.checkAPriceInCart());

          }
    @Test
    void check2PricesInCart() {

        navigate.toTheHomePage();
        actions.sendKeysUsername("standard_user");
        actions.sendKeysPassword("secret_sauce");
        actions.clickLoginButton();
        actions.clickToAddToCartSauceLabsBikeLight();
        actions.clickToAddToCartSauceLabsBackpack();
        actions.clickCartButton();



        Assertions.assertEquals("29.99",actions.checkAPriceInCartBackpackbyXpath());
        Assertions.assertEquals("9.99",actions.checkAPriceInCartLightbyXpath());


    }
    @Test
    void checkASumInCart() {

        navigate.toTheHomePage();
        actions.sendKeysUsername("standard_user");

        actions.sendKeysPassword("secret_sauce");
        actions.clickLoginButton();
        actions.clickToAddToCartSauceLabsBikeLight();
        actions.clickToAddToCartSauceLabsBackpack();

        actions.clickCartButton();
        double priceBackpack = Double.parseDouble(actions.checkAPriceInCartBackpackbyXpath());
        double priceBikeLight = Double.parseDouble(actions.checkAPriceInCartLightbyXpath());
        actions.clickCheckout();
        actions.clickContinue();
        actions.putfirstName("Petra");
        actions.clickContinue();
        actions.putLastName("Mitroova");
        actions.clickContinue();
        actions.putPostalCode("040 11");
        actions.clickContinue();

        Assertions.assertEquals(priceBackpack+priceBikeLight, Double.parseDouble(actions.checkATotalPrice()));

}
    @Test
    void shoppingCartCompleetedFormProblemUser() {

        navigate.toTheHomePage();
        actions.sendKeysUsername("problem_user");

        actions.sendKeysPassword("secret_sauce");
        actions.clickLoginButton();
        actions.clickCartButton();
        actions.clickCheckout();
        actions.clickContinue();
        Assertions.assertEquals("Error: First Name is required",actions.errorMessage());
        actions.putfirstName("Petra");
        actions.clickContinue();
        Assertions.assertEquals("Error: Last Name is required",actions.errorMessage());
        actions.putLastName("Mitroova");
        actions.clickContinue();
        Assertions.assertEquals("Error: Postal Code is required",actions.errorMessage());
        actions.putPostalCode("040 11");
        actions.clickContinue();
        Assertions.assertEquals("https://www.saucedemo.com/checkout-step-two.html", driver.getCurrentUrl());

    }

}
