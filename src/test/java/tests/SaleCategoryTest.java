package tests;

import org.junit.jupiter.api.Test;
import pages.components.Catalogue;
import pages.components.Header;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class SaleCategoryTest extends BaseTest {
    Header header = new Header();
    Catalogue catalogue = new Catalogue();
    @Test
    public void testSalePrice() {
        step("Открыть раздел Sale%",() ->{
            open("/woman-home");
            header
                    .getSaleBtn()
                    .click();
        });
        step("Проверить что в катологе у товаров есть две цены (старая и новая)",() ->{
            catalogue
                    .getProductOldPrice()
                    .filterBy(visible)
                    .shouldHave(sizeGreaterThan(0));
            catalogue
                    .getProductNewPrice()
                    .filterBy(visible)
                    .shouldHave(sizeGreaterThan(0));
        });
    }
}
