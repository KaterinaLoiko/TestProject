package ru.loiko.yamarket.ui;

import io.qameta.allure.Step;
import ru.loiko.yamarket.toolkit.Link;

public class CatalogView extends SearchMainView {

    @Step("Получить тип категории товаров {0}")
    public static Link getTypeCategories(String categoriseHeader, String typeCategories) {
        return new Link(CatalogMap.LNK_TYPE_CATEGORIES.get(categoriseHeader, typeCategories));
    }

    @Step("Получить заголовок категории товаров {0}")
    public static Link getHeaderTypeCategories(String categoriseHeader) {
        return new Link(CatalogMap.LNK_HEADING_CATEGORIES.get(categoriseHeader));
    }

    @Step("Получить категории товаров {0}")
    public static Link getCategories(String categorise) {
        return new Link(CatalogMap.LNK_CATEGORIES.get(categorise));
    }

    @Step("Выбрать категории товаров {0}")
    public static void selectCategories(String categorise) {
        getCategories(categorise).click();
    }
}
