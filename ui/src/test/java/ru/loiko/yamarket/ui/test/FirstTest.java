package ru.loiko.yamarket.ui.test;

import com.codeborne.selenide.Condition;
import com.google.common.collect.ImmutableList;
import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.loiko.yamarket.base.Application;
import ru.loiko.yamarket.base.PropertiesProviderConfiguration;
import ru.loiko.yamarket.ui.CatalogView;
import ru.loiko.yamarket.ui.SearchMainView;
import ru.loiko.yamarket.ui.SearchResultView;

import java.net.MalformedURLException;
import java.util.List;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = {PropertiesProviderConfiguration.class})
public class FirstTest {

    @Autowired
    Environment environment;

    private static Application application;
    private static String url;

    private static boolean preconditionForAll = true;

    @BeforeEach
    public void preconditions() throws MalformedURLException {
        if (preconditionForAll) {
            url = environment.getProperty("url");
            String remoteDriver = environment.getProperty("remoteDriver");
            application = new Application();
            application.openBrowser(remoteDriver, url, "ff");
            preconditionForAll = false;
        }
        application.getWebDriver().get(url);
    }

    @Description("Тест для проверки присутствия на главной странице всех категорий")
    @Test
    public void verifyAllCategoriesArePresent() {
        SearchMainView.getBtnSearch().waitUntil(Condition.appear);
        List<String> categories = ImmutableList.<String>builder()
                .add("Экспресс")
                .add("Продукты")
                .add("Дача")
                .add("Спорт")
                .add("Одежда")
                .add("Электроника")
                .add("Детям")
                .add("Покупайте как бизнес")
                .add("Продавайте на Маркете")
                .add("Универмаг")
                .build();
        categories.forEach(cat ->
                SearchMainView.getCategories(cat).verify.present(true, cat + " должна присутствовать на главной странице"));

    }

    @Description("Тест для проверки открытия и закрытия каталога")
    @Test
    public void verifyOpenCloseCatalog() {
        SearchMainView.getBtnSearch().waitUntil(Condition.appear);
        SearchMainView.clickCatalog();
        CatalogView.getCategories("Выгодно").waitUntil(Condition.appear);
        CatalogView.clickCatalog();
        CatalogView.getCategories("Выгодно").waitUntil(Condition.disappear);
    }

    @Description("Тест для проверки сброса поиска")
    @Test
    public void verifyClearSearch() {
        SearchMainView.getBtnSearch().waitUntil(Condition.appear);
        SearchMainView.setValueForSearch("игр");
        SearchMainView.getTxtSearch().verify.value("игр", "В поле поиска установилось значение игр");
        SearchMainView.clickSearch();
        SearchMainView.getBtnSearchClear().waitUntil(Condition.appear);
        SearchMainView.clickClearSearch();
        SearchMainView.getTxtSearch().verify.value("", "В поле поиска пусто");
    }

    @Description("Тест для проверки кнопки поиска")
    @ParameterizedTest
    @MethodSource("ru.loiko.yamarket.ui.test.testData.TestArguments#provideSearchArguments")
    public void verifySearchButton(String searchValue) {
        SearchMainView.getBtnSearch().waitUntil(Condition.appear);
        SearchMainView.setValueForSearch(searchValue);
        SearchMainView.clickSearch();
        SearchResultView.getLblSearchResult().waitUntil(Condition.appear);
        SearchResultView.getLblSearchResult().verify.present(true, "Результат поиска не пустой");
    }

    @Description("Негативный тест для проверки кнопки поиска")
    @Test
    public void wrongSearch() {
        SearchMainView.getBtnSearch().waitUntil(Condition.appear);
        SearchMainView.setValueForSearch("??");
        SearchMainView.clickSearch();
        SearchResultView.getLblSearchResult().verify.present(false, "Результат поиска пустой");
    }

    @Description("Тест для проверки поиска")
    @ParameterizedTest
    @MethodSource("ru.loiko.yamarket.ui.test.testData.TestArguments#provideSearchArguments")
    public void verifySearch(String searchValue, String searchResult) {
        SearchMainView.getBtnSearch().waitUntil(Condition.appear);
        SearchMainView.setValueForSearch(searchValue);
        SearchMainView.clickSearch();
        SearchMainView.getSuggestion(searchResult).waitUntil(Condition.appear);
        SearchMainView.clickSuggestion(searchResult);
        SearchResultView.getLblSearchResult().waitUntil(Condition.appear);
        SearchResultView.getLblSearchResult().verify.present(true, "Результат поиска не пустой");
    }

    @Description("Тест для проверки предложений поиска")
    @ParameterizedTest
    @MethodSource("ru.loiko.yamarket.ui.test.testData.TestArguments#provideSearchArguments")
    public void verifyFieldSearch(String searchValue, String searchResult) {
        SearchMainView.getBtnSearch().waitUntil(Condition.appear);
        SearchMainView.setValueForSearch(searchValue);
        SearchMainView.getTxtSearch().verify.value(searchValue, "В поле поиска установилось значение " + searchValue);
        SearchMainView.getSuggestion(searchResult).waitUntil(Condition.appear);
        SearchMainView.getSuggestion(searchResult).verify.present(true, "Результат поиска не пустой");
    }

    @Description("Тест для проверки присутствия в каталоге всех категорий")
    @Test
    public void verifyAllCategoriesAreInCatalogPresent() {
        SearchMainView.getBtnSearch().waitUntil(Condition.appear);
        SearchMainView.clickCatalog();
        CatalogView.getCategories("Выгодно").waitUntil(Condition.appear);
        List<String> categoriesInCatalog = ImmutableList.<String>builder()
                .add("Выгодно")
                .add("Для школы и офиса")
                .add("Электроника")
                .add("Компьютеры")
                .add("Одежда и обувь")
                .add("Бытовая техника")
                .add("Дом")
                .add("Красота")
                .add("Детские товары")
                .add("Дача и сад")
                .add("Строительство и ремонт")
                .add("Мебель")
                .add("Продукты питания")
                .add("Гигиена")
                .add("Аптека")
                .add("Оптика")
                .add("Зоотовары")
                .add("Бытовая химия")
                .add("Спорт и отдых")
                .add("Хобби и творчество")
                .add("Авто")
                .add("Книги")
                .add("Ювелирные украшения")
                .add("Цветы")
                .add("Товары для взрослых")
                .add("Цифровые товары")
                .add("Оборудование")
                .build();
        categoriesInCatalog.forEach(cat ->
                SearchMainView.getCategories(cat).verify.present(true, cat + " должна присутствовать в списке калатога"));
    }

    @Description("Тест для проверки присутствия на главной странице всех категорий")
    @ParameterizedTest
    @MethodSource("ru.loiko.yamarket.ui.test.testData.TestArguments#provideCategoriesWithUnderCategories")
    public void verifyAllUnderCategoriesArePresent(String categorise,
                                              String underCat1, String underCat2, String underCat3, String underCat4, String underCat5) {
        SearchMainView.getBtnSearch().waitUntil(Condition.appear);
        SearchMainView.clickCatalog();
        CatalogView.getCategories("Выгодно").waitUntil(Condition.appear);
        CatalogView.selectCategories("Выгодно");
        CatalogView.getHeaderTypeCategories("Электроника");
        List<String> underCategories = ImmutableList.<String>builder()
                .add(underCat1).add(underCat2).add(underCat3).add(underCat4).add(underCat5)
                .build();
        underCategories.forEach(underCategorise -> CatalogView.getTypeCategories(categorise, underCategorise).verify.present(true,
                String.format("Категория с заголовком %s и подкатегорией %s отображается ", categorise, underCategorise)));
    }

    @AfterAll
    public static void afterAll(){
        application.closeBrowser();
    }

}
