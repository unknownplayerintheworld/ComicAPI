package com.hung.comicapi.repository;

import com.hung.comicapi.model.ImageChapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface ImageChapterRepository extends JpaRepository<ImageChapter,Integer> {
    @Query(value = "SELECT * FROM comic.comic " +
            "LEFT JOIN chapter ON comic.comicID = chapter.chapter_comicID " +
            "LEFT JOIN imagechapter ON chapter.chapterID = imagechapter.image_chapterID " +
            "WHERE chapter_number_pos = :chapterPos AND comic.comicID = :comicID", nativeQuery = true)
    List<ImageChapter> getImageChapterWithChapterID(int chapterPos,int comicID);


}
