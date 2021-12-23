package com.cisco.phonebook.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cisco.phonebook.exception.UserDetailNotFoundException;
import com.cisco.phonebook.model.PhoneDetails;
import com.cisco.phonebook.model.UserDetails;
import com.cisco.phonebook.service.PhoneBookService;

@RestController
@RequestMapping("/phoneBook")
public class PhoneBookController {

	@Autowired
	private PhoneBookService phoneBookService;
	
	
	/**
	 * Adding new user details to the system
	 * @param userDetails
	 * @return
	 */
	@PostMapping(value = "/createUser")
	public Optional<UserDetails> addUser(@Valid @RequestBody UserDetails userDetails) {		
		return phoneBookService.addUser(userDetails);
	    }
	
	/**
	 * Get all the user list
	 * @return
	 */
	@GetMapping(value = "/allUsers")
	public List<UserDetails> findAllUserDetails() {		
		return phoneBookService.findAllUserDetails();
	    }	
	
	/**
	 * Deleting the existing user from the system
	 * @param userId
	 * @throws UserDetailNotFoundException
	 */
	@DeleteMapping(value = "/deleteUser/{userId}")
	public ResponseEntity<String>  deleteUser(@PathVariable(value ="userId") UUID userId) throws UserDetailNotFoundException{
		return phoneBookService.deleteUser(userId);
	    }
	
	
	/**
	 * Deleting the existing user from the system
	 * @param userId
	 * @throws UserDetailNotFoundException
	 */
	@DeleteMapping(value = "/deleteUserPhone/{userId}/{phoneNumber}")
	public UserDetails  deleteUserPhone(@PathVariable(value ="userId") UUID userId, @PathVariable(value ="phoneNumber") String phoneNumber) throws UserDetailNotFoundException{
		 return phoneBookService.deleteUserPhone(userId,phoneNumber);
	    }
	
	
	
	/**
	 * Adding perferred phone number to the user
	 * @param userDetails
	 * @return
	 * @throws UserDetailNotFoundException 
	 */
	@PutMapping(value = "/updatePhone/{userId}/{phoneNumber}")
	public UserDetails updateUserPhonePreference(@PathVariable(value ="userId") UUID userId, @PathVariable(value ="phoneNumber") String phoneNumber) throws UserDetailNotFoundException{
		return phoneBookService.updateUserPhonePreference(userId,phoneNumber);
	    }
	
	
	/**
	 * Adding new phone details to existing user
	 * @param userId
	 * @param phoneDetails
	 * @return
	 * @throws UserDetailNotFoundException
	 */
	@PutMapping(value = "/addPhone")
	public ResponseEntity<Optional<UserDetails>> addUserPhone(@RequestBody UserDetails updatePhoneDetails) throws UserDetailNotFoundException{
		return phoneBookService.addUserPhone(updatePhoneDetails);
	    }
	
	
	/**
	 * Get all the list of phone number of user
	 * @param userId
	 * @throws UserDetailNotFoundException 
	 */
	@GetMapping(value = "/phoneList/{userId}")
	public ResponseEntity<List<PhoneDetails>> getUserPhoneList(@PathVariable(value ="userId") UUID userId) throws UserDetailNotFoundException {
		return phoneBookService.getUserPhoneList(userId);
	}
}
