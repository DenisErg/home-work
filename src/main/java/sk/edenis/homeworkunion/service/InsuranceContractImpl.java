package sk.edenis.homeworkunion.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.edenis.homeworkunion.constant.InsuranceContractConst;
import sk.edenis.homeworkunion.dto.InsuranceContractDTO;
import sk.edenis.homeworkunion.dto.PropertyInsuranceContractDTO;
import sk.edenis.homeworkunion.dto.TravelInsuranceContractDTO;
import sk.edenis.homeworkunion.exception.AlreadyExistingException;
import sk.edenis.homeworkunion.model.InsuranceContract;
import sk.edenis.homeworkunion.model.InsuranceContractType;
import sk.edenis.homeworkunion.repository.InsuranceContractRepository;
import sk.edenis.homeworkunion.utility.FieldValidatorUtil;

import java.util.*;

@Service
public class InsuranceContractImpl implements InsuranceContractService {

    private final PropertyInsuranceContractService propertyService;
    private final TravelInsuranceContractService travelService;
    private final InsuranceContractRepository insuranceContractRepository;

    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    public InsuranceContractImpl(PropertyInsuranceContractService propertyService, TravelInsuranceContractService travelService,
                                 InsuranceContractRepository insuranceContractRepository) {
        this.propertyService = propertyService;
        this.travelService = travelService;
        this.insuranceContractRepository = insuranceContractRepository;
    }

    private String validateInsuranceContract(Map<String, Object> contract) {
        FieldValidatorUtil.validateContractMap(contract, InsuranceContractConst.getMandatoryMessageTags());

        String contractType = contract.get(InsuranceContractConst.CONTRACT_TYPE).toString();
        String contractNumber = contract.get(InsuranceContractConst.CONTRACT_NUMBER).toString();
        String creationDate = contract.get(InsuranceContractConst.CREATION_DATE).toString();

        FieldValidatorUtil.validateString(InsuranceContractConst.CONTRACT_TYPE, contractType);
        FieldValidatorUtil.validateString(InsuranceContractConst.CONTRACT_NUMBER, contractNumber);
        FieldValidatorUtil.validateDate(InsuranceContractConst.CREATION_DATE, creationDate);

        if (isContractInDatabase(contractNumber)) {
            throw new AlreadyExistingException("Zmluva s číslom " + contractNumber + " už existuje!");
        }

        return contractType;
    }

    private boolean isContractInDatabase(String contractNumber) {
        return insuranceContractRepository.existsByContractNumberEqualsIgnoreCase(contractNumber);
    }

    private InsuranceContractDTO getInsuranceContractInstances(InsuranceContractType type) {
        Map<InsuranceContractType, InsuranceContractDTO> instances = new HashMap<>();
        instances.put(InsuranceContractType.PROPERTY_INSURANCE, new PropertyInsuranceContractDTO());
        instances.put(InsuranceContractType.TRAVEL_INSURANCE, new TravelInsuranceContractDTO());
        return instances.get(type);
    }

    private InsuranceContractType getInsuranceContractTypeFromString(String type) {
        return InsuranceContractType.convertDisplayNameToType(type);
    }

    @Override
    public List<InsuranceContract> processContracts(List<Map<String, Object>> contractMaps) {

        List<InsuranceContract> contracts = new ArrayList<>();

        for (Map<String, Object> contractMap : contractMaps) {
            String type = validateInsuranceContract(contractMap);

            switch (getInsuranceContractTypeFromString(type)) {
                case PROPERTY_INSURANCE:
                    contracts.add(propertyService.createPropertyInsuranceContract(contractMap));
                    break;
                case TRAVEL_INSURANCE:
                    contracts.add(travelService.createTravelInsuranceContract(contractMap));
                    break;
                case UNKNOWN:
                    throw new IllegalStateException("Neočakávaný typ poistenej zmluvy: " + type);
            }
        }

        return contracts;
    }

    @Override
    public InsuranceContractDTO buildInsuranceContractDTO(InsuranceContract contract) {
        InsuranceContractType type = getInsuranceContractTypeFromString(contract.getContractType());
        InsuranceContractDTO dto = getInsuranceContractInstances(type);
        return mapper.map(contract, dto.getClass());
    }


}
