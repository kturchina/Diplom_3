package com.ya.stellarburger;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public abstract class BaseSeleniumTest {
    public static final long DEFAULT_WAIT = 3;
    public static final String HOME_URL = "https://stellarburgers.nomoreparties.site";
    protected static WebDriver wd;

    @BeforeClass
    public static void setup() {
        //wd = new FirefoxDriver();
        wd = new ChromeDriver();
    }

    @Before
    public void bootstrap() {
        wd.get(HOME_URL);
    }


    @AfterClass
    public static void teardown() {
        wd.close();
    }

    public static WebDriverWait ww() {
        return new WebDriverWait(wd, ofSeconds(DEFAULT_WAIT));
    }
}
