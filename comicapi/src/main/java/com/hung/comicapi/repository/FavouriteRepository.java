package com.hung.comicapi.repository;

import com.hung.comicapi.model.favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavouriteRepository extends JpaRepository<favourite,Integer> {
    @Query(value = "SELECT * FROM comic.favourite " +
            "LEFT JOIN account ON favourite.accountID = account.id " +
            "LEFT JOIN favourite_comic ON favourite_comic.favouriteIDfk = favourite.favouriteID " +
            "LEFT JOIN comic ON comic.comicID = favourite_comic.comicIDfk " +
            "WHERE account.username = :username AND comic.comicID = :comicId",
            nativeQuery = true)
    List<favourite> getFavouriteStatus(@Param("username") String username, @Param("comicId") String comicID);

    @Query(value = "SELECT * FROM comic.favourite " +
            "LEFT JOIN account ON favourite.accountID = account.id " +
            "LEFT JOIN favourite_comic ON favourite_comic.favouriteIDfk = favourite.favouriteID " +
            "LEFT JOIN comic ON comic.comicID = favourite_comic.comicIDfk " +
            "WHERE account.username = :username ",
            nativeQuery = true)
    List<favourite> getFavouriteID(@Param("username") String username);
}
