package com.skillbox.users.service;

import com.skillbox.users.entity.Address;
import com.skillbox.users.exception.AddressNotFoundException;
import com.skillbox.users.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {
	
	private AddressRepository addressRepository;
	
	@Autowired
    public void setAddressRepository(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	public List<Address> findAll() {
		return addressRepository.findAll();
	}
	
	public Address findById(UUID id) {
		
		Optional<Address> addressOpt = addressRepository.findById(id);

		if (!addressOpt.isPresent()) {
			throw new AddressNotFoundException("id - " + id);
		}
		return addressOpt.get();
	}
	
	public Address create(Address address) {
		return addressRepository.save(address);
	}
	
	public String update(Address address) {
		checkExistAddress(address);
		
		addressRepository.save(address);
		
		return 	String.format("Адрес с id %s обновлён", address.getId());
	}
	
	public String delete(Address address ) {
		addressRepository.delete(address);
		return String.format("Адрес с id %s удалён", address.getId());
	}
	
	public Optional<Address> findByCityStreetHouseFlat(String city, String street, String house, int flat) {	
		return addressRepository.findByCityAndStreetAndHouseNumberAndFlat(city, street, house, flat);
	}
	
	private void checkExistAddress(Address address) {
		if (!addressRepository.existsById(address.getId())) {
			throw new AddressNotFoundException("id - " + address.getId());
		}
	}
	
	public boolean existsByCityStreetHouseFlat(String city, String street, String house, int flat) {
		Optional<Address> addressOpt = addressRepository.findByCityAndStreetAndHouseNumberAndFlat(city, street, house, flat);
		
		if (!addressOpt.isPresent()) return false;
		
		return true;
	}
}
