package com.ya.stellarburger.pages;

import org.openqa.selenium.By;

public class AuthPage {
    public final static By actionButton = By.xpath("//form/button");
    public final static By errorMessage = By.xpath("//p[starts-with(@class, 'input__error')]");

    //todo(bug): email and name inputs in registration section have same "name" attribute
    //public final static By regNameInp = By.xpath("//input[@name = 'name']");
    public final static By regNameInp = By.xpath("//label[text() = 'Имя']/following-sibling::input");
    public final static By regEmailInp = By.xpath("//label[text() = 'Email']/following-sibling::input");
    public final static By regPasswordInp = By.xpath("//input[@name = 'Пароль']");
    public final static By regPasswordInpToggle = By.xpath("//input[@name = 'Пароль']/following-sibling::div/*[local-name()='svg']");
    public final static By regNavLogin = By.xpath("//a[@href = '/login']");

    //todo(bug): email input in login section has "name" attribute
    public final static By loginEmailInp = By.xpath("//label[text() = 'Email']/following-sibling::input");
    public final static By loginPasswordInp = By.xpath("//input[@name = 'Пароль']");
    public final static By loginPasswordInpToggle = By.xpath("//input[@name = 'Пароль']/following-sibling::div/*[local-name()='svg']");
    public final static By loginNavReg = By.xpath("//a[@href = '/register']");
    public final static By loginNavReset = By.xpath("//a[@href = '/forgot-password']");

    //todo(bug): email input in reset section has "name" attribute
    public final static By resetEmailInp = By.xpath("//label[text() = 'Email']/following-sibling::input");
}
