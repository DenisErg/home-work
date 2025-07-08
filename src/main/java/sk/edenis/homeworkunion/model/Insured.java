package sk.edenis.homeworkunion.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "insureds")
public class Insured {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false, columnDefinition = "UUID")
    private UUID insuredId;

    private String firstName;
    private String lastName;
    private String personalIdentificationNumber;
    private String email;

    @ManyToOne
    @JoinColumn(name = "permanentAddressId")
    private Address permanentAddress;

    @ManyToOne
    @JoinColumn(name = "correspondenceAddressId")
    private Address correspondenceAddress;

    @OneToMany(mappedBy = "insured")
    private List<InsuranceContract> contracts;

    public Insured() {
    }

    public UUID getInsuredId() {
        return insuredId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonalIdentificationNumber() {
        return personalIdentificationNumber;
    }

    public void setPersonalIdentificationNumber(String personalIdentificationNumber) {
        this.personalIdentificationNumber = personalIdentificationNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(Address permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public Address getCorrespondenceAddress() {
        return correspondenceAddress;
    }

    public void setCorrespondenceAddress(Address correspondenceAddress) {
        this.correspondenceAddress = correspondenceAddress;
    }

    public List<InsuranceContract> getContracts() {
        return contracts;
    }

    public void setContracts(List<InsuranceContract> contracts) {
        this.contracts = contracts;
    }
}
