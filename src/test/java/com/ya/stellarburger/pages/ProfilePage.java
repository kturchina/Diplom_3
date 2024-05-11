package com.ya.stellarburger.pages;

import org.openqa.selenium.By;

public class ProfilePage {
    public final static By navProfile = By.xpath("//a[@href = '/account/profile']");
    public final static By navHistory = By.xpath("//a[@href = '/account/order-history']");
    public final static By navLogout = By.xpath("//button[text() = 'Выход']");

    public final static By historyItem = By.xpath("//li[starts-with(@class, 'OrderHistory_listItem')]");

    //todo: bug: all inputs have same name attribute
    //edit option not implemented?
    public final static By prNameInp = By.xpath("//label[text() = 'Имя']/following-sibling::input");
    public final static By prEmailInp = By.xpath("//label[text() = 'Email']/following-sibling::input");
    public final static By prPasswordInp = By.xpath("//input[@name = 'Пароль']");
}
