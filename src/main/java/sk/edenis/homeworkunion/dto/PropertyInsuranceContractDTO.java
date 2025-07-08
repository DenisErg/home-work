package sk.edenis.homeworkunion.dto;

import java.math.BigDecimal;

public class PropertyInsuranceContractDTO extends InsuranceContractDTO {
    private String propertyType;
    private BigDecimal propertyValue;
    private AddressDTO propertyAddress;

    public PropertyInsuranceContractDTO() {
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public BigDecimal getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(BigDecimal propertyValue) {
        this.propertyValue = propertyValue;
    }

    public AddressDTO getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(AddressDTO propertyAddress) {
        this.propertyAddress = propertyAddress;
    }
}
