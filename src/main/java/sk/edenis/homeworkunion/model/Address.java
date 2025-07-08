package sk.edenis.homeworkunion.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false, columnDefinition = "UUID")
    private UUID addressId;

    @NotBlank(message = "Názov obce nesmie byť prázdny!")
    @Pattern(regexp = "^[A-Za-zÁ-Žá-ž ]+$", message = "Názov obce nie je valídny!")
    private String city;
    private String street;

    private String houseNumber;
    private String postalCode;

    @OneToMany(mappedBy = "permanentAddress")
    private List<Insured> permanentResidences;

    @OneToMany(mappedBy = "correspondenceAddress")
    private List<Insured> correspondenceAddresses;

    public Address() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public UUID getAddressId() {
        return addressId;
    }

    public List<Insured> getPermanentResidences() {
        return permanentResidences;
    }

    public List<Insured> getCorrespondenceAddresses() {
        return correspondenceAddresses;
    }
}
