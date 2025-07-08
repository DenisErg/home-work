package sk.edenis.homeworkunion.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.edenis.homeworkunion.constant.PropertyInsuranceContractConst;
import sk.edenis.homeworkunion.dto.PropertyInsuranceContractDTO;
import sk.edenis.homeworkunion.exception.InvalidFieldFormatException;
import sk.edenis.homeworkunion.model.Address;
import sk.edenis.homeworkunion.model.PropertyInsuranceContract;
import sk.edenis.homeworkunion.model.PropertyType;
import sk.edenis.homeworkunion.repository.PropertyInsuranceContractRepository;
import sk.edenis.homeworkunion.utility.FieldValidatorUtil;

import java.util.Map;

@Service
public class PropertyInsuranceContractServiceImpl implements PropertyInsuranceContractService {

    private final PropertyInsuranceContractRepository propertyInsuranceContractRepository;
    private final AddressService addressService;

    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    public PropertyInsuranceContractServiceImpl(PropertyInsuranceContractRepository propertyInsuranceContractRepository, AddressService addressService) {
        this.propertyInsuranceContractRepository = propertyInsuranceContractRepository;
        this.addressService = addressService;
    }

    private PropertyInsuranceContract savePropertyInsuranceContract(PropertyInsuranceContract contract) {
        return propertyInsuranceContractRepository.save(contract);
    }

    private PropertyInsuranceContractDTO validatePropertyInsuranceFields(Map<String, Object> contractMap) {

        FieldValidatorUtil.validateContractMap(contractMap, PropertyInsuranceContractConst.getMandatoryMessageTags());

        String stringType = contractMap.get(PropertyInsuranceContractConst.PROPERTY_TYPE).toString();
        String propertyValue = contractMap.get(PropertyInsuranceContractConst.PROPERTY_VALUE).toString();

        FieldValidatorUtil.validateString(PropertyInsuranceContractConst.PROPERTY_TYPE, stringType);
        FieldValidatorUtil.validateString(PropertyInsuranceContractConst.PROPERTY_VALUE, propertyValue);

        if (PropertyType.convertDisplayPropertyType(stringType).equals(PropertyType.UNKNOWN)) {
            throw new IllegalArgumentException("Neočakávaný typ nehnuteľnosti!");
        }

        if (!propertyValue.trim().matches("^\\d+(\\.\\d{1,2})?$")) {
            throw new InvalidFieldFormatException("Hodnota majetku musí byť číslo s najviac dvoma desatinnými miestami.");
        }

        return mapper.map(contractMap, PropertyInsuranceContractDTO.class);
    }

    @Override
    public PropertyInsuranceContract createPropertyInsuranceContract(Map<String, Object> contractMap) {
        PropertyInsuranceContractDTO propertyInsuranceContractDTO = validatePropertyInsuranceFields(contractMap);
        Address propertyAddress = addressService.createAddress(propertyInsuranceContractDTO.getPropertyAddress());

        PropertyInsuranceContract propertyInsuranceContract = new PropertyInsuranceContract();
        propertyInsuranceContract.setContractType(propertyInsuranceContractDTO.getContractType());
        propertyInsuranceContract.setContractNumber(propertyInsuranceContractDTO.getContractNumber());
        propertyInsuranceContract.setCreationDate(propertyInsuranceContractDTO.getCreationDate());
        propertyInsuranceContract.setPropertyType(PropertyType.convertDisplayPropertyType(propertyInsuranceContractDTO.getPropertyType()));
        propertyInsuranceContract.setPropertyValue(propertyInsuranceContractDTO.getPropertyValue());
        propertyInsuranceContract.setPropertyAddress(propertyAddress);

        return savePropertyInsuranceContract(propertyInsuranceContract);
    }
}
