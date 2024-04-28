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
import pages.components.Header;
import pages.components.Outfits;

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
    Header header = new Header();
    IdeasPage ideasPage = new IdeasPage();
    BloggersPage bloggersPage = new BloggersPage();
    Outfits outfits = new Outfits();

    @DisplayName("Проверка отображения карточек блоггеров")
    @Owner("rtimofeev")
    @Tag("bloggers")
    @Test
    public void testBloggers() {
        step("Открыть раздел Идеи", () -> {
            open("/women-home");
            header
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
    @Tag("outfits")
    @Test
    public void testOutfits() {
        step("Открыть раздел Идеи", () -> {
            open("/women-home");
            header
                    .getIdeaCategory()
                    .click();
        });
        step("Открыть раздел Образы-Спорт", () -> {
            ideasPage
                    .getOutfits()
                    .click();
            outfits
                    .getSportOutfit()
                    .click();
            refresh();
        });
        step("Проверить что отображаются карточки спортивных образов", () -> {
            outfits
                    .getTitleOutfits()
                    .get(0).shouldHave(text("Спорт"));
        });
    }
}
