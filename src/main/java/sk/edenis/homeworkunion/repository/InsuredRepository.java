package sk.edenis.homeworkunion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.edenis.homeworkunion.model.Insured;

import java.util.List;
import java.util.UUID;

@Repository
public interface InsuredRepository extends JpaRepository<Insured, UUID> {

    /**
     * @return  list of all Insured from database, sorted by last name;
     */
    List<Insured> findAllByOrderByLastName();

    boolean existsByPersonalIdentificationNumberEquals(String pin);
}
