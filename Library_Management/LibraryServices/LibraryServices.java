package com.example.Library_Management.LibraryServices;

import java.util.List;
import java.util.Optional;

import com.example.Library_Management.Model.Book;


/*Method to save a new Library item to the database
	Method to retrieve a list of all book items from the database
	Method to retrieve  a specific book item by its ID from the database
	Method to update the details of an existing book item in the database
	Method to delete a specific book item by its ID from the database
 */
public interface LibraryServices {
	Book saveBook(Book book);
	List<Book>getbookFromDb(String title);
	Optional<Book> getBookById(int bookId);
	Book updateBookDetails(Optional<Book> book, Book newVal);
	Optional<Book> deleteAll();
	void deleteAllBooks();
	void deletebookById(int bookId);
} 




