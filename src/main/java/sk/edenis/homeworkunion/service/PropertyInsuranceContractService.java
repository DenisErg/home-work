package sk.edenis.homeworkunion.service;

import sk.edenis.homeworkunion.model.PropertyInsuranceContract;

import java.util.Map;

public interface PropertyInsuranceContractService {

    PropertyInsuranceContract createPropertyInsuranceContract(Map<String,Object> contractMap);

}
