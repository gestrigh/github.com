package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
public class CartPage {
    private final SelenideElement productTitle = $("._title_6anq4_20");
    private final SelenideElement deleteBtn = $("[aria-label='Убрать из корзины']");
    private final SelenideElement productCardInCart = $("._root_ko34a_2");
    private final SelenideElement emptyCart = $("[aria-label='Ваша корзина пуста']");
}
