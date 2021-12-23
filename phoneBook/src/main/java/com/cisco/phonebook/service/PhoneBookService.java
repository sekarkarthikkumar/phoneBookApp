package com.cisco.phonebook.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.cisco.phonebook.exception.UserDetailNotFoundException;
import com.cisco.phonebook.model.PhoneDetails;
import com.cisco.phonebook.model.UserDetails;

public interface PhoneBookService {

	public Optional<UserDetails> addUser(UserDetails  userDetails); 

	public List<UserDetails> findAllUserDetails();
	
	public UserDetails findByUserCredentials(String userName, String password);

	public ResponseEntity<String> deleteUser(UUID  userId) throws UserDetailNotFoundException; 
	
	public UserDetails updateUserPhonePreference(UUID userId, String phoneNumber) throws UserDetailNotFoundException;

	public ResponseEntity<List<PhoneDetails>> getUserPhoneList(UUID userId) throws UserDetailNotFoundException;

	public UserDetails deleteUserPhone(UUID userId, String phoneNumber) throws UserDetailNotFoundException;

	public ResponseEntity<Optional<UserDetails>> addUserPhone(UserDetails userDetails) throws UserDetailNotFoundException; 
	
}
