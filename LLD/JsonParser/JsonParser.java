package LLD.JsonParser;

import LLD.JsonParser.impl.*;

import java.util.*;

public class JsonParser {

    final char OPEN_CURLY_BRACKET = '{';
    final char CLOSE_CURLY_BRACKET = '}';
    final char OPEN_SQUARE_BRACKET = '[';
    final char CLOSE_SQUARE_BRACKET = ']';
    final char COMMA = ',';
    final char COLON = ':';
    final char DOUBLE_QUOTE = '"';
    final char SPACE = ' ';

    String json;
    int index;

    public JsonElement parseJson(String jsonString) {
        this.json = jsonString.trim();
        if (json.isBlank() || json.charAt(0) != OPEN_CURLY_BRACKET
                || json.charAt(json.length() - 1) != CLOSE_CURLY_BRACKET)
            throw new RuntimeException("Invalid Json !!!");
        index = 0;
        return parseValue();
    }

    private JsonElement parseValue() {
        char currChar = json.charAt(index);
        switch (currChar) {
            case OPEN_CURLY_BRACKET:
                return parseObject();
            case OPEN_SQUARE_BRACKET:
                return parseList();
            case DOUBLE_QUOTE:
                return parseString();
        }
        if (Character.isDigit(currChar) || currChar == '-')
            return parseNumber();
        if (currChar == 't' || currChar == 'f')
            return parseBoolean();
        if (currChar == 'n')
            return parseNull();

        throw new RuntimeException("Invalid Json !!!");
    }


    private JsonElement parseObject() {
        Map<String, JsonElement> properties = new HashMap<>();
        consume(OPEN_CURLY_BRACKET);
        while (json.charAt(index) != CLOSE_CURLY_BRACKET) {
            skipWhiteSpace();
            String propKey = parseString().getValue().toString();

            skipWhiteSpace();
            consume(COLON);
            skipWhiteSpace();
            JsonElement propValue = parseValue();
            properties.put(propKey, propValue);
            skipWhiteSpace();
            if (json.charAt(index) == COMMA) {
                consume(COMMA);
            }
        }
        consume(CLOSE_CURLY_BRACKET);
        return new JsonObject(properties);
    }

    private JsonElement parseList() {
        List<JsonElement> elements = new ArrayList<>();
        consume(OPEN_SQUARE_BRACKET);
        while (json.charAt(index) != CLOSE_SQUARE_BRACKET) {
            skipWhiteSpace();
            elements.add(parseValue());
            skipWhiteSpace();
            if (json.charAt(index) == COMMA) {
                consume(COMMA);
            }
        }
        consume(CLOSE_SQUARE_BRACKET);
        return new JsonList(elements);
    }

    private JsonElement parseNull() {
        String str = json.substring(index, index + 4);
        if ("null".equals(str)) {
            index += 4;
            return new JsonNull();
        }
        throw new RuntimeException("Invalid Json !!! + found: " + str);
    }

    private JsonElement parseString() {
        consume(DOUBLE_QUOTE);
        String str = readStringContent();
        consume(DOUBLE_QUOTE);
        return new JsonString(str);
    }

    private JsonElement parseBoolean() {
        if ("true".equals(json.substring(index, index + 4))) {
            index += 4;
            return new JsonBoolean(true);
        } else if ("false".equals(json.substring(index, index + 5))) {
            index += 5;
            return new JsonBoolean(false);
        }
        throw new RuntimeException("Expected boolean value, but found" + json.substring(index, index + 4));
    }


    private JsonElement parseNumber() {
        int startIndex = index;

        if (json.charAt(index) == '-') index++;
        while (Character.isDigit(json.charAt(index))) index++;
        if (json.charAt(index) == '.') index++;
        while (Character.isDigit(json.charAt(index))) index++;

        String numStr = json.substring(startIndex, index);

        return numStr.contains(".") ? new JsonNumber(Double.parseDouble(numStr)) : new JsonNumber(Long.parseLong(numStr));
    }


    private String readStringContent() {
        StringBuilder sb = new StringBuilder();
        while (index < json.length() && json.charAt(index) != DOUBLE_QUOTE) {
            if (json.charAt(index) == '\\') {
                index++;
                sb.append(readEscapedChar());
            } else {
                sb.append(json.charAt(index++));
            }
        }
        if (index >= json.length()) throw new RuntimeException("Invalid Json !!!");
        return sb.toString();
    }

    private char readEscapedChar() {
        if (index >= json.length()) throw new RuntimeException("Invalid Json !!!");
        return switch (json.charAt(index++)) {
            case '"', '\\', '/' -> json.charAt(index - 1);
            case 'b' -> '\b';
            case 'f' -> '\f';
            case 'n' -> '\n';
            case 'r' -> '\r';
            case 't' -> '\t';
            case 'u' -> readUnicodeChar();
            default -> throw new RuntimeException("Invalid Json !!!");
        };
    }

    private char readUnicodeChar() {
        if (index + 4 > json.length()) throw new RuntimeException("Invalid Json !!!");
        char c = (char) Integer.parseInt(json.substring(index, index + 4), 16);
        index += 4;
        return c;
    }

    private void consume(char expected) {
        if (json.charAt(index) == expected) index++;
        else throw new RuntimeException("Invalid Json !!! Expected: " + expected + " but got: " + json.charAt(index));
    }

    private void skipWhiteSpace() {
        while (index < json.length() && Character.isWhitespace(json.charAt(index))) {
            index++;
        }
    }

    public static void main(String[] args) {
        String[] testStrings = getTestStrings();
        JsonParser jsonParser = new JsonParser();
        for (String str : testStrings) {
            System.out.println("--------------");
            System.out.println("Input: " + str);
            Object output;
            try {
                output = jsonParser.parseJson(str).getValue();
            } catch (RuntimeException e) {
                output = "Fail: " + e.getMessage();
            }
            System.out.println("Output: " + output);
            System.out.println("--------------");
        }
    }

    private static String[] getTestStrings() {
        return new String[]{
                // --- invalid: empty / whitespace / non-object root ---
                "",
                "   ",
                "not json",
                "[1, 2, 3]",
                "\"hello\"",
                "42",
                "true",
                "null",

                // --- invalid: malformed objects ---
                "{",
                "}",
                "{ \"key\": \"value\"",
                "{ \"key\" \"value\" }",
                "{ \"key\": }",
                "{ key: \"value\" }",

                // --- valid: empty object ---
                " {} ",
                "{}",

                // --- valid: primitives ---
                "{ \"name\": \"John\", \"age\": 30 }",
                "{ \"active\": true, \"deleted\": false }",
                "{ \"value\": null }",
                "{ \"price\": 19.99 }",
                "{ \"count\": 0, \"temp\": -5, \"ratio\": -3.1.4 }",

                // --- valid: arrays ---
                "{ \"items\": [] }",
                "{ \"nums\": [1, 2, 3] }",
                "{ \"mixed\": [1, \"text\", true, false, null] }",

                // --- valid: nested structures ---
                "{ \"user\": { \"name\": \"Jane\", \"age\": 25 } }",
                "{ \"matrix\": [[1, 2], [3, 4]] }",
                "{ \"config\": { \"enabled\": true, \"tags\": [\"a\", \"b\"] } }",

                // --- valid: whitespace tolerance ---
                "{\n  \"key\" : \"value\" ,\n  \"num\" : 42\n}",
                "{ \"a\":1,\"b\":2,\"c\":3 }",

                // --- invalid: trailing / dangling commas ---
                "{ \"a\": 1, }",
                "{ \"a\": 1, \"b\": 2, }",
                "{ \"items\": [1, 2, 3, ] }",
                "{ \"items\": [, 1, 2] }",
                "{ , \"a\": 1 }",

                // --- invalid: duplicate commas / empty elements ---
                "{ \"a\": 1,, \"b\": 2 }",
                "{ \"nums\": [1,, 2] }",

                // --- edge: duplicate keys (last wins in most parsers) ---
                "{ \"id\": 1, \"id\": 2 }",
                "{ \"name\": \"first\", \"name\": \"second\" }",

                // --- edge: single-element containers ---
                "{ \"only\": { \"x\": 1 } }",
                "{ \"only\": [42] }",
                "{ \"emptyStr\": \"\" }",

                // --- edge: deeply nested ---
                "{ \"l1\": { \"l2\": { \"l3\": { \"l4\": { \"l5\": { \"value\": \"deep\" } } } } } }",
                "{ \"a\": [ { \"b\": [ { \"c\": [1] } ] } ] }",

                // --- edge: nested empty containers ---
                "{ \"obj\": {}, \"arr\": [] }",
                "{ \"nested\": { \"empty\": {}, \"list\": [] } }",

                // --- edge: numeric boundaries ---
                "{ \"zero\": 0, \"large\": 9223372036854775807, \"small\": -9223372036854775808 }",
                "{ \"scientific\": 1e10 }",
                "{ \"leadingZero\": 007 }",

                // --- edge: boolean / null adjacent to numbers ---
                "{ \"flags\": [true, false, null, 0, 1] }",

                // --- edge: mixed whitespace (tabs, multiple spaces) ---
                "{\t\"tabbed\"\t:\t\"value\"\t,\t\"spaced\"  :  99\t}",

                // --- edge: long keys and many properties ---
                "{ \"k1\": 1, \"k2\": 2, \"k3\": 3, \"k4\": 4, \"k5\": 5, \"k6\": 6, \"k7\": 7, \"k8\": 8 }",

                // --- invalid: garbage after valid object ---
                "{ \"a\": 1 } extra",
                "{ \"a\": 1 }{ \"b\": 2 }",

                // --- invalid: unclosed strings / special chars (strict JSON) ---
                "{ \"broken\": \"unclosed }",
                "{ \"escape\": \"line\\nbreak\" }",
                "{ \"unicode\": \"\\u0041\" }",
        };
    }
}
