package com.mini_project.books_catalog.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini_project.books_catalog.Entities.Authors;
import com.mini_project.books_catalog.Exceptions.CustomException;
import com.mini_project.books_catalog.Services.AuthorService;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<String> addAuthors(@RequestBody Authors authors)throws CustomException{
        authorService.addAuthor(authors);
        String msg = "Title Inserted successfully";
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("author/{id}")
    public ResponseEntity<String> updateAuthor(@PathVariable int id, @RequestBody Authors authors)
            throws CustomException {
        String msg = "details updated succesfully";
        authorService.updateAuthors(id, authors);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{Id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable int auId) throws CustomException {
        authorService.deleteAuthor(auId);
        String msg = "Author Deleted Succesfully";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

}
