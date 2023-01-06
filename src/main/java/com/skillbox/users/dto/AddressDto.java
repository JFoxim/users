package com.skillbox.users.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class AddressDto implements Serializable {
	private static final long serialVersionUID = 904146912865711857L;
	
	private UUID id;
	private String city;
	private String street;
	private String houseNumber;
	private Integer flatNumber;	

	public AddressDto() {}

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
		AddressDto other = (AddressDto) obj;
		return Objects.equals(city, other.city) && Objects.equals(houseNumber, other.houseNumber)
				&& Objects.equals(id, other.id) && Objects.equals(street, other.street);
	}

}
