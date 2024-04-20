package tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import pages.components.*;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;


public class SearchTest extends BaseTest {
    Header header = new Header();
    Catalogue catalogue = new Catalogue();
    OfferPopup offerPopup = new OfferPopup();
    Filter filter = new Filter();
    ProductCard productCard = new ProductCard();
    String skirt = "Юбка Женская";
    int productNumber = 1;
    String productBrand = "New Balance";
    String productModel = " Кеды 550";

    @Test
    public void testSearchViscoseSkirt() {
        step("Ввод в поискоевое поле 'Юбка женская' ", () -> {
            open("/women-home");
            header
                    .getSearch()
                    .setValue(skirt)
                    .pressEnter();
        });
        step("Применяем фильтр по материалу", () -> {
            catalogue
                    .getFilterProduct()
                    .click();
            filter
                    .getMainMaterialViscose()
                    .click();
            filter
                    .getAcceptBtn()
                    .click();
        });
        step("Открыть карточку товара", () -> {
            catalogue
                    .getProductCards()
                    .get(productNumber)
                    .click();
            offerPopup
                    .getClosePopup()
                    .click();
        });
        step("Проверить что товар имеет материал 'Вискоза'", () -> {
            productCard
                    .getProductComposition()
                    .shouldHave(Condition.text("Вискоза"));
        });
    }

    @Test
    public void testSearchNewBalance550() {
        step("Ввод в поискоевое поле New Balance 550", () -> {
            open("/women-home");
            header
                    .getSearch()
                    .setValue(productBrand)
                    .setValue(productModel)
                    .pressEnter();
        });
        step("Открыть карточку товара", () -> {
            catalogue
                    .getProductCards()
                    .get(productNumber)
                    .click();
            offerPopup
                    .getClosePopup()
                    .click();
        });
        step("Проверить что это нужный товар", () -> {
            productCard
                    .getProductName()
                    .shouldHave(Condition.text(productBrand));
            productCard
                    .getModelName()
                    .shouldHave(Condition.text(productModel));
        });
    }
}
