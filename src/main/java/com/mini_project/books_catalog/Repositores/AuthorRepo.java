package com.mini_project.books_catalog.Repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mini_project.books_catalog.Entities.Authors;

public interface AuthorRepo extends JpaRepository<Authors, Integer> {

    boolean existsByAuMobile(long auMobile);

    boolean existsByAuEmail(String auEmail);

}
