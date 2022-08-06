package ru.loiko.yamarket.toolkit;

public class Label extends BaseElement {
    public Verify verify = this.new Verify();

    public Label(String locator) {
        super(locator);
    }

    @Override
    public String getValue() {
        return element.getText().trim();
    }

}
