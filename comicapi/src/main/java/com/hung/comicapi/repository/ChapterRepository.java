package com.hung.comicapi.repository;

import com.hung.comicapi.model.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChapterRepository extends JpaRepository<Chapter,Integer> {
    @Query(value = "SELECT chapterID,chapter_number_pos,chapter_comicID,chapter.updated_at FROM comic.chapter LEFT JOIN comic ON comic.comicID = chapter.chapter_comicID WHERE comic.comicID = :comicID", nativeQuery = true)
    List<Chapter> findChaptersByComicID(@Param("comicID") String comicID);
    @Query(value = "SELECT chapter.chapterID, chapter.chapter_number_pos, chapter.chapter_comicID, chapter.updated_at AS updated_at " +
            "FROM comic " +
            "LEFT JOIN comic_trans ON comic.comicID = comic_trans.comiciDfk " +
            "LEFT JOIN transteam ON transteam.transteamID = comic_trans.transIDfk " +
            "LEFT JOIN chapter ON comic.comicID = chapter.chapter_comicID " +
            "INNER JOIN history_chapter ON history_chapter.chapter_id = chapter.chapterID " +
            "LEFT JOIN history_comic ON history_comic.comicIDfk = comic.comicID " +
            "LEFT JOIN history ON history.historyID = history_comic.historyIDfk " +
            "LEFT JOIN account ON account.id = history.accountID " +
            "WHERE account.username = :username", nativeQuery = true)
    List<Chapter> findChapterDetailsByUsername(@Param("username") String username);


    @Query(value = "SELECT * FROM comic.chapter WHERE chapter_comicID = ?1 AND chapter_number_pos = ?2", nativeQuery = true)
    List<Chapter> getChapterID(String comicId, String numberPos);
}
