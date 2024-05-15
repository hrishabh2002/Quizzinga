package com.secure.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secure.web.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	
	Student findByUsername(String username);
}
