package com.mini_project.books_catalog.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mini_project.books_catalog.Entities.Authors;
import com.mini_project.books_catalog.Exceptions.CustomException;
import com.mini_project.books_catalog.Repositores.AuthorRepo;

@Service
public class AuthorService {
    @Autowired
    AuthorRepo authorRepo;

    public Authors addAuthor(Authors author) throws CustomException {
        if (author.getAuName() == null || author.getAuEmail() == null || author.getAuMobile() == 0 || author.getAuCity() == null || author.getAuCountry() == null)
            throw new CustomException("Enter all author details");
        if (!isValidEmail(author.getAuEmail()))
            throw new CustomException("Invalid email format");
        if (authorRepo.existsByAuEmail(author.getAuEmail()))
            throw new CustomException("Email alreday present. Please give another email");
        if (authorRepo.existsByAuMobile(author.getAuMobile()))
            throw new CustomException("Mobile number alreday present. Please give another mobile number");
        return authorRepo.save(author);
    }

    private boolean isValidEmail(String email) throws CustomException {
        return email != null && email.matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

    public void deleteAuthor(int auId) throws CustomException {

        if (!authorRepo.existsById(auId)) {
            throw new CustomException(String.format("author id  %s  not found", auId));
        }
        authorRepo.deleteById(auId);

    }

    public Authors getAuthorById(int pubId) {
        return authorRepo.findById(pubId).orElse(null);
    }

    public Authors getAuthorByAuthorId(int id) {
        return authorRepo.findById(id).get();
    }

    public Authors updateAuthors(int auId, Authors authors) throws CustomException {
        Authors auth = authorRepo.findById(auId).orElseThrow(() -> new CustomException("Publisher not found"));
        if (authors.getAuName() != null)
            auth.setAuName(authors.getAuName());
        if (authors.getAuEmail() != null)
            auth.setAuEmail(authors.getAuEmail());
        if (authors.getAuMobile() != 0)
            auth.setAuMobile(authors.getAuMobile());
        if (authors.getAuCity() != null)
            auth.setAuCity(authors.getAuCity());
        if (authors.getAuCountry() != null)
            auth.setAuCountry(authors.getAuCountry());
        return authorRepo.save(auth);
    }
}
