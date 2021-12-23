package com.cisco.phonebook.serviceImpl;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cisco.phonebook.exception.UserDetailNotFoundException;
import com.cisco.phonebook.model.PhoneDetails;
import com.cisco.phonebook.model.UserDetails;
import com.cisco.phonebook.respository.PhoneDetailsRespository;
import com.cisco.phonebook.respository.UserDetailsRepository;
import com.cisco.phonebook.service.PhoneBookService;

@Service
@Transactional
public class PhoneBookServiceImpl implements PhoneBookService {

	Logger logger = LoggerFactory.getLogger(PhoneBookServiceImpl.class);

	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	@Autowired
	private PhoneDetailsRespository phoneDetailsRespository;

	@Override
	public Optional<UserDetails> addUser(UserDetails userDetails) {
		userDetailsRepository.save(userDetails);
		return userDetailsRepository.findById(userDetails.getUserId());
	}

	@Override
	public List<UserDetails> findAllUserDetails() {
		// TODO Auto-generated method stub
		return userDetailsRepository.findAll();
	}

	@Override
	public UserDetails findByUserCredentials(String userName, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> deleteUser(UUID userId) throws UserDetailNotFoundException {
		logger.info("Inside Delete User");
		UserDetails details = userDetailsRepository.findById(userId).orElseThrow(
				() -> new UserDetailNotFoundException("User with id: " + userId + " is not found in the system"));

		userDetailsRepository.deleteById(userId);

		return new ResponseEntity<String>("Deleted the User name : " + details.getUserName() + " with UserId: " + userId
				+ " successfully from the system !", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Optional<UserDetails>> addUserPhone( UserDetails updatePhoneDetails)
			throws UserDetailNotFoundException {

		logger.info("Inside addUserPhone ");
		UserDetails userdDetails = userDetailsRepository.findById(updatePhoneDetails.getUserId()).orElseThrow(
				() -> new UserDetailNotFoundException("User with id: " + updatePhoneDetails.getUserId() + " is not found in the system"));

	
		Set<PhoneDetails> newPhoneDetailsList = updatePhoneDetails.getPhoneDetails();
		for (Iterator iterator = newPhoneDetailsList.iterator(); iterator.hasNext();) {
			PhoneDetails phoneDetails = (PhoneDetails) iterator.next();
			userdDetails.getPhoneDetails().add(phoneDetails);	
		}
						
		userDetailsRepository.saveAndFlush(userdDetails);
		return new ResponseEntity<Optional<UserDetails>>(userDetailsRepository.findById(updatePhoneDetails.getUserId()), HttpStatus.OK);
	}

	@Override
	public UserDetails updateUserPhonePreference(UUID userId, String phoneNumber) throws UserDetailNotFoundException {
		

		logger.info("Inside updateUserPhonePreference ");
		
		UserDetails userdDetails = userDetailsRepository.findById(userId).orElseThrow(
				() -> new UserDetailNotFoundException("User with id: " + userId + " is not found in the system"));
		
		//Check if phonedetails exists
		boolean isPhoneDetailsExist = userdDetails.getPhoneDetails().stream()
				.anyMatch(p -> p.getPhoneNumber().equalsIgnoreCase(phoneNumber));
		if (!isPhoneDetailsExist) {
			throw new UserDetailNotFoundException(
					"Phone number entered is not exist in the user's phone details. Please add phone details for new phone number");
		} else {
			userdDetails.setPreferredPhoneNumber(phoneNumber);
			userDetailsRepository.save(userdDetails);
		}
		return userdDetails;
	}

	@Override
	public ResponseEntity<List<PhoneDetails>> getUserPhoneList(UUID userId) throws UserDetailNotFoundException {
		// TODO Auto-generated method stub
		
		logger.info("Inside getUserPhoneList of userId :" + userId);
		UserDetails userdDetails = userDetailsRepository.findById(userId).orElseThrow(
				() -> new UserDetailNotFoundException("User with id: " + userId + " is not found in the system"));

		List<PhoneDetails> phoneList = userdDetails.getPhoneDetails().stream().collect(Collectors.toList());

		return new ResponseEntity<List<PhoneDetails>>(phoneList, HttpStatus.OK);
	}

	/**
	 * Deleting the phone number from user's phoneList
	 */
	@Override
	public UserDetails deleteUserPhone(UUID userId, String phoneNumber) throws UserDetailNotFoundException {
		// TODO Auto-generated method stub
		logger.info("Inside deleteUserPhone: " + phoneNumber + " of userId :" + userId);
		UserDetails userDetails = userDetailsRepository.findById(userId).orElseThrow(
				() -> new UserDetailNotFoundException("User with id: " + userId + " is not found in the system"));
		boolean isPhoneDetailsExist = userDetails.getPhoneDetails().stream()
				.anyMatch(p -> p.getPhoneNumber().equalsIgnoreCase(phoneNumber));
		if (!isPhoneDetailsExist) {
			throw new UserDetailNotFoundException(
					"Phone number entered is not exist in the user's phone details. Please add phone details for new phone number");
		} else {
			
			Optional<PhoneDetails> phonedetail = userDetails.getPhoneDetails().stream().filter(p -> p.getPhoneNumber().equalsIgnoreCase(phoneNumber)).findFirst();
			
			// Removed the phone details
			userDetails.getPhoneDetails().removeIf(p -> p.getPhoneNumber().equalsIgnoreCase(phoneNumber));
			userDetailsRepository.saveAndFlush(userDetails);
			
			
			//delete from phonedetails
			phoneDetailsRespository.deleteById(phonedetail.get().getPhoneId());
			
						
			// set preferred phoneNumber to null if requested removal phoneNumber is
			// currently preferred
			if (userDetails.getPreferredPhoneNumber().equalsIgnoreCase(phoneNumber)) {
				userDetails.setPreferredPhoneNumber(null);
				logger.info("Inside user preferred number is same as removal phone details number ");
			}

			
		}
		return userDetails;
	}

}
