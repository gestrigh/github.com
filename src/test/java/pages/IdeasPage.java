package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
public class IdeasPage {
    private final SelenideElement bloggers = $("[href='/lp/collections_bloggers/']");
    private final SelenideElement outfits = $("[href='/looks/women/']");
}
