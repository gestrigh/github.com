package pages.components;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
public class ProductCard {
    private final SelenideElement addToCartBtn = $(".recaptcha._recaptcha_lrjtr_7");
    private final SelenideElement productName = $(".product-title__brand-name");
}
