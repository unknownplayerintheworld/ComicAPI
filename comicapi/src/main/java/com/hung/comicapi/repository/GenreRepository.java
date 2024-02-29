package com.hung.comicapi.repository;

import com.hung.comicapi.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre,Integer> {
    @Query(value = "Select * from genre",nativeQuery = true)
    List<Genre> findAllGenre();

}
