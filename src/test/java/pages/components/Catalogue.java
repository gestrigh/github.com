package pages.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Getter
public class Catalogue {
    private final ElementsCollection productCards = $$(".x-product-card__card")
            .as("Выбрать товар в Каталоге");
    private final ElementsCollection productOldPrice = $$(".x-product-card-description__price-old")
            .as("Старая цена товара");
    private final ElementsCollection productNewPrice = $$(".x-product-card-description__price-new")
            .as("Новая цена товара");
    private final SelenideElement filterProduct = $(byText("Материалы"))
            .as("Фильтр по матреиалу");

    public void checkSaleProductPrice(int index) {
        productCards
                .get(index)
                .click();
    }

}
