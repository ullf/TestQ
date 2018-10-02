package ru.marksblog;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class Api {

    private WebDriver driver;

    public WebDriver setup(){
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:9515"), DesiredCapabilities.chrome());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.get("https://mail.ru/");
        return driver;
    }

    public void login(String email,String password){
        WebElement elemEmail = driver.findElement(By.id("mailbox:login"));
        elemEmail.sendKeys(email);
        WebElement elemPassword = driver.findElement(By.id("mailbox:password"));
        elemPassword.sendKeys(password);
        WebElement login = driver.findElement(By.className("o-control"));
        login.submit();
    }

    public void writeMessage(String toWho,String subject,String message){
        WebElement writeMessage = null;
        try{
            writeMessage = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.className("b-toolbar__item")));
        }catch (NoSuchElementException e){
            e.getSupportUrl();
        }
        writeMessage.click();
        WebElement webToWho = driver.findElement(By.cssSelector("textarea.js-input.compose__labels__input"));
        webToWho.sendKeys(toWho);
        webToWho.sendKeys(Keys.TAB);
        WebElement webSubject = driver.findElement(By.cssSelector("input.b-input"));
        webSubject.sendKeys(subject);
        ((JavascriptExecutor)driver).executeScript("tinyMCE.activeEditor.setContent('"+message+"')");
        driver.switchTo().activeElement();
        String keys = Keys.chord(Keys.CONTROL,Keys.ENTER);
        driver.switchTo().defaultContent().findElement(By.tagName("body")).sendKeys(keys);
    }
}
