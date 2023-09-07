package com.example.Library_Management.LibraryRepository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Library_Management.Model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {   
	Author save(Author author);
	void deleteById(int id);
	Iterable<Author> findByAuthorNameContainingIgnoreCase(String title);
}
