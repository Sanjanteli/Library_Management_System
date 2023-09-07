package com.example.Library_Management.LibraryController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.Library_Management.Model.Book;
import com.example.Library_Management.LibraryServices.EmailService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/email")
public class EmailController {
	@Autowired
	private EmailService emailService;
	@RequestMapping("/")
	public ResponseEntity<String> checkEmail(@RequestParam(required = false) String userEmail, @RequestBody Book bookDetails )  //user email, rest obj(@request Body 
	{
		String bookName=bookDetails.getBookName();
		String strMsg = bookDetails.getBookName()+" "+bookDetails.getAuthorName()+" "+bookDetails.getIsPublished();
		
	System.out.println(bookDetails);	
	emailService.sendEmail(userEmail, "Your Book Lending is confirmed!", "We are happy to inform you that your Lending for a book in "
			+ " Book Name:"+ bookName +""  +" is confirmed! You can find your furthur information below.\n"
			+strMsg+" ");
	return new ResponseEntity<>("Message Send",HttpStatus.CREATED);
	}
}