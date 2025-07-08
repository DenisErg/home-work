package sk.edenis.homeworkunion.service;

import sk.edenis.homeworkunion.dto.InsuredDetailResponseDTO;
import sk.edenis.homeworkunion.dto.InsuredRequestDTO;
import sk.edenis.homeworkunion.dto.InsuredOverviewResponseDTO;
import sk.edenis.homeworkunion.model.Insured;

import java.util.List;
import java.util.UUID;

public interface InsuredService {

    Insured saveInsured(InsuredRequestDTO insuredRequestDTO) ;

    List<InsuredOverviewResponseDTO> getAllInsured();

    InsuredDetailResponseDTO getInsuredById(UUID id);

    InsuredDetailResponseDTO buildInsuredDetailResponseDTO(Insured insured);
}
