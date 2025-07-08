package sk.edenis.homeworkunion.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.edenis.homeworkunion.dto.*;
import sk.edenis.homeworkunion.exception.AlreadyExistingException;
import sk.edenis.homeworkunion.model.Address;
import sk.edenis.homeworkunion.model.InsuranceContract;
import sk.edenis.homeworkunion.model.Insured;
import sk.edenis.homeworkunion.repository.InsuredRepository;
import sk.edenis.homeworkunion.utility.FieldValidatorUtil;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class InsuredServiceImpl implements InsuredService {
    private final AddressService addressService;
    private final InsuranceContractService insuranceContractService;
    private final InsuredRepository insuredRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public InsuredServiceImpl(AddressService addressService, InsuranceContractService insuranceContractService, InsuredRepository insuredRepository) {
        this.addressService = addressService;
        this.insuranceContractService = insuranceContractService;
        this.insuredRepository = insuredRepository;
    }

    private Insured createInsured(InsuredRequestDTO insuredRequestDTO, List<InsuranceContract> contracts, Address permanentAddress) {
        return createInsured(insuredRequestDTO, contracts, permanentAddress, permanentAddress);
    }

    private Insured createInsured(InsuredRequestDTO insuredRequestDTO, List<InsuranceContract> contracts, Address permanentAddress, Address correspondenceAddress) {
        Insured insured = new Insured();
        insured.setFirstName(insuredRequestDTO.getFirstName());
        insured.setLastName(insuredRequestDTO.getLastName());
        insured.setPersonalIdentificationNumber(validatePersonalIdentificationNumber(insuredRequestDTO.getPersonalIdentificationNumber()));
        insured.setEmail(insuredRequestDTO.getEmail());
        insured.setPermanentAddress(permanentAddress);
        insured.setCorrespondenceAddress(correspondenceAddress);
        insured.setContracts(contracts);
        return insured;
    }

    private String validatePersonalIdentificationNumber(String pin) {
        pin = pin.replace(" ", "");
        pin = pin.replace("/", "");

        if (pin.length() != 10) {
            throw new IllegalArgumentException("Rodné číslo nie je platné!");
        }
        if (insuredRepository.existsByPersonalIdentificationNumberEquals(pin)) {
            throw new AlreadyExistingException("Osoba s týmto rodným číslom už existuje.");
        }

        return pin;
    }

    @Override
    @Transactional
    public Insured saveInsured(InsuredRequestDTO insuredRequestDTO) {
        Insured insured;

        List<InsuranceContract> contracts = insuranceContractService.processContracts(insuredRequestDTO.getContracts());

        Address permanentAddress = addressService.createAddress(insuredRequestDTO.getPermanentAddress());

        if (insuredRequestDTO.getCorrespondenceAddress() != null) {
            Address correspondenceAddress = addressService.createAddress(insuredRequestDTO.getCorrespondenceAddress());
            insured = createInsured(insuredRequestDTO, contracts, permanentAddress, correspondenceAddress);
        } else {
            insured = createInsured(insuredRequestDTO, contracts, permanentAddress);
        }

        for (InsuranceContract contract : contracts) {
            contract.setInsured(insured);
        }

        insuredRepository.save(insured);
        return insured;
    }

    @Override
    public List<InsuredOverviewResponseDTO> getAllInsured() {
        List<Insured> insureds = insuredRepository.findAllByOrderByLastName();

        List<InsuredOverviewResponseDTO> insuredDTOs = insureds
                .stream()
                .map(insured -> modelMapper.map(insured, InsuredOverviewResponseDTO.class))
                .collect(Collectors.toList());
        if (insuredDTOs.isEmpty()) {
            throw new EntityNotFoundException("Zoznam poistencov je prázdny alebo nedostupný");
        }

        return insuredDTOs;
    }

    @Override
    public InsuredDetailResponseDTO getInsuredById(String id) {
        UUID uuid = FieldValidatorUtil.validateUUID("ID",id);

        Insured insured = insuredRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Poistenec s ID: " + id + " nebol nájdený!"));

        InsuredDetailResponseDTO dto = buildInsuredDetailResponseDTO(insured);
        dto.setPermanentAddress(addressService.buildAddressDTO(insured.getPermanentAddress()));
        dto.setCorrespondenceAddress(addressService.buildAddressDTO(insured.getCorrespondenceAddress()));

        List<InsuranceContractDTO> insuranceContractDTOs = new ArrayList<>();

        for (InsuranceContract contract : insured.getContracts()) {
            insuranceContractDTOs.add(insuranceContractService.buildInsuranceContractDTO(contract));
        }

        dto.setContracts(insuranceContractDTOs);
        return dto;
    }

    @Override
    public InsuredDetailResponseDTO buildInsuredDetailResponseDTO(Insured insured) {
        InsuredDetailResponseDTO dto = new InsuredDetailResponseDTO();
        dto.setFirstName(insured.getFirstName());
        dto.setLastName(insured.getLastName());
        dto.setPersonalIdentificationNumber(
                String.valueOf(insured.getPersonalIdentificationNumber()));
        dto.setEmail(insured.getEmail());

        return dto;
    }

}
