package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import pages.components.Catalogue;
import pages.components.Header;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Epic("Lamoda web")
@Story("Страница Скидки")
@Feature("Проверка скидки у товара")
@Tags({@Tag("smoke"), @Tag("saleCategory")})
public class SaleCategoryTest extends BaseTest {
    Header header = new Header();
    Catalogue catalogue = new Catalogue();

    @DisplayName("Проверка отображения скидки у скидочного товара")
    @Owner("rtimofeev")
    @Tag("salePrice")
    @Test
    public void testSalePrice() {
        step("Открыть раздел Sale%", () -> {
            open("/woman-home");
            header
                    .getSaleBtn()
                    .click();
        });
        step("Проверить что в катологе у товаров есть две цены (старая и новая)", () -> {
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
