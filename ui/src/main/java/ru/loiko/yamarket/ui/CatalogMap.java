package ru.loiko.yamarket.ui;

public enum CatalogMap {
    LNK_HEADING_CATEGORIES("//div[@role='heading']//a[text()[normalize-space(.)='%s']]"),
    LNK_CATEGORIES("//li[@data-zone-name='category-link']//span[text()[normalize-space(.)='%s']]"),
    LNK_TYPE_CATEGORIES("//div[@role='heading']//a[text()[normalize-space(.)='%s']]/ancestor::div[3]//ul//a[text()[normalize-space(.)='%s']]"),
    ;
    String id;

    CatalogMap(String id) {
        this.id = id;
    }

    public String get() {
        return id;
    }

    public String get(Object... args) {
        return String.format(id, args);
    }
}
