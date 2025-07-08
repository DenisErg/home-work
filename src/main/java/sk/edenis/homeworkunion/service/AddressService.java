package sk.edenis.homeworkunion.service;

import sk.edenis.homeworkunion.dto.AddressDTO;
import sk.edenis.homeworkunion.model.Address;

import javax.validation.Valid;

public interface AddressService {

    Address createAddress(@Valid AddressDTO addressDTO);

    AddressDTO buildAddressDTO(Address address);

}
