package com.example.Library_Management.LibraryServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Library_Management.Model.User;
import com.example.Library_Management.Model.User;

import com.example.Library_Management.LibraryRepository.UserRepository;

@Service
public class UserServiceImpl {
	
	@Autowired
	private UserRepository userRepo;

	public void addUser(User obj) {
		
		this.userRepo.save(obj);
	}
	
	public List<User> findBookedUsersByBookId(long bookId) {
		
		return this.userRepo.findUsersByBookId(bookId);
	}
	

}