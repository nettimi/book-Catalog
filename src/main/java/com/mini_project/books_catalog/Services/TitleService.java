package com.mini_project.books_catalog.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mini_project.books_catalog.Entities.Authors;
import com.mini_project.books_catalog.Entities.Publishers;
import com.mini_project.books_catalog.Entities.TitleAuthors;
import com.mini_project.books_catalog.Entities.Titles;
import com.mini_project.books_catalog.Exceptions.CustomException;
import com.mini_project.books_catalog.Repositores.AuthorRepo;
import com.mini_project.books_catalog.Repositores.PublisherRepo;
import com.mini_project.books_catalog.Repositores.SaleRepo;
import com.mini_project.books_catalog.Repositores.StoreRepo;
import com.mini_project.books_catalog.Repositores.TitleAuthorRepo;
import com.mini_project.books_catalog.Repositores.TitleRepo;
import com.mini_project.books_catalog.dto.TitleIdDetailsDTO;

@Service
public class TitleService {

    @Autowired
    TitleRepo titleRepo;

    @Autowired
    PublisherRepo publisherRepo;

    @Autowired
    AuthorRepo authorRepo;

    @Autowired
    StoreRepo storeRepo;

    @Autowired
    TitleAuthorRepo titleAuthorRepo;

    @Autowired
    SaleRepo saleRepo;

    public Titles addTitles(Titles titles, int pubId, int auId, double rp) throws CustomException {
        if(titles.getTitleName() == null || titles.getPrice() == 0 || titles.getYtdSales() == 0 || titles.getReleaseYear() == null)
            throw new CustomException("Enter all title details");
        Publishers pub = publisherRepo.findById(pubId).orElseThrow(() -> new CustomException("Publisher not found"));

        Authors auth = authorRepo.findById(auId).orElseThrow(() -> new CustomException("Author not found"));

        titles.setPublishers(pub);

        TitleAuthors titleAuthors = new TitleAuthors();
        titleAuthors.setTitles(titles);
        titleAuthors.setAuthors(auth);
        titleAuthors.setRoyaltyPct(rp);

        titles.getAuthors().add(titleAuthors);

        return titleRepo.save(titles);
    }

    public Titles updateTitles(int titleId, Titles titles) throws CustomException {
        Titles title = titleRepo.findById(titleId).orElseThrow(() -> new CustomException("Title Id  not found"));
        if (titles.getTitleName() != null)
            title.setTitleName(titles.getTitleName());
        if (titles.getPrice() != 0)
            title.setPrice(titles.getPrice());
        if (titles.getYtdSales() != 0)
            title.setYtdSales(titles.getYtdSales());
        if (titles.getReleaseYear() != null)
            title.setReleaseYear(titles.getReleaseYear());

        return titleRepo.save(title);
    }

    public List<Titles> getAllTitles() throws CustomException {
        List<Titles> titles = titleRepo.findAll();

        if (titles.isEmpty()) {
            throw new CustomException("No data in the title table");
        }

        return titles;
    }

    public void deleteTitle(int titleId) throws CustomException {
        if (!titleRepo.existsById(titleId)) {
            throw new CustomException(String.format("title id  %s  not found", titleId));
        }
        titleRepo.deleteById(titleId);
    }

    public List<Titles> getTitlesByPublisher(Publishers publisher) throws CustomException {
        List<Titles> titles = titleRepo.findAllByPublishers(publisher);

        if (titles.isEmpty()) {
            throw new CustomException("No titles found for publisher ID: " + publisher.getPubId());
        }

        return titles;
    }

    public List<Titles> getTitlesByTitleName(String titleName) throws CustomException {
        List<Titles> titles = titleRepo.findByTitleNameIgnoreCaseContaining(titleName);

        if (titles.isEmpty()) {
            throw new CustomException("No titles found for the title name: " + titleName);
        }

        return titles;
    }

    public List<Titles> getTitlesByPrice(double min, double max) throws CustomException {
        List<Titles> titles = titleRepo.findAllByPriceBetween(min, max);

        if (titles.isEmpty()) {
            throw new CustomException("No titles found within the price range: " + min + " to " + max);
        }

        return titles;
    }

    public List<Titles> getTopNTitlesByYtdSales(int n) {
        return titleRepo.findTopNByOrderByYtdSalesDesc(n);
    }

    public TitleIdDetailsDTO getTitleDetailsById(int id) throws CustomException {
        Titles title = titleRepo.findById(id)
                .orElseThrow(() -> new CustomException("TitleId not found"));
        List<String> authorNames = title.getAuthors().stream()
                .map(author -> author.getAuthors().getAuName())
                .collect(Collectors.toList());

        String publisherName = title.getPublishers().getPubName();

        return new TitleIdDetailsDTO(title.getTitleName(), title.getPrice(), title.getYtdSales(), authorNames,
                publisherName);
    }

    public List<Titles> getTitlesByAuthor(int authId) throws CustomException {
        // get all titles by author
        List<Integer> titleIds = titleAuthorRepo.findAllTitlesByAuthor(authId);
        if (titleIds.isEmpty()) {
            throw new CustomException("No titles found for the author ID: " + authId);
        }

        List<Titles> titles = titleRepo.findAllById(titleIds);

        return titles;
    }

}
