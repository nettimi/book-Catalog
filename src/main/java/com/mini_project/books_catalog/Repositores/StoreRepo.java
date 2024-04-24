package com.mini_project.books_catalog.Repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mini_project.books_catalog.Entities.Store;

public interface StoreRepo extends JpaRepository<Store, Integer> {

}
