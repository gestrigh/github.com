package pages.components;

import com.codeborne.selenide.ElementsCollection;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$$;

@Getter
public class Catalogue {
    private final ElementsCollection productCards = $$(".x-product-card__card")
            .as("Выбрать товар в Каталоге");

    public void checkSaleProductPrice(int index){
        productCards
                .get(index)
                .click();
    }
}
