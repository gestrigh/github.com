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
    Header header = new Header();
    Catalogue catalogue = new Catalogue();
    OfferPopup offerPopup = new OfferPopup();
    ProductCard productCard = new ProductCard();
    NotificationAddToCart notificationAddToCart = new NotificationAddToCart();
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
            header
                    .getWomenCategory()
                    .click();
            header
                    .getAccessoriesBtn()
                    .hover();
            header
                    .getBagsBtn()
                    .click();
        });

        step("Выбрать товар", () -> {
            catalogue
                    .getProductCards()
                    .get(productNumber)
                    .click();
            offerPopup
                    .getClosePopup()
                    .click();
            productName = productCard
                    .getProductName()
                    .getText();
        });

        step("Добавить товар в корзину", () -> productCard
                .getAddToCartBtn()
                .click());

        step("Перейти в корзину и проверить, что товар добавлен", () -> {
            notificationAddToCart
                    .getGoToCart()
                    .click();
            cartPage.getCartList().shouldHave(Condition.text(productName));
        });

    }

    @DisplayName("Удаление товара из корзины")
    @Owner("rtimofeev")
    @Tag("cart")
    @Test
    public void testDeleteFromCart() {
        step("Открыть страницу категории 'Женщинам' -> 'Аксессуары' -> 'Сумки' на Lamoda", () -> {
            open("/women-home");
            header
                    .getWomenCategory()
                    .click();
            header
                    .getAccessoriesBtn()
                    .hover();
            header
                    .getBagsBtn()
                    .click();
        });

        step("Выбрать товар", () -> {
            catalogue
                    .getProductCards()
                    .get(productNumber)
                    .click();
            offerPopup
                    .getClosePopup()
                    .click();
        });

        step("Добавить товар в корзину", () -> productCard
                .getAddToCartBtn()
                .click());

        step("Перейти в корзину, удалить товар, проверить что корзина пуста", () -> {
            notificationAddToCart
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
