import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.Screenshot;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


@Listeners({ ScreenShooter.class })
public class SelenideBasics2 {
    @BeforeMethod
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.reportsFolder = "src/main/resources/Reports";
    }
    @Test
    public void test1()
    {
        SoftAssert softAssert = new SoftAssert();

        open("https://demoqa.com/books");
        List<SelenideElement> booksOreilly = $$(".rt-tr-group");
        List<SelenideElement> booksJSO = new ArrayList<SelenideElement>();
        for (SelenideElement e: booksOreilly)
        {
            if(e.find(By.xpath("//div[contains(text(), 'O'\Reilly Media')]")) && e.find(By.xpath("//div[contains(text(), 'JavaScript')]")))
            {
                booksJSO.add(e);
            }
        }
        //aq ' simbolo ver chavsvi stringshi verafrit, nervebi momeshala


        int expectedNumber = 10;
        int actualNumber = booksJSO.size();

        softAssert.assertEquals(expectedNumber, actualNumber);
        softAssert.assertEquals("'Learning JavaScript Design Patterns", booksJSO.get(0).getText());


        booksJSO.stream().forEach(e -> e.find(By.tagName("a")).click());
    }

    @Test
    public void Test2()
    {
        open("https://demoqa.com/books");

        List<SelenideElement> matchingBooks = $$(".rt-tbody [role='row']")
                .filterBy($(".rt-td:nth-child(3)"), text("O'Reilly Media"))
                .filterBy($(".rt-td:nth-child(1)"), text("Javascript"))
                .shouldHave(sizeGreaterThan(0));

        matchingBooks.forEach(book -> {
            SelenideElement image = book.$(".inventory_image");
            image.shouldBe(visible);
            image.shouldNotHave(attribute("src", ""));
        });
    }
}
