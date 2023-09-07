package com.example.Library_Management.LibraryServiceImpl;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Collections;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.Library_Management.LibraryRepository.LibraryRepository;
import com.example.Library_Management.LibraryServices.LibraryServices;
import com.example.Library_Management.Model.Book;

import jakarta.persistence.metamodel.CollectionAttribute;


/**
 * from the interface Library Services
 * implement the abstract methods 
 * and use annotations
 * */
@Service
public class LibraryServiceImpl implements LibraryServices {

	LibraryRepository bookRepository;

	public LibraryServiceImpl(LibraryRepository bookRepository) 
	{
		this.bookRepository = bookRepository;
	}

	//It is saveBook method use for to store book details in Rest API
	@Override
	public Book saveBook(Book book)
	{
		return bookRepository.save(book);  
	}

	//To give the book details by Id
	public Optional<Book> getBookById(int bookId)
	{ 
		Optional<Book> book=bookRepository.findById(bookId);
		return book;
	}


	//Update any Data from rest API
	@Override
	public Book updateBookDetails( Optional<Book> bookDet,Book newVal) {

		Book book =bookDet.get();
		book.setBookName(newVal.getBookName());
		book.setPrice(newVal.getPrice());
		book.setNoCopies(newVal.getNoCopies());
		book.setAuthorName(newVal.getAuthorName());
		book.setIsPublished(newVal.getIsPublished());

		return bookRepository.save(book);
	}

	// This method give book details by bookName with bookId
	public Book getByBookName(int bookId) { 
		Optional<Book> library = bookRepository.findById(bookId);  
		System.out.println(bookId);
		System.out.println(library.isPresent());
		if(library.isPresent()) {
			Book obj = library.get();
			System.out.println(obj);
			return library.get();
		}
		else {
			return null;
		}
	}
	
	
	public Book getByAuthorName(int bookId) { 
		Optional<Book> library = bookRepository.findById(bookId);  
		System.out.println(bookId);
		System.out.println(library.isPresent());
		if(library.isPresent()) {
			Book obj = library.get();
			System.out.println(obj);
			return library.get();
		}
		else {
			return null;
		}
	}

	// Delete all books from the rest API
	@Override
	public Optional<Book> deleteAll() {
		bookRepository.deleteAll();
		return Optional.empty();
	}

	//Delete All data by id
	@Override
	public void  deletebookById(int bookId) {
		bookRepository.deleteById(bookId);
	}

	// Get the book details by book name 
	public List<Book>getByBookDetailsByBookName(String name) {
		List<Book> bookList = new ArrayList<>();
		bookRepository.findByBookNameContainingIgnoreCase(name).forEach(bookList::add);
		return bookList;
	}

	//Find the book from DB with rest API
	@Override
	public List<Book> getbookFromDb(String title) {
		List<Book> book = new ArrayList<Book>();
		if (title == null) 
			bookRepository.findAll().forEach(book::add);
		else
			bookRepository.findByBookNameContainingIgnoreCase(title).forEach(book::add);
		return  book;
	}
	
	public List<Book>getBookDetailsByAuthorName(String authorName)
	{
		List<Book> book = new ArrayList<Book>();
		bookRepository.findByAuthorNameContainingIgnoreCase(authorName).forEach(book::add);
		return book;
	}

	@Override
	public void deleteAllBooks() {
		bookRepository.deleteAll();	
	}

	public List<Book> findByIsPublished(boolean status) {
		return bookRepository. findByIsPublished(status);
	}

	

	private Sort.Direction getSortDirection(String direction) {
		if (direction.equals("asc")) {
			return Sort.Direction.ASC;
		} else if (direction.equals("desc")) {
			return Sort.Direction.DESC;
		}
		return Sort.Direction.ASC;
	}

	public List<Book> getAllBooksByPriceDesc(String price,String direction) {
		List<Book> book = bookRepository.findAll(Sort.by(getSortDirection(direction),price));
		return book;

	}

	public List<Book> getAllBooksByBookName(String bookName,  String direction) {
		List<Book> book = bookRepository.findAll(Sort.by(getSortDirection(direction),bookName));
		return book;
	}

	public List<Book> getAllBooksByAuthorName(String authorName, String direction) {
		List<Book> book = bookRepository.findAll(Sort.by(getSortDirection(direction),authorName));
		return book;
	}
	public List<Book> getAllBooksByPrice(String price,String direction)
	{
		List<Book> book = bookRepository.findAll(Sort.by(getSortDirection(direction),price));
		return book;
	}
	
	 public List<Book> getBooksByAuthorId(Long authorId) {
	        return bookRepository.findByAuthorId(authorId);
	    }
}