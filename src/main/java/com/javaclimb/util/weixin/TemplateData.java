package com.javaclimb.util.weixin;

public class TemplateData {
    private String value;
    private String color = "#232426";

    private TemplateData() {
    }

    public TemplateData(String value) {
        this.value = value;
    }

    public TemplateData(String value, String color) {
        this.value = value;
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
