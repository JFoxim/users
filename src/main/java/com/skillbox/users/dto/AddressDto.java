package com.skillbox.users.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto implements Serializable {
	private static final long serialVersionUID = 904146912865711857L;
	
	private UUID id;
	private String city;
	private String street;
	private String houseNumber;
	private Integer flatNumber;
}
