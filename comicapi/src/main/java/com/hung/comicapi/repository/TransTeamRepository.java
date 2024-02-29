package com.hung.comicapi.repository;

import com.hung.comicapi.model.Comic;
import com.hung.comicapi.model.transteam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransTeamRepository extends JpaRepository<transteam,Integer> {
    @Query(value = "SELECT * FROM comic LEFT JOIN comic_trans ON comic.comicID = comic_trans.comicIDfk " +
            "LEFT JOIN transteam ON comic_trans.transIDfk = transteam.transteamID " +
            "WHERE comic.comicID = ?1", nativeQuery = true)
    List<transteam> findByComicName(String comicID);
    @Query(value = "SELECT * FROM comic LEFT JOIN comic_trans ON comic.comicID = comic_trans.comicIDfk " +
            "LEFT JOIN transteam ON comic_trans.transIDfk = transteam.transteamID " +
            "WHERE transteam.transteamID = ?1", nativeQuery = true)
    List<transteam> findByTransTeamID(String transteamID);

    @Query(value = "SELECT * FROM comic " +
            "LEFT JOIN comic_trans ON comic.comicID = comic_trans.comicIDfk " +
            "LEFT JOIN transteam ON transteam.transteamID = comic_trans.transIDfk " +
            "LEFT JOIN chapter ON comic.comicID = chapter.chapter_comicID " +
            "INNER JOIN history_chapter ON history_chapter.chapter_id = chapter.chapterID " +
            "LEFT JOIN history_comic ON history_comic.comicIDfk = comic.comicID " +
            "LEFT JOIN history ON history.historyID = history_comic.historyIDfk " +
            "LEFT JOIN account ON account.id = history.accountID " +
            "WHERE account.username = :username", nativeQuery = true)
    List<transteam> findTransDetailsByUsername(@Param("username") String username);
    @Query(value = "SELECT * FROM comic.transteam where transname like %:transname% ;",nativeQuery = true)
    List<transteam> findTrans(@Param("transname") String transname);
}
