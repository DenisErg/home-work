package sk.edenis.homeworkunion.service;

import sk.edenis.homeworkunion.model.TravelInsuranceContract;

import java.util.Map;

public interface TravelInsuranceContractService {

    TravelInsuranceContract createTravelInsuranceContract(Map<String,Object> contractMap);

}
