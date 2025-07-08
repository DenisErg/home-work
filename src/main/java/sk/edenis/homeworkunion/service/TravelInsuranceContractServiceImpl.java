package sk.edenis.homeworkunion.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.edenis.homeworkunion.constant.TravelInsuranceContractConst;
import sk.edenis.homeworkunion.dto.TravelInsuranceContractDTO;
import sk.edenis.homeworkunion.model.TravelInsuranceContract;
import sk.edenis.homeworkunion.repository.TravelInsuranceContractRepository;
import sk.edenis.homeworkunion.utility.FieldValidatorUtil;

import java.util.Map;

@Service
public class TravelInsuranceContractServiceImpl implements TravelInsuranceContractService {

    private final TravelInsuranceContractRepository travelInsuranceContractRepository;

    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    public TravelInsuranceContractServiceImpl(TravelInsuranceContractRepository travelInsuranceContractRepository) {
        this.travelInsuranceContractRepository = travelInsuranceContractRepository;
    }

    private TravelInsuranceContractDTO validateTravelInsuranceContract(Map<String, Object> contractMap) {

        FieldValidatorUtil.validateContractMap(contractMap, TravelInsuranceContractConst.getMandatoryMessageTags());

        String inceptionOfInsurance = contractMap.get(TravelInsuranceContractConst.INCEPTION_OF_INSURANCE).toString();
        String terminationOfInsurance = contractMap.get(TravelInsuranceContractConst.TERMINATION_OF_INSURANCE).toString();
        String liabilityInsurance = contractMap.get(TravelInsuranceContractConst.LIABILITY_OF_INSURANCE).toString();
        String accidentInsurance = contractMap.get(TravelInsuranceContractConst.ACCIDENT_INSURANCE).toString();


        FieldValidatorUtil.validateDate(TravelInsuranceContractConst.INCEPTION_OF_INSURANCE, inceptionOfInsurance);
        FieldValidatorUtil.validateDate(TravelInsuranceContractConst.TERMINATION_OF_INSURANCE, terminationOfInsurance);
        FieldValidatorUtil.validateBoolean(TravelInsuranceContractConst.LIABILITY_OF_INSURANCE, liabilityInsurance);
        FieldValidatorUtil.validateBoolean(TravelInsuranceContractConst.ACCIDENT_INSURANCE, accidentInsurance);

        return mapper.map(contractMap, TravelInsuranceContractDTO.class);

    }

    private TravelInsuranceContract saveTravelInsuranceContract(TravelInsuranceContract travelInsuranceContract) {
        return travelInsuranceContractRepository.save(travelInsuranceContract);
    }

    @Override
    public TravelInsuranceContract createTravelInsuranceContract(Map<String, Object> contractMap) {
        TravelInsuranceContractDTO travelInsuranceContractDTO = validateTravelInsuranceContract(contractMap);

        TravelInsuranceContract travelInsuranceContract = new TravelInsuranceContract();
        travelInsuranceContract.setContractType(travelInsuranceContractDTO.getContractType());
        travelInsuranceContract.setContractNumber(travelInsuranceContractDTO.getContractNumber());
        travelInsuranceContract.setCreationDate(travelInsuranceContractDTO.getCreationDate());
        travelInsuranceContract.setInceptionOfInsurance(travelInsuranceContractDTO.getInceptionOfInsurance());
        travelInsuranceContract.setTerminationOfInsurance(travelInsuranceContractDTO.getTerminationOfInsurance());
        travelInsuranceContract.setLiabilityInsurance(travelInsuranceContractDTO.getLiabilityInsurance());
        travelInsuranceContract.setAccidentInsurance(travelInsuranceContractDTO.getAccidentInsurance());
        return saveTravelInsuranceContract(travelInsuranceContract);
    }
}
