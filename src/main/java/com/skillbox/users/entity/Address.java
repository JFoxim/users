package com.skillbox.users.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;


import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
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

	public Address() {}
		
	public Address(String city, String street, String houseNumber, Integer flatNumber) {
		super();
		this.city = city;
		this.street = street;
		this.houseNumber = houseNumber;
		this.flatNumber = flatNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Address address = (Address) o;
		return Objects.equals(id, address.id) && Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(houseNumber, address.houseNumber) && Objects.equals(flatNumber, address.flatNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, city, street, houseNumber, flatNumber);
	}
}
