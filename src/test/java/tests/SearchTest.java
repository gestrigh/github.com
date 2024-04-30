package tests;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.components.*;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static java.lang.String.format;

@Epic("Lamoda web")
@Story("Поиск товаров")
@Feature("Поиск товаров с применением фильтра и без")
@Tags({@Tag("smoke"), @Tag("search")})
public class SearchTest extends BaseTest {
    Header header = new Header();
    Catalogue catalogue = new Catalogue();
    Filter filter = new Filter();
    OfferPopup offerPopup = new OfferPopup();
    ProductCard productCard = new ProductCard();
    String skirt = "Юбка Женская";
    int productNumber = 1;

    @DisplayName("Поиск товара с применением фильтра")
    @Owner("rtimofeev")
    @Tag("filterSearchSkirt")
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
                    .get(1)
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
        step("Проверить что товар имеет материал 'Вискоза'", () -> productCard
                .getProductComposition()
                .shouldHave(Condition.text("Вискоза")));
    }

    @DisplayName("Поиск товара")
    @Owner("rtimofeev")
    @Tag("searchProduct")
    @ValueSource(strings = {"юбка", "рубашка", "джинсы"})
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
