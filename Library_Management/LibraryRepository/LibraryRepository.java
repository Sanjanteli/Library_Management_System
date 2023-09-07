package com.example.Library_Management.LibraryRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Library_Management.Model.Book;

//This is a Repository Interface which contains the abstract methods and that extends JpaRepository
@Repository
public interface LibraryRepository extends JpaRepository<Book, Integer> {   
	List< Book> findByBookNameContainingIgnoreCase(String bookName);
	List<Book> findByIsPublished(boolean status);
	List< Book> findByAuthorName(Book authorName);
	List<Book> findByAuthorNameContainingIgnoreCase(String authorName);
	List<Book> findByAuthorId(Long authorId);
}