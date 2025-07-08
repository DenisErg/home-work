package sk.edenis.homeworkunion.model;

public enum PropertyType {
    FLAT("Byt"),
    HOUSE_BRICK("Rodinný dom - murovaný"),
    HOUSE_WOODEN("Rodinný dom - drevený"),
    UNKNOWN("Neznámy typ nehnuteľnosti");

    private final String displayPropertyType;

    PropertyType(String displayPropertyType) {
        this.displayPropertyType = displayPropertyType;
    }

    public String getDisplayPropertyType() {
        return displayPropertyType;
    }

    public static PropertyType convertDisplayPropertyType(String displayPropertyType) {
        for (PropertyType type : values()) {
            if (type.getDisplayPropertyType().equals(displayPropertyType)) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
