package ru.loiko.yamarket.ui;

public enum SearchMainMap {
    TXB_HEADER_SEARCH("//input[@id='header-search']"),
    BTN_SEARCH("//button/span[text()[normalize-space(.)='Найти']]"),
    BTN_SEARCH_CLEAR("//button[@area-label='Стереть']"),
    BTN_CATALOG("//button//span[text()[normalize-space(.)='Каталог']]"),
    LNK_SELECT_CATEGORIES("//li[@role='tab']//span[text()[normalize-space(.)='%s']]"),
    LNK_SUGGESTIONS("//ul[@class='mini-suggest__popup-content']/li[text()[normalize-space(.)='%s']]"),
    ;
    String id;

    SearchMainMap(String id) {
        this.id = id;
    }

    public String get() {
        return id;
    }

    public String get(Object... args) {
        return String.format(id, args);
    }
}
