package sk.edenis.homeworkunion.constant;

import java.util.ArrayList;
import java.util.List;

public final class PropertyInsuranceContractConst {

    //Property Insurance message tags
    public static final String PROPERTY_TYPE = "propertyType";
    public static final String PROPERTY_ADDRESS = "propertyAddress";
    public static final String PROPERTY_VALUE = "propertyValue";

    public static List<String> getMandatoryMessageTags() {
        List<String> mandatoryFields = new ArrayList<>();
        mandatoryFields.add(PROPERTY_TYPE);
        mandatoryFields.add(PROPERTY_ADDRESS);
        mandatoryFields.add(PROPERTY_VALUE);
        return mandatoryFields;
    }
}
