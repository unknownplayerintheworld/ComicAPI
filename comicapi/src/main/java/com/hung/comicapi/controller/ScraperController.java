package com.hung.comicapi.controller;

import com.hung.comicapi.model.DataJSON;
import com.hung.comicapi.service.ScraperServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/scraper")
public class ScraperController {
    @Autowired
    private ScraperServices scraperServices;

    @GetMapping("/")
    ResponseEntity<DataJSON> getImgComic(){
        try {
            scraperServices = new ScraperServices();
            String url = "https://kenhcomic.blogspot.com/2017/01/o-long-vien-tap-1-phan-1.html";
            String outputDirectory = "upload/nettruyen";
            scraperServices.scrapeImages(url, outputDirectory);
            return ResponseEntity.status(HttpStatus.OK).body(new DataJSON(true, "Get images successful", outputDirectory));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DataJSON(false, e.getMessage(), ""));
        }
    }
}
