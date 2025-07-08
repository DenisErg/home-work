package sk.edenis.homeworkunion.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.edenis.homeworkunion.constant.AddressConst;
import sk.edenis.homeworkunion.dto.AddressDTO;
import sk.edenis.homeworkunion.exception.MissingFieldException;
import sk.edenis.homeworkunion.model.Address;
import sk.edenis.homeworkunion.repository.AddressRepository;
import sk.edenis.homeworkunion.utility.FieldValidatorUtil;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    private Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    private Optional<Address> findExistingAddress(AddressDTO addressDTO) {
        return addressRepository.findByCityAndStreetAndHouseNumberAndPostalCode(
                addressDTO.getCity(),
                addressDTO.getStreet(),
                addressDTO.getHouseNumber().replace(" ", ""),
                addressDTO.getPostalCode());
    }

    private Address validateAddress(AddressDTO addressDTO) {
        if (addressDTO == null) {
            throw new MissingFieldException("Adresa nie je valídna.");
        }

        FieldValidatorUtil.validateString(AddressConst.POSTAL_CODE, addressDTO.getPostalCode());
        FieldValidatorUtil.validateTextField(AddressConst.CITY, addressDTO.getCity().trim());
        FieldValidatorUtil.validateTextField(AddressConst.STREET, addressDTO.getStreet());
        FieldValidatorUtil.validateInteger(AddressConst.HOUSE_NUMBER, addressDTO.getHouseNumber());

        return findExistingAddress(addressDTO).orElse(null);
    }

    @Override
    public Address createAddress(AddressDTO addressDTO) {
        Address address = validateAddress(addressDTO);

        if (address == null) {
            address = new Address();
            address.setCity(addressDTO.getCity());
            address.setStreet(addressDTO.getStreet());
            address.setHouseNumber(addressDTO.getHouseNumber().replace(" ", ""));
            address.setPostalCode(addressDTO.getPostalCode());
        }
        return saveAddress(address);
    }

    @Override
    public AddressDTO buildAddressDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCity(address.getCity());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setHouseNumber(String.valueOf(address.getHouseNumber()));
        addressDTO.setPostalCode(address.getPostalCode());
        return addressDTO;
    }

}
