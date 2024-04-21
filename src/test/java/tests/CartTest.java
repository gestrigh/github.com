package tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.components.*;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class CartTest extends BaseTest {
    Header header = new Header();
    Catalogue catalogue = new Catalogue();
    OfferPopup offerPopup = new OfferPopup();
    ProductCard productCard = new ProductCard();
    NotificationAddToCart notificationAddToCart = new NotificationAddToCart();
    CartPage cartPage = new CartPage();
    String productName;
    int productNumber = 0;

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
