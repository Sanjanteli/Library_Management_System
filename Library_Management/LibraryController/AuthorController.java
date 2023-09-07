package com.example.Library_Management.LibraryController;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

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
import com.example.Library_Management.Model.Author;
import com.example.Library_Management.Model.Book;
import com.example.Library_Management.Model.User;
import com.example.Library_Management.LibraryRepository.AuthorRepository;
import com.example.Library_Management.LibraryRepository.LibraryRepository;
import com.example.Library_Management.LibraryServices.AuthorService;
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/authors")
@RestController()
public class AuthorController {

	@Autowired
	AuthorService authorService;

	@PostMapping
	public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
		try {
			authorService.createAuthor(author);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("/updateAuthor/{id}")
	public ResponseEntity<Author> updateAuthor(@PathVariable int id, @RequestBody Author updateAuthor){

		Optional<Author> authorData=authorService.getAuthorById(id);

		if(authorData.isPresent())
		{
			authorService.updateAuthor(authorData, updateAuthor);
			return new ResponseEntity<>(authorService.updateAuthor(authorData, updateAuthor),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deleteAuthor/{id}")
	public ResponseEntity<String> deleteAuthorById(@PathVariable("id")  int id){
		Optional<Author> authorData=authorService.getAuthorById(id);
		try {
			if(authorData.isPresent())
			{
				authorService.deleteAuthor(id);
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
	
	@GetMapping("/{id}")
    public ResponseEntity<Author> getAllAuthorById(@PathVariable int id) {
        Optional<Author> author = authorService.getAuthorById(id);
        if (author.isPresent()) {
            return ResponseEntity.ok(author.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	@GetMapping
	public ResponseEntity<List<Author>> getAllAuthor(@RequestParam(required = false) String name) {
		try {
			List<Author> authorList  = authorService.getAuthorFromDb(name);

			
			if (authorList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(authorList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}