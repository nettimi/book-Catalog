package com.mini_project.books_catalog.Controllers;

import java.util.List;

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
import com.mini_project.books_catalog.Entities.Titles;
import com.mini_project.books_catalog.Exceptions.CustomException;
import com.mini_project.books_catalog.Repositores.TitleRepo;
import com.mini_project.books_catalog.Services.TitleService;
import com.mini_project.books_catalog.dto.TitleIdDetailsDTO;

@RestController
@RequestMapping("/api/titles")
public class TitleController {
    @Autowired
    private TitleService titleService;


    @Autowired
    TitleRepo titleRepo;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addtitle/{pubId}/{auId}/{rp}")
    public ResponseEntity<String> addTitles(@RequestBody Titles titles, @PathVariable int pubId, @PathVariable int auId,
            @PathVariable double rp) throws CustomException {
        titleService.addTitles(titles, pubId, auId, rp);
        String msg = "Title Inserted successfully";
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("title/{id}")
    public ResponseEntity<String> updateTitles(@PathVariable int id, @RequestBody Titles titles)
            throws CustomException {
        String msg = "details updated succesfully";
        titleService.updateTitles(id, titles);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTitle(@PathVariable("id") int titleId) throws CustomException {
        titleService.deleteTitle(titleId);
        String msg = "Title details deleted successfully";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping()
    public List<Titles> getAllTitles() throws CustomException{
        return titleService.getAllTitles();
    }

    

    @GetMapping("/publisher/{publisherId}")
    public ResponseEntity<?> getTitlesByPublisherId(@PathVariable int publisherId) throws CustomException {
        Publishers publisher = new Publishers();
        publisher.setPubId(publisherId);
        List<Titles> titles = titleService.getTitlesByPublisher(publisher);
        return new ResponseEntity<>(titles, HttpStatus.OK);
    }

    @GetMapping("/{titles}")
    public List<Titles> getTitlesByTitleName(@PathVariable("titles") String titleName) throws CustomException {
        return titleService.getTitlesByTitleName(titleName);
    }

    @GetMapping("/{minprice}/{maxprice}")
    public List<Titles> getTitlesByPrice(@PathVariable("minprice") double min, @PathVariable("maxprice") double max)throws CustomException {
        return titleService.getTitlesByPrice(min, max);
    }

    @GetMapping("/{titleId}/details")
    public ResponseEntity<TitleIdDetailsDTO> getTitleDetailsById(@PathVariable("titleId") int titleId)
            throws Exception {
        TitleIdDetailsDTO titleDetails = titleService.getTitleDetailsById(titleId);
        return new ResponseEntity<>(titleDetails, HttpStatus.OK);
    }

    @GetMapping("/top/ytdSales/{n}")
    public ResponseEntity<List<Titles>> getTopNTitlesByYtdSales(@PathVariable("n") int n) {
        try {
            List<Titles> titles = titleService.getTopNTitlesByYtdSales(n);
            return new ResponseEntity<>(titles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/auth/{authId}")
    public List<Titles> getTitlesByAuthor(@PathVariable int authId) throws CustomException{
        return titleService.getTitlesByAuthor(authId);
    }

}
