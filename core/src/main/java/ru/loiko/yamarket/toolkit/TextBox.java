package ru.loiko.yamarket.toolkit;

public class TextBox extends BaseElement {

    public Verify verify = this.new Verify();

    public TextBox(String locator) {
        super(locator);
    }

    public void clear() {
        element.clear();
    }

    public String getText() {
        return element.getText();
    }

    public void setValue(String value) {
        element.setValue(value);
    }

    @Override
    public String getValue() {
        return element.getValue();
    }

}
