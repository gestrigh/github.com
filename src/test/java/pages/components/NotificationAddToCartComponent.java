package pages.components;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@Getter
public class NotificationAddToCartComponent {
    private final SelenideElement goToCart = $(byText("Перейти в корзину"));
}
