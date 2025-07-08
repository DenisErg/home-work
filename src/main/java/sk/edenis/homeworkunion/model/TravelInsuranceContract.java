package sk.edenis.homeworkunion.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "travel_insurance_contract")
public class TravelInsuranceContract extends InsuranceContract {

    private Date inceptionOfInsurance;
    private Date terminationOfInsurance;
    private Boolean liabilityInsurance;
    private Boolean accidentInsurance;

    public TravelInsuranceContract() {
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
