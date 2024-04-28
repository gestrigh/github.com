package pages.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Getter
public class Outfits {
    private final SelenideElement sportOutfit = $("[href='/looks/women/?event=41']")
            .as("Раздел Спорт");
    private final ElementsCollection titleOutfits = $$("._title_1kz8o_32")
            .as("Заголовок Образа");
}
