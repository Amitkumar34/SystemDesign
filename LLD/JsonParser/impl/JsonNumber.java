package LLD.JsonParser.impl;

import LLD.JsonParser.JsonElement;

public class JsonNumber implements JsonElement {
    Number number;

    public JsonNumber(Number number) {
        this.number = number;
    }

    @Override
    public Object getValue() {
        return number;
    }
}
