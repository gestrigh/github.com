package pages;

import com.codeborne.selenide.ElementsCollection;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$$;

@Getter
public class BloggersPage {
    private final ElementsCollection bloggerCards = $$(".js-product-link")
            .as("Карточка блоггера");
}
