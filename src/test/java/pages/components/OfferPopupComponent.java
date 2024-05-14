package pages.components;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
public class OfferPopupComponent {
    private final SelenideElement closePopup = $("[title='Закрыть']");
}
