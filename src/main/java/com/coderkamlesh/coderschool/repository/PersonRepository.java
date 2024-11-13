package com.coderkamlesh.coderschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderkamlesh.coderschool.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
	Person readByEmail(String email);
}
