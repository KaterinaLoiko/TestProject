package ru.loiko.yamarket.toolkit;

public class Link extends BaseElement {

    public Verify verify = this.new Verify();

    public Link(String locator) {
        super(locator);
    }

    @Override
    public String getValue() {
        return element.getText();
    }

    public void click(){
        element.click();
    }

}
