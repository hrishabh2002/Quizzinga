package com.secure.web.quiz;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.secure.web.entity.Questions;
import com.secure.web.repository.QuestionRepository;

@Service
public class MyQuestionService {
	
	@Autowired
	private QuestionRepository questionRepo;
	
	public List<Questions> getAllQuestions() {
		
		return questionRepo.findAll();
	}

	public Optional<Questions> getQuestionById(Long id) {
		
		return questionRepo.findById(id);
	}

	
	public List<Questions> getQuestionsForUser(Integer num, String topic) {
		
		Pageable pageable=PageRequest.of(0, num);
		return questionRepo.findByTopic(topic,pageable).getContent();
	}
}
