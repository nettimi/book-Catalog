package com.mini_project.books_catalog.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mini_project.books_catalog.Entities.Publishers;
import com.mini_project.books_catalog.Exceptions.CustomException;
import com.mini_project.books_catalog.Repositores.PublisherRepo;

@Service
public class PublisherService {
    @Autowired
    private PublisherRepo publisherRepo;

    public Publishers addPublisher(Publishers publishers) throws CustomException {
        if(publishers.getPubName() == null || publishers.getPubEmail() == null || publishers.getPubCity() == null || publishers.getPubCountry() ==null)
            throw new CustomException("Enter all publisher details");
        if (publisherRepo.existsByPubName(publishers.getPubName()))
            throw new CustomException("Publisher name already present. Please give another name");
        if (!isValidEmail(publishers.getPubEmail()))
            throw new CustomException("Invalid email format");
        if (publisherRepo.existsByPubEmail(publishers.getPubEmail()))
            throw new CustomException("Email already present. Please give another Email");
        return publisherRepo.save(publishers);
    }

    private boolean isValidEmail(String email) throws CustomException {
        return email != null && email.matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

    public Publishers getPublisherById(int pubId) {
        return publisherRepo.findById(pubId).orElse(null);
    }

    public Publishers updatePublishers(int pubId, Publishers publishers) throws CustomException {
        Publishers pub = publisherRepo.findById(pubId).orElseThrow(() -> new CustomException("Publisher not found"));
        if (publishers.getPubName() != null)
            pub.setPubName(publishers.getPubName());
        if (publishers.getPubEmail() != null)
            pub.setPubEmail(publishers.getPubEmail());
        if (publishers.getPubCity() != null)
            pub.setPubCity(publishers.getPubCity());
        if (publishers.getPubCountry() != null)
            pub.setPubCountry(publishers.getPubCountry());
        return publisherRepo.save(pub);
    }

    public void deletePublisher(int pubId) throws CustomException {
        if (!publisherRepo.existsById(pubId)) {
            throw new CustomException(String.format("publisher id  %s  not found", pubId));
        }
        publisherRepo.deleteById(pubId);

    }
}
