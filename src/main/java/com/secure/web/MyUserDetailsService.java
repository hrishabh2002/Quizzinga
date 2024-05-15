package com.secure.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.secure.web.entity.Student;
import com.secure.web.repository.StudentRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	StudentRepository stdRepo;
	@Autowired
	 private HttpSession session;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Student student=stdRepo.findByUsername(username);
		
		if(student==null) {
			System.out.println("some error");
			throw new UsernameNotFoundException("Error 404");
		}
		 session.setAttribute("loggedInStudent", student.getUsername());
		 System.out.println(student.getUsername());
		
		return new StudentPricipal(student);
	}

}
