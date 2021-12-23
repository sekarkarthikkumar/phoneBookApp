package com.cisco.phonebook.model;

import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

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
public class UserDetails {

	@Id
	@GeneratedValue
	private UUID userId;
	
	@Size(min=3,message="Username should be atleast 3 letter")
	private String userName;
	
	@Size(min=4,message="Password should be atleast 4 letter")
	private String password;
	
	@Email
	private String emailAddress;
	
	private String preferredPhoneNumber;
	
	@OneToMany(targetEntity = PhoneDetails.class, cascade = CascadeType.ALL)
	@JoinColumn(name="userPhone_fk",referencedColumnName = "userId")
	private Set<PhoneDetails> phoneDetails;
}