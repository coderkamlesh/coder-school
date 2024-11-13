package com.coderkamlesh.coderschool.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.coderkamlesh.coderschool.model.Contact;

import jakarta.transaction.Transactional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

	List<Contact> findByStatus(String status);

	@Query("SELECT c FROM Contact c WHERE c.status = :status")
//	    @Query(value = "SELECT * FROM contact_msg c WHERE c.status = :status",nativeQuery = true)
	Page<Contact> findByStatus(String status, Pageable pageable);

	@Transactional
	@Modifying
	@Query("UPDATE Contact c SET c.status = ?1 WHERE c.contact_id = ?2")
	int updateStatusById(String status, int id);
}
