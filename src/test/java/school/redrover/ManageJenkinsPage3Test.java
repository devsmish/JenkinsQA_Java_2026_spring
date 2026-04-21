package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import java.util.Arrays;
import java.util.List;

public class ManageJenkinsPage3Test extends BaseTest {

    private static final By MANAGE_JENKINS_LINK = By.cssSelector("a[href='/manage']");
    private static final By SEARCH_BAR = By.id("settings-search-bar");
    private static final By EMPTY_DROPDOWN = By.className("jenkins-search__results__no-results-label");
    private static final By HEADER = By.xpath("//h1");

    private String getHeader() {
        return getDriver().findElement(HEADER).getText();
    }

    @DataProvider
    public Object[][] caseInSensitive() {
        return new Object[][]{
                {"system", "System"},
                {"SYSTEM", "System"},
                {"uSeRs", "Users"}
        };
    }

    @Test(dataProvider = "caseInSensitive")
    public void testSearchCaseInsensitive(String input, String expOutput) {

        getWait10().until(ExpectedConditions.elementToBeClickable(MANAGE_JENKINS_LINK)).click();
        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h1"), "Manage Jenkins"));

        WebElement searchBar = getDriver().findElement(SEARCH_BAR);
        searchBar.sendKeys(input);

        String actualOutput = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class, 'jenkins-dropdown__item')]"))).getText();

        Assert.assertEquals(actualOutput, expOutput);

    }


    @DataProvider
    public Object[][] invalidInput() {
        return new Object[][]{
                {"qwerty123", "No results"},
                {"!@#$", "No results"},
                {"  ", "No results"}
        };
    }

    @Test(dataProvider = "invalidInput")
    public void testSearchInvalid(String input, String expOutput) {
        getWait10().until(ExpectedConditions.elementToBeClickable(MANAGE_JENKINS_LINK)).click();
        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h1"), "Manage Jenkins"));

        getDriver().findElement(SEARCH_BAR).sendKeys(input);
        String actualOutput = getWait10().until(ExpectedConditions.visibilityOfElementLocated(EMPTY_DROPDOWN)).getText();

        Assert.assertEquals(actualOutput, expOutput);
    }

    @DataProvider
    public Object[][] systemConfiguration() {
        return new Object[][]{
                {"System"}, {"Tools"}, {"Plugins"}, {"Nodes"}, {"Clouds"}, {"Appearance"}
        };
    }

    @Test(dataProvider = "systemConfiguration")
    public void testNavigateToSystemConfigurationPagesByEnter(String section) {
        getWait10().until(ExpectedConditions.elementToBeClickable(MANAGE_JENKINS_LINK)).click();
        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(HEADER, "Manage Jenkins"));

        getDriver().findElement(SEARCH_BAR).sendKeys(section);
        getDriver().findElement(SEARCH_BAR).sendKeys(Keys.ENTER);

        getWait10().until(ExpectedConditions.not
                (ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h1"), "Manage Jenkins")));

        Assert.assertEquals(getHeader(), section);
    }
}