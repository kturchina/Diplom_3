package com.ya.stellarburger;

import com.ya.stellarburger.pages.AuthPage;
import com.ya.stellarburger.pages.BurgerBuilderPage;
import com.ya.stellarburger.pages.ProfilePage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlToBe;

public class LoginTest extends BaseSeleniumTest {
    public static class AuthData {
        public final String email;
        public final String password;

        AuthData(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }

    public static AuthData getAuthData() {
        try {
            var props = new Properties();
            props.load(new FileInputStream("stellarburger.properties"));
            return new AuthData(props.getProperty("stellarburger.email"), props.getProperty("stellarburger.password"));
        }
        catch (IOException ignore) {}
        fail("Failed to load auth data, run @RegistrationTest to cache account");
        return null;
    }

    public static void doLogin() {
        var authData = getAuthData();
        var loginBtn = ww().until(presenceOfElementLocated(AuthPage.actionButton));
        assertEquals("Войти", loginBtn.getText());

        wd.findElement(AuthPage.loginEmailInp).sendKeys(authData.email);
        wd.findElement(AuthPage.loginPasswordInp).sendKeys(authData.password);

        loginBtn.click();
    }

    public void assertLoggedIn() {
        ww().until(urlToBe(HOME_URL+"/"));
        ww().until(presenceOfElementLocated(BurgerBuilderPage.orderButton));
    }

    public void assertLogout() {
        ww().until(urlToBe(HOME_URL+"/login"));
    }

    public static void doLogout() {
        wd.findElement(BurgerBuilderPage.profileLink).click();
        ww().until(presenceOfElementLocated(ProfilePage.navLogout)).click();
    }

    @Test
    public void testLoginViaMainBtn() {
        wd.findElement(BurgerBuilderPage.loginButton).click();

        doLogin();
        assertLoggedIn();

        doLogout();
        assertLogout();
    }

    @Test
    public void testLoginViaProfileLink() {
        wd.findElement(BurgerBuilderPage.profileLink).click();

        doLogin();
        assertLoggedIn();

        doLogout();
        assertLogout();
    }

}
