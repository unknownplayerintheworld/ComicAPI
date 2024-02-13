package com.hung.comicapi.service;

import com.hung.comicapi.model.Comic;
import com.hung.comicapi.model.Genre;
import com.hung.comicapi.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;
    public List<Genre> findAllGenres(){
        try{
            List<Genre> genres = genreRepository.findAllGenre();
            if(genres.isEmpty()){
                throw new RuntimeException("There is not having a record genres entires!");
            }
            else{
                return genres;
            }
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
