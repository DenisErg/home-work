package sk.edenis.homeworkunion.model;

public enum InsuranceContractType {
    TRAVEL_INSURANCE("Cestovné poistenie"),
    PROPERTY_INSURANCE("Poistenie majetku"),
    UNKNOWN("Neznámy typ zmluvy");

    private final String displayName;

    InsuranceContractType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static InsuranceContractType convertDisplayNameToType(String displayName) {
        for (InsuranceContractType type : values()) {
            if (type.getDisplayName().equalsIgnoreCase(displayName)) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
