package pages.components;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
public class ProductCardComponent {
    private final SelenideElement addToCartBtn = $(".recaptcha._recaptcha_lrjtr_7");
    private final SelenideElement productName = $(".product-title__brand-name");
    private final SelenideElement modelName = $("._modelName_1lw0e_21");
    private final SelenideElement productComposition = $(".ui-product-description-attribute-material_filling");
}
