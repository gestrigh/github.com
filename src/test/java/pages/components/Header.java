package pages.components;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@Getter
public class Header {
    private final SelenideElement womenCategory = $("[data-genders='women']")
            .as("Раздел 'Женщинам'");
    private final SelenideElement search = $("[placeholder='Поиск']")
            .as("Строка поиска по разделу");
    private final SelenideElement cartBtn = $("[href='/checkout/cart/']")
            .as("Строка поиска по разделу");
    private final SelenideElement mainBtn = $("[aria-label='Главная']")
            .as("Кнопка Lamoda (Главная)");
    private final SelenideElement clothesBtn = $(byText("Одежда"))
            .as("Кнопка Одежда");
    private final SelenideElement accessoriesBtn = $(byText("Аксессуары"))
            .as("Кнопка Аксессуары");
    private final SelenideElement bagsBtn = $(byText("Сумки"))
            .as("Кнопка Сумки");

}