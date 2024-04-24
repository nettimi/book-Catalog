package com.mini_project.books_catalog.Repositores;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mini_project.books_catalog.Entities.Authors;
import com.mini_project.books_catalog.Entities.Publishers;
import com.mini_project.books_catalog.Entities.Titles;

public interface TitleRepo extends JpaRepository<Titles, Integer> {

    public List<Titles> findAllByPublishers(Publishers publishers);

    public List<Titles> findByTitleNameIgnoreCaseContaining(String titleName);

    public List<Titles> findAllByPriceBetween(double min, double max);

    @Query(value = "SELECT TOP (?1) * FROM titles ORDER BY ytd_sales DESC", nativeQuery = true)
    List<Titles> findTopNByOrderByYtdSalesDesc(int n);

    public List<Titles> findByAuthors(Authors au);

}
