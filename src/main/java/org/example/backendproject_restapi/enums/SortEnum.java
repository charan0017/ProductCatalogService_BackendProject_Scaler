package org.example.backendproject_restapi.enums;


public enum SortEnum {
    ASC("asc"),
    DESC("desc");

    private final String text;

    SortEnum(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public static SortEnum fromString(String text) {
        for (SortEnum b : SortEnum.values()) {
            if (b.text.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
