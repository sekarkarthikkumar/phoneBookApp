package com.cisco.phonebook.respository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cisco.phonebook.model.PhoneDetails;

@Repository
public interface PhoneDetailsRespository extends JpaRepository<PhoneDetails, UUID> {

}