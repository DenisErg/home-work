package sk.edenis.homeworkunion.constant;

import java.util.ArrayList;
import java.util.List;

public final class TravelInsuranceContractConst {

    // Travel Insurance message tags
    public static final String INCEPTION_OF_INSURANCE   = "inceptionOfInsurance";
    public static final String TERMINATION_OF_INSURANCE = "terminationOfInsurance";
    public static final String LIABILITY_OF_INSURANCE   = "liabilityInsurance";
    public static final String ACCIDENT_INSURANCE       = "accidentInsurance";

    public static List<String> getMandatoryMessageTags() {
        List<String> mandatoryFields = new ArrayList<>();
        mandatoryFields.add(INCEPTION_OF_INSURANCE);
        mandatoryFields.add(TERMINATION_OF_INSURANCE);
        mandatoryFields.add(LIABILITY_OF_INSURANCE);
        mandatoryFields.add(ACCIDENT_INSURANCE);
        return mandatoryFields;
    }

}
