package sk.edenis.homeworkunion.service;

import sk.edenis.homeworkunion.dto.InsuranceContractDTO;
import sk.edenis.homeworkunion.model.InsuranceContract;

import java.util.List;
import java.util.Map;

public interface InsuranceContractService {

    List<InsuranceContract> processContracts(List<Map<String, Object>> contracts);

    InsuranceContractDTO buildInsuranceContractDTO(InsuranceContract contract);



}
