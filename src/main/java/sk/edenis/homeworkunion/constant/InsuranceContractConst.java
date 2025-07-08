package sk.edenis.homeworkunion.constant;

import java.util.ArrayList;
import java.util.List;

public final class InsuranceContractConst {

    //Parent Insurance contract message tags
    public static final String CONTRACT_TYPE = "contractType";
    public static final String CONTRACT_NUMBER = "contractNumber";
    public static final String CREATION_DATE = "creationDate";

    public static List<String> getMandatoryMessageTags() {
        List<String> mandatoryFields = new ArrayList<>();
        mandatoryFields.add(CONTRACT_TYPE);
        mandatoryFields.add(CONTRACT_NUMBER);
        mandatoryFields.add(CREATION_DATE);
        return mandatoryFields;
    }
}
