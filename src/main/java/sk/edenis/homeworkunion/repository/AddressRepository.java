package sk.edenis.homeworkunion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.edenis.homeworkunion.model.Address;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {

    Optional<Address> findByCityAndStreetAndHouseNumberAndPostalCode(
            String city, String street, String houseNumber, String postalCode);

}
