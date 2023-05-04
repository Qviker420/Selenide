import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.CollectionCondition.textsInAnyOrder;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {
    @Test
    public void testOne()
    {
       open("http://the-internet.herokuapp.com/checkboxes");
        $$(By.tagName("input")).first().click();
        $$(By.tagName("input")).first().shouldHave(Condition.type($$(By.tagName("input")).last().getAttribute("type")));
        System.out.println($$(By.tagName("input")).last().getAttribute("type"));
    }
    @Test
    public void testTwo()
    {
        open("http://the-internet.herokuapp.com/dropdown ");
        $("#dropdown").shouldHave(text("Please select an option"));
        $("#dropdown").selectOptionByValue("2");
        $("#dropdown").shouldHave(text("Option 2"));
    }
    @Test
    public void testThree()
    {
        open("https://demoqa.com/text-box");
        $(By.id("userName")).setValue("Name");
        $(By.xpath("//input[@type='email']")).setValue("text@mail.com");
        $("textarea#currentAddress").setValue("adress");
        $$(".form-control").last().setValue("some stuff");

        $("#submit").scrollTo();
        $("#submit").click();

        $$("p.mb-1").shouldHave(textsInAnyOrder("Name", "text@mail.com", "adress", "some stuff"));
    }
}
