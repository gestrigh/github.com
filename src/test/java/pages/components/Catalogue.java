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
    private final ElementsCollection productCardTitle = $$(".x-product-card-description__product-name")
            .as("Имя товара");
    private final ElementsCollection filterProduct = $$("._item_pjvgk_2")
            .as("Фильтр");

}
