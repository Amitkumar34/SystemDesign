package LLD.JsonParser.impl;

import LLD.JsonParser.JsonElement;

public class JsonNull implements JsonElement {
    @Override
    public Object getValue() {
        return null;
    }
}
