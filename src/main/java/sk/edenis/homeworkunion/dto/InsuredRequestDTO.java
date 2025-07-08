package sk.edenis.homeworkunion.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InsuredRequestDTO {

    @NotBlank(message = "Meno nesmie byť prázdne!")
    private String firstName;

    @NotBlank(message = "Priezvisko nesmie byť prázdne!")
    private String lastName;

    @NotBlank(message = "Rodné číslo nesmie byť prázdne!")
    private String personalIdentificationNumber;
    @NotBlank(message = "Email nesmie byť prázdny!")
    @Email(message = "Email nie je valídny.")
    private String email;

    @NotNull(message = "Adresa musí byť vyplnená.")
    @Valid
    private AddressDTO permanentAddress;
    private AddressDTO correspondenceAddress;

    private List<Map<String, Object>> contracts;

    public InsuredRequestDTO() {
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

    public List<Map<String, Object>> getContracts() {
        return contracts;
    }

    public void setContracts(List<Map<String, Object>> contracts) {
        this.contracts = contracts;
    }
}
