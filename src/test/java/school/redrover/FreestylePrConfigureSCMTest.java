package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class FreestylePrConfigureSCMTest extends BaseTest {

    @Test
    public void testRepositoryURL() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys("Freestyle");
        getDriver().findElement(By.cssSelector("li.hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.id("ok-button")).click();
        WebElement gitOption = getDriver().findElement(By.xpath("//label[text()='Git']"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", gitOption);

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("_.url"))).
                sendKeys("https://github.com");

        Assert.assertEquals(getDriver().findElement(By.name("_.url")).
                getAttribute("value"), "https://github.com", "The repository URL does not match!");
    }
    @Test
    public void testCredentials() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys("Freestyle");
        getDriver().findElement(By.cssSelector("li.hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.id("ok-button")).click();
        WebElement gitOption = getDriver().findElement(By.xpath("//label[text()='Git']"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", gitOption);

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement dropDownList = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//select[@name='_.credentialsId']")));

        Assert.assertTrue(dropDownList.isDisplayed(), "The Credentials drop-down list is not displayed");
    }
    @Test
    public void testBranchesToBuild() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys("Freestyle");
        getDriver().findElement(By.cssSelector("li.hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.id("ok-button")).click();
        WebElement gitOption = getDriver().findElement(By.xpath("//label[text()='Git']"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", gitOption);

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement branchInput = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[contains(text(), 'Branch Specifier')]/following::input[1]")));
        branchInput.clear();
        branchInput.sendKeys("*/main");

        Assert.assertEquals(getDriver().findElement
                        (By.xpath("//div[contains(text(), 'Branch Specifier')]/following::input[1]")).
                getAttribute("value"), "*/main",
                "The branch name does not match the expected one!");
    }
}
