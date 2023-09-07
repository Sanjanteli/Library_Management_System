package com.example.Library_Management.LibraryController;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Library_Management.LibraryRepository.AuthorRepository;
import com.example.Library_Management.LibraryRepository.LibraryRepository;
import com.example.Library_Management.LibraryServiceImpl.LibraryServiceImpl;
import com.example.Library_Management.LibraryServices.LibraryServices;
import com.example.Library_Management.Model.Author;
import com.example.Library_Management.Model.Book;

/* The client is send request through the controller*/
//This is a Library Controller class with the help of this class client send the Requests
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/books")

//@RequestMapping("/libraryManagement.com")

public class LibraryController {
	@Autowired
    AuthorRepository authorRepository;
	@Autowired 
	public LibraryServiceImpl libraryServicesImpl;
	public LibraryController (LibraryServiceImpl libraryServicesImpl) {
		this.libraryServicesImpl =libraryServicesImpl;
	}
	// Read the all book details by Database 
	@GetMapping
	public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) String name) {
		try {
			List<Book> bookList  = libraryServicesImpl.getbookFromDb(name);			
			if (bookList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(bookList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/auth")
	public ResponseEntity<List<Book>> getBookDetailsByAuthorName(@RequestParam(required = false) String name) {
		try {
			List<Book> bookList  = libraryServicesImpl.getBookDetailsByAuthorName(name);			
			if (bookList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(bookList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	// create library rest api
//	@PostMapping
//	public ResponseEntity<Book> saveBook(@RequestBody Book name) {
//		try {
//			return new ResponseEntity<>(libraryServicesImpl.saveBook(name), HttpStatus.CREATED);
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	@PostMapping("/{authorId}")
	public ResponseEntity<Book> saveBook(@RequestBody Book bookObj, @PathVariable("authorId")int Id) {
		
		Author obj = authorRepository.findById(Id).get();
		

		bookObj.setAuthor(obj);
		return new ResponseEntity<>(libraryServicesImpl.saveBook(bookObj), HttpStatus.CREATED); 
		
	
//	}).orElseThrow(() -> new Exception("no author id available"));
//	return new ResponseEntity<>(bookInsertedToDb, HttpStatus.CREATED);
	}
	//Read any Data particular data from database with the help of Id

	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id")  int bookId){
		System.out.println(bookId);
		//serv.methid name 
		Optional<Book> existBook = libraryServicesImpl.getBookById(bookId);

		if (existBook.isPresent()) {
			return new ResponseEntity<>(existBook.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// update library rest api
	@PutMapping("/{bookId}")
	public ResponseEntity<Book> updateLibraryDetalisById(@PathVariable int bookId, @RequestBody Book updatedBookDetails){

		Optional<Book> bookData=libraryServicesImpl.getBookById(bookId);

		if(bookData.isPresent())
		{

			libraryServicesImpl.updateBookDetails(bookData, updatedBookDetails);
			return new ResponseEntity<>(libraryServicesImpl.updateBookDetails(bookData, updatedBookDetails),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


	// Any one delete book by Id
	@DeleteMapping("/deleteBook/{id}")
	public ResponseEntity<String> deleteBookById(@PathVariable("id")  int bookId){
		Optional<Book> bookData=libraryServicesImpl.getBookById(bookId);
		try {
			if(bookData.isPresent())
			{
				libraryServicesImpl.deletebookById(bookId);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	//DeleteAll Data from the database 

	@DeleteMapping
	public ResponseEntity<HttpStatus> deleteAllBook(){
		try
		{
			libraryServicesImpl.deleteAllBooks();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch(Exception exception) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	//To find the domain where book are publish 

	@GetMapping("/bookPublish")
	public ResponseEntity<List<Book>> findByBookPublish() {
		try {
			List<Book> library = libraryServicesImpl.findByIsPublished(true);

			if (library.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(library, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/bookNotPublish")
	//http://localhost:8080/api/v1/books/bookNotPublish 
	public ResponseEntity<List<Book>> findByBookNotPublish() {
		try {
			List<Book> library = libraryServicesImpl.findByIsPublished(false);

			if (library.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(library, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/price/{dir}")
	public ResponseEntity<List<Book>> getAllBooksByPriceHighToLow(@PathVariable("dir") String direction, @RequestParam(required=true) String price)
	{
		try {
			List<Book> library = libraryServicesImpl.getAllBooksByPrice(price,direction);
			if(library.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(library, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/bookName/{dir}")
	public ResponseEntity<List<Book>> getBookNameAtoZ(@PathVariable("dir") String direction,  @RequestParam(required = true) String bookName) {
		try {
			List<Book> bookList  = libraryServicesImpl.getAllBooksByBookName( bookName,  direction);

			if (bookList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(bookList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/authorName/{dir}")
	public ResponseEntity<List<Book>> getAuthorNameAtoZ(@PathVariable("dir") String direction,  @RequestParam(required = true) String authorName) {
		try {
			List<Book> bookList  = libraryServicesImpl.getAllBooksByAuthorName(authorName, direction);				
			if (bookList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(bookList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
//	@PostMapping("/{bookId}")
//	public ResponseEntity<Map<String,String>> addBookDetails(@PathVariable("bookId") int bookId,@RequestBody Author author)
//	{
//  	Optional<Book> existBook = libraryServicesImpl.getBookById(bookId);
//
//		if (existBook.isPresent()) {
//			Book bookFromDb = existBook.get();
//			bookFromDb.setAuthorDetails(author);
//			libraryServicesImpl.saveBook(bookFromDb);
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
//		else
//		{
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
	
	@GetMapping("/byAuthor/{authorId}")
  public List<Book> getBooksByAuthorId(@PathVariable Long authorId) {
      return libraryServicesImpl.getBooksByAuthorId(authorId);
  }
}