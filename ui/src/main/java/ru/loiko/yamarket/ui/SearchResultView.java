package ru.loiko.yamarket.ui;

import io.qameta.allure.Step;
import ru.loiko.yamarket.toolkit.Label;

public class SearchResultView extends SearchMainView {

    private static final Label lblSearchResult = new Label(SearchResultMap.LBL_SEARCH_RESULT.get());

    @Step("Получить заголовок результата поиска")
    public static Label getLblSearchResult() {
        return lblSearchResult;
    }
}
