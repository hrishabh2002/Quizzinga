package com.secure.web.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.secure.web.entity.ScoreEntry;
import com.secure.web.entity.Student;
import com.secure.web.repository.StudentRepository;

@Controller
public class HomeController {
	
	@Autowired
    private StudentRepository stdRepo;
	
	@GetMapping("/home")
	public String home(Model model, Principal principal) {
	    String username = principal.getName();
	    Student loggedInStudent = stdRepo.findByUsername(username);
	    if (loggedInStudent != null) {
	        model.addAttribute("loggedInStudent", loggedInStudent.getUsername());
	        model.addAttribute("loggedInScores", loggedInStudent.getScores());
	    }
	    return "home";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	

	
	@GetMapping("/register")
	public String registerPage() {
		return "register";
	}
	
	@PostMapping("/register-process")
	public String processRegister(@RequestParam("name") String name, @RequestParam("password") String password) {
	    Student student = new Student(name, password);
	    stdRepo.save(student);
	    return "login";
	}

	@PostMapping("/submit-process")
	public String processSubmit(Model model, @RequestParam("score") int scoreValue,@RequestParam("category") String category, @RequestParam("duration") String duration, Principal principal) {
	    String username = principal.getName();
	    Student student = stdRepo.findByUsername(username);
	    if (student != null) {
	        List<ScoreEntry> scores = student.getScores();
	        if (scores == null) {
	            scores = new ArrayList<>();
	        }
	        scores.add(new ScoreEntry(category, scoreValue, new Date())); 
	        student.setScores(scores);
	        stdRepo.save(student);
	    }
	    return "submit";
	}	
}
