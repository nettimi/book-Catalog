package com.mini_project.books_catalog.Repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mini_project.books_catalog.Entities.Publishers;

public interface PublisherRepo extends JpaRepository<Publishers, Integer> {

    boolean existsByPubEmail(String pubEmail);

    boolean existsByPubName(String pubName);

}
