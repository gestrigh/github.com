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
import pages.CartPage;
import pages.components.*;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Epic("Lamoda web")
@Story("Корзина")
@Feature("Добавление товара в корзину + удаление")
@Tags({@Tag("smoke"), @Tag("cart")})
public class CartTest extends BaseTest {
    HeaderComponent headerComponent = new HeaderComponent();
    CatalogueComponent catalogueComponent = new CatalogueComponent();
    OfferPopupComponent offerPopupComponent = new OfferPopupComponent();
    ProductCardComponent productCardComponent = new ProductCardComponent();
    NotificationAddToCartComponent notificationAddToCartComponent = new NotificationAddToCartComponent();
    CartPage cartPage = new CartPage();
    String productName;
    int productNumber = 0;

    @DisplayName("Добавление товара в корзину")
    @Owner("rtimofeev")
    @Tag("cart")
    @Test
    public void testAddToCart() {
        step("Открыть страницу категории 'Женщинам' -> 'Аксессуары' -> 'Сумки' на Lamoda", () -> {
            open("/women-home");
            headerComponent
                    .getWomenCategory()
                    .click();
            headerComponent
                    .getAccessoriesBtn()
                    .hover();
            headerComponent
                    .getBagsBtn()
                    .click();
        });

        step("Выбрать товар", () -> {
            catalogueComponent
                    .getProductCards()
                    .get(productNumber)
                    .click();
            offerPopupComponent
                    .getClosePopup()
                    .click();
            productName = productCardComponent
                    .getProductName()
                    .getText();
        });

        step("Добавить товар в корзину", () -> productCardComponent
                .getAddToCartBtn()
                .click());

        step("Перейти в корзину и проверить, что товар добавлен", () -> {
            notificationAddToCartComponent
                    .getGoToCart()
                    .click();
            cartPage.getProductTitle().shouldHave(Condition.text(productName));
        });

    }

    @DisplayName("Удаление товара из корзины")
    @Owner("rtimofeev")
    @Tag("cart")
    @Test
    public void testDeleteFromCart() {
        step("Открыть страницу категории 'Женщинам' -> 'Аксессуары' -> 'Сумки' на Lamoda", () -> {
            open("/women-home");
            headerComponent
                    .getWomenCategory()
                    .click();
            headerComponent
                    .getAccessoriesBtn()
                    .hover();
            headerComponent
                    .getBagsBtn()
                    .click();
        });

        step("Выбрать товар", () -> {
            catalogueComponent
                    .getProductCards()
                    .get(productNumber)
                    .click();
            offerPopupComponent
                    .getClosePopup()
                    .click();
        });

        step("Добавить товар в корзину", () -> productCardComponent
                .getAddToCartBtn()
                .click());

        step("Перейти в корзину, удалить товар, проверить что корзина пуста", () -> {
            notificationAddToCartComponent
                    .getGoToCart()
                    .click();
            cartPage
                    .getProductCardInCart()
                    .hover();
            cartPage
                    .getDeleteBtn()
                    .click();
            cartPage
                    .getEmptyCart()
                    .shouldBe(Condition.visible);
        });
    }
}
