package com.hung.comicapi.controller;

import com.hung.comicapi.model.Comic;
import com.hung.comicapi.model.DataJSON;
import com.hung.comicapi.model.Genre;
import com.hung.comicapi.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/genre")

public class GenreController {
    @Autowired
    private GenreService genreService;
    @GetMapping("/")
    public ResponseEntity<DataJSON> getAllGenres(){
        try{
            List<Genre> genres = genreService.findAllGenres();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get all genres successful",genres)
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),"")
            );
        }
    }
}
