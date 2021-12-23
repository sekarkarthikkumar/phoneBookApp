package com.cisco.phoneBook.unitTest;

import static org.hamcrest.CoreMatchers.any;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cisco.phonebook.exception.UserDetailNotFoundException;
import com.cisco.phonebook.model.PhoneDetails;
import com.cisco.phonebook.model.UserDetails;
import com.cisco.phonebook.respository.PhoneDetailsRespository;
import com.cisco.phonebook.respository.UserDetailsRepository;
import com.cisco.phonebook.service.PhoneBookService;
import com.cisco.phonebook.serviceImpl.PhoneBookServiceImpl;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
public class PhoneBookServiceImplTest {

	@MockBean
	private UserDetailsRepository userDetailsRepository;

	@MockBean
	private PhoneDetailsRespository phoneDetailsRespository;

	@Autowired
	private PhoneBookService phoneBookService;

	@TestConfiguration
	static class PhoneBookServiceImplTestConfiguration {
		@Bean
		public PhoneBookService phoneBookService() {
			return new PhoneBookServiceImpl();
		}
	}

	@Before
	public void setUp() {
		//mocking the methods/service used in test method
		List<UserDetails> userlist = getUserList();
		Mockito.when(userDetailsRepository.findAll()).thenReturn(userlist);
		Mockito.when(userDetailsRepository.findById(UUID.fromString("c81d4e2e-bcf2-11e6-869b-7df92533d2db"))).thenReturn(getUser());
	}

	/**
	 * get all user test
	 */
	@Test
	public void getAllUserList() {

		List<UserDetails> userAdded = phoneBookService.findAllUserDetails();

		String user1Email = userAdded.get(0).getEmailAddress();
		Assert.assertEquals("ABC@GMAIL.COM",user1Email);

	}
	
	

	
	/** Deleting user's phone test
	 * 
	 */
	@Test
	public void deleteUserPhoneNumber() {
		UserDetails deleteUserPhone =new UserDetails();
		try {
			deleteUserPhone = phoneBookService.deleteUserPhone(UUID.fromString("c81d4e2e-bcf2-11e6-869b-7df92533d2db"), "1234567890");
		} catch (UserDetailNotFoundException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(2,deleteUserPhone.getPhoneDetails().size());
	}
	 

	private Optional<UserDetails> getUser() {

		UserDetails usrDt = new UserDetails();
		usrDt.setUserId(UUID.fromString("c81d4e2e-bcf2-11e6-869b-7df92533d2db"));
		usrDt.setUserName("ABC");
		usrDt.setPassword("1234");
		usrDt.setEmailAddress("ABC@GMAIL.COM");
		usrDt.setPreferredPhoneNumber("1234567890");
		usrDt.setPhoneDetails(getPhoneSet());
		return Optional.of(usrDt);
	}

	private List<UserDetails> getUserList() {

		List<UserDetails> userList = new ArrayList<UserDetails>();
		UserDetails usrDt = new UserDetails();
		usrDt.setUserId(UUID.fromString("c81d4e2e-bcf2-11e6-869b-7df92533d2db"));
		usrDt.setUserName("ABC");
		usrDt.setPassword("1234");
		usrDt.setEmailAddress("ABC@GMAIL.COM");
		usrDt.setPreferredPhoneNumber("1234567890");
		usrDt.setPhoneDetails(getPhoneSet());

		UserDetails usr2 = new UserDetails();
		usr2.setUserId(UUID.fromString("c81d4e2e-bcf2-11e6-869b-7df92533d2db"));
		usr2.setUserName("TEST");
		usr2.setPassword("4321");
		usr2.setEmailAddress("ABC@GMAIL.COM");
		usr2.setPreferredPhoneNumber("1234567890");
		usr2.setPhoneDetails(getPhoneSet());

		UserDetails usr3 = new UserDetails();
		usr3.setUserId(UUID.fromString("c81d4e2e-bcf2-11e6-869b-7df92533d2db"));
		usr3.setUserName("USER");
		usr3.setPassword("5555");
		usr3.setEmailAddress("ABC@GMAIL.COM");
		usr3.setPreferredPhoneNumber("1234567890");
		usr3.setPhoneDetails(getPhoneSet());
		userList.add(usrDt);
		userList.add(usr2);
		userList.add(usr3);
		return userList;
	}

	private Set<PhoneDetails> getPhoneSet() {
		Set<PhoneDetails> phoneSet = new HashSet<PhoneDetails>();
		PhoneDetails phoneDt1 = new PhoneDetails();
		phoneDt1.setPhoneId(UUID.fromString("e52232e1-0ded-4587-999f-4dd135a4a94f"));
		phoneDt1.setPhoneModel("IOS");
		phoneDt1.setPhoneName("APPLE");
		phoneDt1.setPhoneNumber("1234567890");

		PhoneDetails phoneDt2 = new PhoneDetails();
		phoneDt2.setPhoneId(UUID.fromString("e52232e1-0ded-4587-999f-4dd135a4a94f"));
		phoneDt2.setPhoneModel("ANDRIOD");
		phoneDt2.setPhoneName("HTC");
		phoneDt2.setPhoneNumber("1122334455");

		PhoneDetails phoneDt3 = new PhoneDetails();
		phoneDt3.setPhoneId(UUID.fromString("e52232e1-0ded-4587-999f-4dd135a4a94f"));
		phoneDt3.setPhoneModel("ANDRIOD");
		phoneDt3.setPhoneName("SAMSUNG");
		phoneDt3.setPhoneNumber("1112223334");

		phoneSet.add(phoneDt1);
		phoneSet.add(phoneDt2);
		phoneSet.add(phoneDt3);

		return phoneSet;
	}

}
