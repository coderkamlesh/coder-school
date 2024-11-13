package com.coderkamlesh.coderschool.repository;

import org.springframework.stereotype.Repository;

import com.coderkamlesh.coderschool.model.CoderClass;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CoderClassRepository  extends JpaRepository<CoderClass,Integer> {
	
}
