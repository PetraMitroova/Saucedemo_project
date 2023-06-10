package starter.wikipedia;

import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class SearchActions extends UIInteractions {

    @Step("Search by keyword '{0}'")
    public void searchBy(String keyword) {
        $("#searchInput").sendKeys(keyword,Keys.ENTER);
    }

    @Step
    public void sendKeysUsername(String username){
        getDriver().findElement(By.id("user-name")).sendKeys(username);
    }

    @Step
    public void sendKeysPassword(String password){
        getDriver().findElement(By.id("password")).sendKeys(password);
    }

    @Step
    public void clickLoginButton(){
        getDriver().findElement(By.id("login-button")).click();
    }
    @Step
    public void clickMenuButton(){
        getDriver().findElement(By.id("react-burger-menu-btn")).click();

    }
    @Step
    public void clickLogoutButton(){
        getDriver().findElement(By.id("logout_sidebar_link")).click();
    }
    @Step
    public void clickCartButton(){
        getDriver().findElement(By.id("shopping_cart_container")).click();

    }
    @Step
    public void clickContinueShopping(){
        getDriver().findElement(By.id("continue-shopping")).click();

    }
    @Step
    public void clickCheckout(){
        getDriver().findElement(By.id("checkout")).click();

    }
    @Step
    public void putfirstName(String name){
        getDriver().findElement(By.id("first-name")).sendKeys(name);

    }@Step
    public void putLastName(String lastName){
        getDriver().findElement(By.id("last-name")).sendKeys(lastName);

    }@Step
    public void putPostalCode(String postalCode){
        getDriver().findElement(By.id("postal-code")).sendKeys(postalCode);

    }
    @Step
    public void clickContinue (){
        getDriver().findElement(By.id("continue")).click();

    }
    @Step
    public String errorMessage(){
        return getDriver().findElement(By.xpath("/html/body/div/div/div/div[2]/div/form/div[1]/div[4]/h3")).getText();

    }
    @Step
    public void clickToAddToCartSauceLabsBikeLight(){
        getDriver().findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();

    }
    @Step
    public String checkAPriceInCart(){
       return  getDriver().findElement(By.xpath("//div[@class=\"inventory_item_price\"]")).getText();

    }
    @Step
    public void clickToAddToCartSauceLabsBackpack(){
        getDriver().findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

    }

    @Step
    public String checkAPriceInCartBackpackbyXpath(){
        return  getDriver().findElement(By.xpath("/html/body/div/div/div/div[2]/div/div[1]/div[4]/div[2]/div[2]/div")).getText()
                .replace("$","");

    }
    @Step
    public String checkAPriceInCartLightbyXpath(){
        return  getDriver().findElement(By.xpath("/html/body/div/div/div/div[2]/div/div[1]/div[3]/div[2]/div[2]/div")).getText()
                .replace("$","");

    }
    @Step
    public String checkATotalPrice(){
        return  getDriver().findElement(By.className("summary_subtotal_label")).getText()
                .replace("Item total: $","");

    }





}
