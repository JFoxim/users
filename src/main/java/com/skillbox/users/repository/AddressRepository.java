package com.skillbox.users.repository;

import com.skillbox.users.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID>{
    
	@Query(value = "SELECT a FROM Address a")
	List<Address> findAllAddresses();
	
	@Query(value = "SELECT a FROM Address a WHERE a.city = :city AND a.street = :street AND a.houseNumber = :houseNumber AND a.flatNumber = :flat")
	Optional<Address> findByCityAndStreetAndHouseNumberAndFlat(@Param("city") String city, 
			@Param("street") String street, @Param("houseNumber") String houseNumber, @Param("flat") int flat);
}
