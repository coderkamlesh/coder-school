package com.coderkamlesh.coderschool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderkamlesh.coderschool.model.Courses;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer> {
	List<Courses> findByOrderByNameDesc();
	List<Courses> findByOrderByName();
}
