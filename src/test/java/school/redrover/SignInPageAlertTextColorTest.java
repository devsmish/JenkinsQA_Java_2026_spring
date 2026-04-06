package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.JenkinsUtils;

import java.time.Duration;
@Ignore
public class SignInPageAlertTextColorTest extends BaseTest {

    @Test
    public void testSignInPageAlertTextColor (){
        JenkinsUtils.logout(getDriver());

        getDriver().findElement(By.cssSelector("#j_username")).sendKeys("user");
        getDriver().findElement(By.cssSelector("#j_password")).sendKeys("qwerty");
        getDriver().findElement(By.xpath("//button[text()='Sign in']")).click();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement alertText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(),'Invalid username or password')]")));

        String actualColor = alertText.getCssValue("color");

        // Проверяем, что цвет содержит "красные" значения (RGB или OKLCH)
        // Для OKLCH: первый параметр (lightness) не важен, проверяем chroma и hue
        boolean isRedColor = actualColor.contains("oklch") &&
                (actualColor.matches(".*oklch\\([0-9.]+\\s+[0-9.]+\\s+[0-9.]+\\)"));

        // Альтернатива: проверка на RGB red компонент > 0.5
        boolean isRedRgb = actualColor.matches(".*rgb\\([0-9]+, 0, 0\\).*");

        Assert.assertTrue(isRedColor || isRedRgb,
                "Цвет текста ошибки не является красным: " + actualColor);
}}
