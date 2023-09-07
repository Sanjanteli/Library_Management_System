package com.example.Library_Management.LibraryController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Library_Management.LibraryRepository.LibraryRepository;
import com.example.Library_Management.LibraryRepository.UserRepository;
import com.example.Library_Management.LibraryServiceImpl.UserServiceImpl;
import com.example.Library_Management.Model.Book;
import com.example.Library_Management.Model.User;




// many comments on one emp
//many user book one rest
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {
	//open postm
	@Autowired
	private UserServiceImpl userServ;
	
	
	@Autowired
	private LibraryRepository bookRepo;
	
	
		@PostMapping("/bookTable/{bookId}")
		public ResponseEntity<Map<String,String>> addBooking(@PathVariable("bookId") int bookId,@RequestBody User user)
		{
		
				User obj=new User();
				Random rand = new Random();
				obj.setUserEmail(user.getUserEmail());
				int userId=rand.nextInt(11111);
				obj.setUserName(user.getUserName());
				obj.setDate(user.getDate());
				obj.setUserID(userId);
				obj.setBookDetails(this.bookRepo.findById( bookId).get());
				this.userServ.addUser(obj);
				Map<String,String> response=new HashMap<String,String>();
				response.put("status", "success");
				response.put("message", "Comment added!!");
			
					return new ResponseEntity<Map<String,String>>(response, HttpStatus.CREATED);
			
		}
		
		@GetMapping("/bookedUsers/{bookId}")
		public List<User> getCommentsByBookId(@PathVariable long bookId)
		{
			List<User> userList = new ArrayList<User>();
			userList = this.userServ.findBookedUsersByBookId(bookId);
			
			return  userList;
			
		}

}
