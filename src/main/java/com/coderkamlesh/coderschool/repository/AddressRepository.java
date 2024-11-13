package com.coderkamlesh.coderschool.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderkamlesh.coderschool.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}