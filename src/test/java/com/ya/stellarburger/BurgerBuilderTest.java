package com.ya.stellarburger;

import com.ya.stellarburger.pages.BurgerBuilderPage;
import java.util.stream.Collectors;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BurgerBuilderTest extends BaseSeleniumTest {

    public static Point navToIngredients(String sectionTitle, By handler) throws InterruptedException {
        wd.findElement(handler).click();

        Thread.sleep(1500);

        return wd.findElement(By.xpath("//h2[text() = '" + sectionTitle + "']")).getLocation();
    }

    @Test
    public void testIngredientsNav() throws InterruptedException {
        var pSalsas = navToIngredients("Соусы", BurgerBuilderPage.ingredientsNavSalas);
        var pMains = navToIngredients("Начинки", BurgerBuilderPage.ingredientsNavMains);
        var pBuns = navToIngredients("Булки", BurgerBuilderPage.ingredientsNavBuns);

        assertEquals(pSalsas.y, pMains.y);
        assertEquals(pBuns.y, pMains.y);
    }

    @Test
    public void testBunSelect() {
        assertEquals("0", wd.findElement(BurgerBuilderPage.constructorTotalPrice).getText());

        var ingredients = wd.findElements(BurgerBuilderPage.ingredients);

        var buns = ingredients.stream().filter(i -> i.getText().contains("булка")).collect(Collectors.toList());
        assertFalse(buns.isEmpty());

        buns.forEach(bun -> {
            new Actions(wd).dragAndDrop(bun, wd.findElement(BurgerBuilderPage.constructorDropArea)).perform();
            var bunPrice = Long.parseLong(bun.findElement(BurgerBuilderPage.ingredientPrice).getText());

            //price
            assertEquals(String.valueOf(bunPrice*2), wd.findElement(BurgerBuilderPage.constructorTotalPrice).getText());

            //qty
            buns.stream()
                    .filter(b -> b != bun)
                    .forEach(b -> assertEquals("0", b.findElement(BurgerBuilderPage.ingredientQty).getText()));
            assertEquals("2", bun.findElement(BurgerBuilderPage.ingredientQty).getText());
        });
    }
}
