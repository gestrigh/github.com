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

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static java.lang.String.format;

@Epic("Lamoda web")
@Story("Поиск товаров")
@Feature("Поиск товаров с применением фильтра и без")
@Tags({@Tag("smoke"), @Tag("search")})
public class SearchTest extends BaseTest {
    HeaderComponent headerComponent = new HeaderComponent();
    CatalogueComponent catalogueComponent = new CatalogueComponent();
    FilterComponent filterComponent = new FilterComponent();
    OfferPopupComponent offerPopupComponent = new OfferPopupComponent();
    ProductCardComponent productCardComponent = new ProductCardComponent();
    String skirt = "Юбка Женская";
    int productNumber = 1;

    @DisplayName("Поиск товара с применением фильтра")
    @Owner("rtimofeev")
    @Tag("search")
    @Test
    public void testFilterSearchSkirt() {
        step("Ввод в поискоевое поле 'Юбка женская' ", () -> {
            open("/women-home");
            headerComponent
                    .getSearch()
                    .setValue(skirt)
                    .pressEnter();
        });
        step("Применяем фильтр по материалу 'Вискоза'", () -> {
            catalogueComponent
                    .getFilterProduct()
                    .get(1)
                    .click();
            filterComponent
                    .getMainMaterialViscose()
                    .click();
            filterComponent
                    .getAcceptBtn()
                    .click();
        });
        step("Открыть карточку товара", () -> {
            catalogueComponent
                    .getProductCards()
                    .get(productNumber)
                    .click();
            offerPopupComponent
                    .getClosePopup()
                    .click();
        });
        step("Проверить что в составе товара есть материал 'Вискоза'", () -> productCardComponent
                .getProductComposition()
                .shouldHave(Condition.text("Вискоза")));
    }

    @DisplayName("Поиск товара")
    @Owner("rtimofeev")
    @Tag("search")
    @ValueSource(strings = {"юбка", "рубашка", "джинсы"})
    @ParameterizedTest(name = "Заполнение поля поиска значением \"{0}\"")
    public void testSearch(String setValue) {
        step(format("Ввод в поискоевое поле %s", setValue), () -> {
            open("/women-home");
            headerComponent
                    .getSearch()
                    .setValue(setValue)
                    .pressEnter();
        });
        step(format("Проверить что в каталоге отображается нужный товар %s", setValue), () -> {
            catalogueComponent
                    .getProductCardTitle()
                    .get(0)
                    .shouldHave(Condition.text(setValue));
        });
    }
}
