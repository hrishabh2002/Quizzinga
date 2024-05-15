package com.secure.web.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.secure.web.entity.Questions;

public interface QuestionRepository extends JpaRepository<Questions, Long> {


	Page<Questions> findByTopic(String topic, Pageable pageable);
	
}
