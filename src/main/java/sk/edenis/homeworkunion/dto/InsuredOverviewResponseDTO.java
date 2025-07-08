package sk.edenis.homeworkunion.dto;

import java.util.UUID;

public class InsuredOverviewResponseDTO {

    private UUID insuredId;
    private String firstName;
    private String lastName;
    private String personalIdentificationNumber;
    private String email;
    private AddressDTO permanentAddress;
    private AddressDTO correspondenceAddress;

    public InsuredOverviewResponseDTO() {
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

    public UUID getInsuredId() {
        return insuredId;
    }

    public void setInsuredId(UUID insuredId) {
        this.insuredId = insuredId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressDTO getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(AddressDTO permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public AddressDTO getCorrespondenceAddress() {
        return correspondenceAddress;
    }

    public void setCorrespondenceAddress(AddressDTO correspondenceAddress) {
        this.correspondenceAddress = correspondenceAddress;
    }
}
