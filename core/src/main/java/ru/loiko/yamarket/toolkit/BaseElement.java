package ru.loiko.yamarket.toolkit;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public abstract class BaseElement {

    protected String locator;
    protected SelenideElement element;

    protected BaseElement(String locator) {
        this.locator = locator;
        this.element = $(By.xpath(locator));
    }

    @Step("Получение локатора элемента")
    public String getLocator() {
        return locator;
    }

    public SelenideElement getSelenideElement() { return element; }

    @Step("Ожидание элемента")
    public void waitUntil(Condition condition) {
        element.waitUntil(condition, 30000);
    }

    @Step("Ожидание элемента {1} миллисекунд")
    public void waitUntil(Condition condition, int timeout) {
        element.waitUntil(condition, timeout);
    }

    public boolean isVisible() {
        return element.isDisplayed();
    }

    public boolean isExist() {
        return element.exists();
    }

    public boolean isEnabled() {
        return element.isEnabled();
    }

    public abstract String getValue();

    public class Verify {

        @Step("Проверить, присутствует ли элемент")
        public void present(boolean present, String message) {
            Assertions.assertEquals(present, isVisible(), message);
        }

        @Step("Проверить, доступен ли элемент для действия")
        public void enable(boolean enable, String message) {
            Assertions.assertEquals(enable, isEnabled(), message);
        }

        @Step("Проверить, совпадает ли {0} со значением в элементе")
        public void value(String expectedValue, String message) {
            Assertions.assertEquals(expectedValue, getValue(), message);
        }

        @Step("Проверить, содержится ли {0} в элементе")
        public void valueContains(String expectedValue, String message) {
            Assertions.assertTrue(getValue().contains(expectedValue), message);
        }
    }

}
