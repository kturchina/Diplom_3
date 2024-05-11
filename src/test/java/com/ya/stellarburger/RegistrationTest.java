package com.ya.stellarburger;

import com.ya.stellarburger.pages.AuthPage;
import com.ya.stellarburger.pages.BurgerBuilderPage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.Map;
import java.util.Properties;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlToBe;

public class RegistrationTest extends BaseSeleniumTest {
    public static final long SUFFIX = Instant.now().getEpochSecond();
    public static final String USER_NAME = "kt_"+SUFFIX;
    public static final String USER_EMAIL = "kt_"+SUFFIX+"@ya.ru";
    public static final String USER_PASSWORD = "kt_newPassw0rd";

    private WebElement gotoRegistration() {
        wd.findElement(BurgerBuilderPage.loginButton).click();
        ww().until(presenceOfElementLocated(AuthPage.actionButton));
        wd.findElement(AuthPage.loginNavReg).click();

        return new WebDriverWait(wd, ofSeconds(3)).until(presenceOfElementLocated(AuthPage.actionButton));
    }

    @Test
    public void testRegistrationInvalidData() {
        var regButton = gotoRegistration();

        wd.findElement(AuthPage.regEmailInp).sendKeys("not_email_string");
        wd.findElement(AuthPage.regNameInp).sendKeys(USER_NAME);
        var pwdButton = wd.findElement(AuthPage.regPasswordInp);
        pwdButton.sendKeys("123");
        regButton.click();

        ww().until(textToBe(AuthPage.errorMessage, "Некорректный пароль"));

        pwdButton.sendKeys("123456");//min password length
        regButton.click();

        //standard ui message on submit error
        ww().until(textToBe(AuthPage.errorMessage, "Такой пользователь уже существует"));
    }

    @Test
    public void testRegistration() {
        var regButton = gotoRegistration();

        assertEquals("Зарегистрироваться", regButton.getText());

        wd.findElement(AuthPage.regEmailInp).sendKeys(USER_EMAIL);
        wd.findElement(AuthPage.regNameInp).sendKeys(USER_NAME);
        var passwordInp = wd.findElement(AuthPage.regPasswordInp);
        passwordInp.sendKeys(USER_PASSWORD);

        //check password visibility toggle works
        assertEquals("password", passwordInp.getAttribute("type"));
        wd.findElement(AuthPage.regPasswordInpToggle).click();
        assertEquals("text", passwordInp.getAttribute("type"));
        wd.findElement(AuthPage.regPasswordInpToggle).click();
        assertEquals("password", passwordInp.getAttribute("type"));

        regButton.click();

        ww().until(urlToBe(HOME_URL+"/login"));

        var loginButton = wd.findElement(AuthPage.actionButton);
        assertEquals("Войти", loginButton.getText());

        wd.findElement(AuthPage.loginEmailInp).sendKeys(USER_EMAIL);
        wd.findElement(AuthPage.loginPasswordInp).sendKeys(USER_PASSWORD);
        loginButton.click();

        ww().until(urlToBe(HOME_URL+"/"));
        ww().until(presenceOfElementLocated(BurgerBuilderPage.orderButton));

        storeAccount();
    }

    private void storeAccount() {
        try {
            new Properties() {{putAll(Map.of(
                    "stellarburger.email", USER_EMAIL,
                    "stellarburger.password", USER_PASSWORD
                ));}}
                .store(new FileOutputStream("stellarburger.properties"), null);
        }
        catch (IOException ignore) {
            fail("Failed to store account data");
        }
    }
}
