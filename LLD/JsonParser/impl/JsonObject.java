package LLD.JsonParser.impl;

import LLD.JsonParser.JsonElement;

import java.util.HashMap;
import java.util.Map;

public class JsonObject implements JsonElement {
    Map<String, JsonElement> properties;

    public JsonObject(Map<String, JsonElement> properties) {
        this.properties = properties;
    }

    @Override
    public Object getValue() {
        Map<String, Object> result = new HashMap<>();
        properties.forEach((k, v) -> result.put(k, v.getValue()));
        return result;
    }
}
