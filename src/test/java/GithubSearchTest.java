import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;

public class GithubSearchTest {

    @BeforeAll
    static void configure() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = "firefox";
    }

    @Test
    void selenideWikiSearch() {
        open("/selenide/selenide");
        $("#wiki-tab").click();
        $$(".markdown-body ul li a").shouldHave(itemWithText("Soft assertions"));
        $$(".markdown-body ul li a").findBy(text("Soft assertions")).click();
        $("#wiki-body").shouldHave(text("""
                 @ExtendWith({SoftAssertsExtension.class})
                 class Tests {
                   @Test
                   void test() {
                     Configuration.assertionMode = SOFT;
                     open("page.html");
                     $("#first").should(visible).click();
                     $("#second").should(visible).click();
                   }
                 }"""));
    }
}
