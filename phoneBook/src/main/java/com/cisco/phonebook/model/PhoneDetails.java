package com.cisco.phonebook.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDetails {

	@Id
	@GeneratedValue
	private UUID phoneId;
	private String phoneName;
	private String phoneNumber;
	private String phoneModel;


}
