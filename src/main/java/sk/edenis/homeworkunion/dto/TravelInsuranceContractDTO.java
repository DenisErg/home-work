package sk.edenis.homeworkunion.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class TravelInsuranceContractDTO extends InsuranceContractDTO {
    @NotNull(message = "Začiatok poistenia musí byť vyplnený.")
    private Date inceptionOfInsurance;
    @NotNull(message = "Koniec poistenia musí byť vyplnený.")
    private Date terminationOfInsurance;
    @NotNull(message = "Poistenie zodpovednosti musí byť vyplnené.")
    private Boolean liabilityInsurance;
    @NotBlank(message = "Úrazové poistenie musí byť vyplnené.")
    private Boolean accidentInsurance;

    public TravelInsuranceContractDTO() {
    }

    public Date getInceptionOfInsurance() {
        return inceptionOfInsurance;
    }

    public void setInceptionOfInsurance(Date inceptionOfInsurance) {
        this.inceptionOfInsurance = inceptionOfInsurance;
    }

    public Date getTerminationOfInsurance() {
        return terminationOfInsurance;
    }

    public void setTerminationOfInsurance(Date terminationOfInsurance) {
        this.terminationOfInsurance = terminationOfInsurance;
    }

    public Boolean getLiabilityInsurance() {
        return liabilityInsurance;
    }

    public void setLiabilityInsurance(Boolean liabilityInsurance) {
        this.liabilityInsurance = liabilityInsurance;
    }

    public Boolean getAccidentInsurance() {
        return accidentInsurance;
    }

    public void setAccidentInsurance(Boolean accidentInsurance) {
        this.accidentInsurance = accidentInsurance;
    }
}
