package tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.components.*;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static java.lang.String.format;


public class SearchTest extends BaseTest {
    Header header = new Header();
    Catalogue catalogue = new Catalogue();
    Filter filter = new Filter();
    ProductCard productCard = new ProductCard();
    String skirt = "Юбка Женская";
    int productNumber = 1;

    @Test
    public void testFilterSearchSkirt() {
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
        });
        step("Проверить что товар имеет материал 'Вискоза'", () -> productCard
                    .getProductComposition()
                    .shouldHave(Condition.text("Вискоза")));
    }


    @ValueSource(strings = {"юбка","джинсы","рубашка"})
    @ParameterizedTest(name = "Заполнение поля поиска значением \"{0}\"")
    public void testSearch(String setValue) {
        step(format("Ввод в поискоевое поле %s", setValue), () -> {
            open("/women-home");
            header
                    .getSearch()
                    .setValue(setValue)
                    .pressEnter();
        });
        step(format("проверить что в каталоге отображается нужный товар %s", setValue), () -> {
            catalogue
                    .getProductCardTitle()
                    .get(0)
                    .shouldHave(Condition.text(setValue));
        });
    }
}