package com.mini_project.books_catalog.Repositores;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mini_project.books_catalog.Entities.TitleAuthors;
import com.mini_project.books_catalog.Entities.TitleAuthorsId;

public interface TitleAuthorRepo extends JpaRepository<TitleAuthors, TitleAuthorsId> {

    @Query(value = "select titles_title_id from title_authors where authors_au_id = ?1", nativeQuery = true)
    List<Integer> findAllTitlesByAuthor(int authId);

}
