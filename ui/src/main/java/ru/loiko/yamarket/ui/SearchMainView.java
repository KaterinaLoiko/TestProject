package ru.loiko.yamarket.ui;

import io.qameta.allure.Step;
import ru.loiko.yamarket.toolkit.Button;
import ru.loiko.yamarket.toolkit.Link;
import ru.loiko.yamarket.toolkit.TextBox;

public class SearchMainView {

    private static final Button btnSearch = new Button(SearchMainMap.BTN_SEARCH.get());
    private static final Button btnCatalog = new Button(SearchMainMap.BTN_CATALOG.get());
    private static final Button btnSearchClear = new Button(SearchMainMap.BTN_SEARCH_CLEAR.get());
    private static final TextBox txtSearch = new TextBox(SearchMainMap.TXB_HEADER_SEARCH.get());

    public static Button getBtnSearch() {
        return btnSearch;
    }

    public static Button getBtnCatalog() {
        return btnCatalog;
    }

    public static Button getBtnSearchClear() {
        return btnSearchClear;
    }

    public static TextBox getTxtSearch() {
        return txtSearch;
    }

    @Step("Установить значение в поле \"Найти\"")
    public static void setValueForSearch(String value) {
        getTxtSearch().setValue(value);
    }

    @Step("Нажать \"Найти\"")
    public static void clickSearch() {
        getBtnSearch().click();
    }

    @Step("Нажать \"Стереть\"")
    public static void clickClearSearch() {
        getBtnSearchClear().click();
    }

    @Step("Нажать \"Каталог\"")
    public static void clickCatalog() {
        getBtnCatalog().click();
    }

    @Step("Получить предложения товаров {0}")
    public static Link getSuggestion(String suggestion) {
        return new Link(SearchMainMap.LNK_SUGGESTIONS.get(suggestion));
    }

    @Step("Нажать один и вариантов поиска")
    public static void clickSuggestion(String suggestion) {
        getSuggestion(suggestion).click();
    }

    @Step("Получить предложение поиска {0}")
    public static Link getCategories(String categoriseName) {
        return new Link(SearchMainMap.LNK_SELECT_CATEGORIES.get(categoriseName));
    }
}
