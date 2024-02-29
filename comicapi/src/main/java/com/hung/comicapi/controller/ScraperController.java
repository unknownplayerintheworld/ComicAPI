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
//            for (int i = 1; i < 4; i++) {
//                String url = "https://doctruyenonline.vn/truyen-tranh/dragon-ball-bay-vien-ngoc-rong/chapter-" +i;
//                scraperServices.scrapeAndSaveImages(url);
//            }
            String url = "https://doctruyenonline.vn/truyen-tranh/toan-co-tu/chapter-3";
            scraperServices.scrapeAndSaveImages(url);
            return ResponseEntity.status(HttpStatus.OK).body(new DataJSON(true, "Get images successful", ""));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DataJSON(false, e.getMessage(), ""));
        }
    }
}
