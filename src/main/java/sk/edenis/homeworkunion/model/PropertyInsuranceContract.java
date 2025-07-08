package sk.edenis.homeworkunion.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "property_insurance_contracts")
public class PropertyInsuranceContract extends InsuranceContract {

    @ManyToOne
    @JoinColumn(name = "addressId")
    private Address propertyAddress;

    private PropertyType propertyType;

    @Column(precision = 15, scale = 2)
    private BigDecimal propertyValue;

    public Address getPropertyAddress() {
        return propertyAddress;
    }

    public PropertyInsuranceContract() {
    }

    public void setPropertyAddress(Address propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public BigDecimal getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(BigDecimal propertyValue) {
        this.propertyValue = propertyValue;
    }
}
