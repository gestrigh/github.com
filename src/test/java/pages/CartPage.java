package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
public class CartPage {
    private final SelenideElement cartList = $("._package_1qhqp_37");
}
