package com.ya.stellarburger.pages;

import org.openqa.selenium.By;

public class BurgerBuilderPage {
    public final static By loginButton = By.xpath("//button[text() = 'Войти в аккаунт']");
    public final static By orderButton = By.xpath("//button[text() = 'Оформить заказ']");
    public final static By profileLink = By.xpath("//a[@href = '/account']");
    public final static By logo = By.xpath("//div[starts-with(@class, 'AppHeader_header__logo')]");

    public final static By ingredients = By.xpath("//a[starts-with(@class, 'BurgerIngredient_ingredient')]");
    public final static By ingredientsNavBuns = By.xpath("//span[text() = 'Булки']/parent::*");
    public final static By ingredientsNavSalas = By.xpath("//span[text() = 'Соусы']/parent::*");
    public final static By ingredientsNavMains = By.xpath("//span[text() = 'Начинки']/parent::*");

    public final static By constructorDropArea = By.xpath("//section[starts-with(@class, 'BurgerConstructor_basket')]");
    public final static By constructorIngredientDelete = By.xpath("//div[@class = 'constructor-element']//*[starts-with(@class, 'constructor-element__action')]");
    public final static By constructorIngredient = By.xpath("//div[@class = 'constructor-element']");
    public final static By constructorTotalPrice = By.xpath("//div[starts-with(@class, 'BurgerConstructor_basket__totalContainer')]/p");
    public final static By constructorBuns = By.xpath("//ul[starts-with(@class, 'BurgerConstructor_basket__list')]/li");

    public final static By modalClose = By.xpath("//button[starts-with(@class, 'Modal_modal__close')]");
    public final static By modal = By.xpath("//section[starts-with(@class, 'Modal_modal_opened')]");
    public final static By modalTitle = By.xpath("//h2[starts-with(@class, 'Modal_modal__title')]");

    public final static By ingredientPrice = By.xpath(".//p[starts-with(@class, 'BurgerIngredient_ingredient__price')]");
    public final static By ingredientQty = By.xpath(".//p[starts-with(@class, 'counter_counter')]");
}
