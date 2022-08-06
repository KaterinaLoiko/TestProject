package ru.loiko.yamarket.toolkit;

public class Button extends BaseElement{

    public Verify verify = this.new Verify();

    public Button(String locator) {
        super(locator);
    }

    public void click(){
        element.click();
    }

    @Override
    public String getValue() {
        return element.getText();
    }

}
