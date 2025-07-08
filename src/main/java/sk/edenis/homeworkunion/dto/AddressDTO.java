package sk.edenis.homeworkunion.dto;

import javax.validation.constraints.*;

public class AddressDTO {
    @NotBlank(message = "Obec nie je platná!")
    @Pattern(regexp = "^[a-zA-Zá-žÁ-Ž ]+$", message = "Obec nie je platná.")
    private String city;
    @NotBlank(message = "Ulica nesmie byť prázdna!")
    private String street;

    @NotBlank(message = "Číslo domu nesmie byť prázdne!")
    @Pattern(regexp = "[1-9]\\d{0,8}$", message = "Číslo domu nie je platné!")
    private String houseNumber;

    @NotBlank(message = "Poštové smerové číslo nesmie byť prázdne!")
    @Pattern(regexp = "\\d{3} ?\\d{2}", message = "PSČ musí mať formát XXX XX (napr. 010 01)")
    private String postalCode;

    public AddressDTO() {
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
}
