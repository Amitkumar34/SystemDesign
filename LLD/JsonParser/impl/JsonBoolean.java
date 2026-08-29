package LLD.JsonParser.impl;

import LLD.JsonParser.JsonElement;

public class JsonBoolean implements JsonElement {
    Boolean value;

    public JsonBoolean(Boolean value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }
}
