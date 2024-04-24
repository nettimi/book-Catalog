package com.mini_project.books_catalog.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini_project.books_catalog.Entities.Publishers;
import com.mini_project.books_catalog.Exceptions.CustomException;
import com.mini_project.books_catalog.Services.PublisherService;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {
    @Autowired
    private PublisherService publisherService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<String> addPublisher(@RequestBody Publishers publisher)throws CustomException {
        publisherService.addPublisher(publisher);
        String msg = "Publisher Inserted successfully";
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("pub/{id}")
    public ResponseEntity<String> updatePublisher(@PathVariable int id, @RequestBody Publishers publishers)
            throws CustomException {
        String msg = "details updated succesfully";
        publisherService.updatePublishers(id, publishers);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/{pubId}")
    public Publishers getPublisherById(@PathVariable int pubId) {
        return publisherService.getPublisherById(pubId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{pubId}")
    public ResponseEntity<String> deletePublisher(@PathVariable("pubId") int pubId) throws CustomException {
        publisherService.deletePublisher(pubId);
        String msg = "Publisher Deleted Succesfully";
        return new ResponseEntity<>(msg, HttpStatus.OK);

    }

}
