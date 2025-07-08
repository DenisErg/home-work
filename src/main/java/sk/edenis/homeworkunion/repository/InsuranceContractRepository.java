package sk.edenis.homeworkunion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.edenis.homeworkunion.model.InsuranceContract;

import java.util.UUID;

@Repository
public interface InsuranceContractRepository extends JpaRepository<InsuranceContract, UUID> {

    boolean existsByContractNumberEqualsIgnoreCase(String contractNumber);
}
