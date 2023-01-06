package com.skillbox.users.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "addresses")
public class Address {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(name = "ID", length = 36)
	private UUID id;
	
	@Column(name = "city", unique = true, nullable = false)
	private String city;
	
	@Column(name = "street", nullable = false)
	private String street;
	
	@Column(name = "house_number", nullable = false)
	private String houseNumber;
	
	@Column(name = "flat_number")
	private Integer flatNumber;	
	
	@ManyToMany(mappedBy = "addressies")
	Set<User> users;
	
	public Address() {}
		
	public Address(String city, String street, String houseNumber, Integer flatNumber) {
		super();
		this.city = city;
		this.street = street;
		this.houseNumber = houseNumber;
		this.flatNumber = flatNumber;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(city, houseNumber, id, street);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(city, other.city) && Objects.equals(houseNumber, other.houseNumber)
				&& Objects.equals(id, other.id) && Objects.equals(street, other.street);
	}
	
	@Override
	public String toString() {
		return "Address [id=" + id + ", city=" + city + ", street=" + street + ", houseNumber=" + houseNumber
				+ ", flatNumber=" + flatNumber + "]";
	}


	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public Integer getFlatNumber() {
		return flatNumber;
	}

	public void setFlatNumber(Integer flatNumber) {
		this.flatNumber = flatNumber;
	}
}
