package com.cisco.phonebook.respository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cisco.phonebook.model.UserDetails;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, UUID> {



}
