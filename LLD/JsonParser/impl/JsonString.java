package LLD.JsonParser.impl;

import LLD.JsonParser.JsonElement;

public class JsonString implements JsonElement {
    String str;

    public JsonString(String str) {
        this.str = str;
    }

    @Override
    public Object getValue() {
        return str;
    }
}