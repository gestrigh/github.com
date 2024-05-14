package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import pages.BloggersPage;
import pages.IdeasPage;
import pages.components.HeaderComponent;
import pages.components.OutfitsComponent;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;
import static io.qameta.allure.Allure.step;

@Epic("Lamoda web")
@Story("Страница Идеи")
@Feature("Переходы по разделам старницы")
@Tags({@Tag("smoke"), @Tag("ideasPage")})
public class IdeasTest extends BaseTest {
    HeaderComponent headerComponent = new HeaderComponent();
    IdeasPage ideasPage = new IdeasPage();
    BloggersPage bloggersPage = new BloggersPage();
    OutfitsComponent outfitsComponent = new OutfitsComponent();

    @DisplayName("Проверка отображения карточек блоггеров")
    @Owner("rtimofeev")
    @Tag("ideas")
    @Test
    public void testBloggers() {
        step("Открыть раздел Идеи", () -> {
            open("/women-home");
            headerComponent
                    .getIdeaCategory()
                    .click();
        });
        step("Открыть раздел Блоггеры", () -> {
            ideasPage
                    .getBloggers()
                    .click();
        });
        step("Проверить что отображаются карточки блоггеров", () -> {
            bloggersPage.getBloggerCards().shouldHave(sizeGreaterThan(0));
            bloggersPage.getBloggerCards().forEach(element -> element.scrollIntoView(true).shouldBe(visible));
        });
    }

    @DisplayName("Проверка отображения спортивных образов")
    @Owner("rtimofeev")
    @Tag("ideas")
    @Test
    public void testOutfits() {
        step("Открыть раздел Идеи", () -> {
            open("/women-home");
            headerComponent
                    .getIdeaCategory()
                    .click();
        });
        step("Открыть раздел Образы-Спорт", () -> {
            ideasPage
                    .getOutfits()
                    .click();
            outfitsComponent
                    .getTitleOutfits()
                    .get(0)
                    .shouldBe(visible, Duration.ofSeconds(10));
            outfitsComponent
                    .getSportOutfit()
                    .click();
            refresh();
        });
        step("Проверить что отображаются карточки спортивных образов", () -> {
            outfitsComponent
                    .getTitleOutfits()
                    .get(0).shouldHave(text("Спорт"));
        });
    }
}
