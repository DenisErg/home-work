package sk.edenis.homeworkunion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.edenis.homeworkunion.model.PropertyInsuranceContract;

import java.util.UUID;

@Repository
public interface PropertyInsuranceContractRepository extends JpaRepository<PropertyInsuranceContract, UUID> {

}
