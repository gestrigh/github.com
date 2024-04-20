package pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
public class Filter {
    private final SelenideElement mainMaterialViscose = $(".vue-recycle-scroller__item-wrapper")
            .findAll("div")
            .findBy(Condition.text("Вискоза"))
            .as("Фильтр основного материала Вискоза");
    private final SelenideElement acceptBtn = $(".x-button_primaryNewFilled")
            .as("Кнопка Применить");
}
