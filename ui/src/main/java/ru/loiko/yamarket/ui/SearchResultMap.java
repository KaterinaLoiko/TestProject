package ru.loiko.yamarket.ui;

public enum SearchResultMap {
    LBL_SEARCH_RESULT("//div[@data-auto='ClarifyCategory']/div[1]"),
    ;
    String id;

    SearchResultMap(String id) {
        this.id = id;
    }

    public String get() {
        return id;
    }

    public String get(Object... args) {
        return String.format(id, args);
    }
}
