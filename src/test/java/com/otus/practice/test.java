package com.otus.practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class test {


    private static final By name = By.xpath("//input[@name='fname']");
    public By auth = By.xpath("//button[contains(text(), 'Вход')]");
    protected By lk = By.xpath("//a[@title='Личный кабинет']");
    private final Logger logger = LogManager.getLogger(test.class);
    private final By emailField = By.xpath("//input[@placeholder='Электронная почта' and @type='text']");
    private final By passwordField = By.xpath("//input[@placeholder='Введите пароль' and @type='password']");
    private final By logIn = By.xpath("//button[@type='submit' and contains(text(), 'Войти')]");
    private final By avatar = By.xpath("//div[@class='header2-menu__icon-img ic-blog-default-avatar']");
    private final By personal = By.xpath("//a[@title='О себе']");
    private final By surname = By.xpath("//input[@name='lname']");

    private WebDriver driver;

    @Test
    public void OtusTest() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        logger.info("Драйвер для браузера Chrome запущен, установлено неявное ожидание = {} сек", 10);

        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.get("https://otus.ru");

        driver.findElement(auth).click();
        logger.info("Нажата кнопка ВХОД ИЛИ РЕГИСТРАЦИЯ");


        wait.until(ExpectedConditions.elementToBeClickable(emailField));

        driver.findElement(emailField).sendKeys("vladoladvlad@gtest.com");


        driver.findElement(passwordField).sendKeys("veryStrongPassword111!");


        driver.findElement(logIn).click();
        logger.info("Нажата кнопка ВОЙТИ");

        Assert.assertTrue(isLoggedIn());
        logger.info("Пользователь внутри");


        driver.findElement(avatar).click();

        driver.findElement(lk).click();


        driver.findElement(personal).click();
        logger.info("Вызван разде О СЕБЕ");

        String Myname = driver.findElement(name).getAttribute("value");


        Assert.assertEquals(Myname, "Владимир");
        String Mysurname = driver.findElement(surname).getAttribute("value");
        Assert.assertEquals(Mysurname, "Владимиров");


        Thread.sleep(1000);
        driver.quit();
    }

    public boolean isLoggedIn() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(avatar));
        return driver.findElement(avatar).isDisplayed();
    }
}
