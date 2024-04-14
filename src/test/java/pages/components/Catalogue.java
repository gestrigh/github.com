package pages.components;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
public class Catalogue {
    private final SelenideElement catalogueFirst = $("div.grid__catalog > div:first-child")
            .as("Первый товар в Каталоге");
}
