package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class FolderTest extends BaseTest {
    public static final String FOLDER_NAME = "FolderInitial";
    public static final By FOLDER_NAME_MAIN_PAGE = By.cssSelector(".jenkins-table__link > span:first-child");
    public final String FOLDER_NEW_NAME = "FolderNewName";
    public final String DESCRIPTION_TEXT = "DescriptionForTest";

    private void createFolder(String name) {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='name']"))).sendKeys(name);
        getDriver().findElement(By.xpath("//span[text()='Folder']")).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='ok-button']"))).click();
        getWait10().until(ExpectedConditions.presenceOfElementLocated(By.name("Submit")));
    }

    private void goToMainPage() {
        WebElement logo = getWait10().until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.app-jenkins-logo")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", logo);
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@href='/view/all/newJob']"))).isDisplayed();
    }

    @Test
    public void testCreate() {
        createFolder(FOLDER_NAME);
        goToMainPage();

        Assert.assertEquals(
                getWait10().until(ExpectedConditions.visibilityOfElementLocated(FOLDER_NAME_MAIN_PAGE)).getText(), FOLDER_NAME);
    }

    @Test(dependsOnMethods = "testCreate")
    public void testRename() {

        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[@href='job/%s/']".formatted(FOLDER_NAME)))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/job/%s/confirm-rename']".formatted(FOLDER_NAME)))).click();

        WebElement newName = getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='newName']")));
        newName.clear();
        newName.sendKeys(FOLDER_NEW_NAME);
        getDriver().findElement(By.xpath("//button[@value='Rename']")).click();

        goToMainPage();

        Assert.assertEquals(getWait10().until(ExpectedConditions.visibilityOfElementLocated(FOLDER_NAME_MAIN_PAGE)).getText(), FOLDER_NEW_NAME);
    }

    @Test(dependsOnMethods = "testRename")
    public void testAddDescription() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[@href='job/%s/']".formatted(FOLDER_NEW_NAME)))).click();

        getDriver().findElement(By.id("description-link")).click();
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys(DESCRIPTION_TEXT);
        getDriver().findElement(By.xpath("//button[@value='Save']")).click();

        Assert.assertEquals(
                getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content"))).getText(),
                DESCRIPTION_TEXT);
    }

    @Test(dependsOnMethods = "testAddDescription")
    public void testAddMetricButtonVisibleInHealthMetricsDropdown() {

        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[@href='job/%s/']".formatted(FOLDER_NEW_NAME)))).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(By.linkText("Configure"))).click();

        WebElement healthSection = getWait5().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(text(), 'Health metrics')]")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", healthSection);

        WebElement addButton = getWait5().until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("button.hetero-list-add[suffix='healthMetrics']")));

        Assert.assertEquals(addButton.getAttribute("suffix"), "healthMetrics",
                "Button should have suffix='healthMetrics'");
    }

}
