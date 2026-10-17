package LLD.JsonParser.impl;

import LLD.JsonParser.JsonElement;

import java.util.List;
import java.util.stream.Collectors;

public class JsonList implements JsonElement {
    List<JsonElement> elements;

    public JsonList(List<JsonElement> elements) {
        this.elements = elements;
    }

    @Override
    public Object getValue() {
        return elements.stream().map(JsonElement::getValue).collect(Collectors.toList());
    }
}
