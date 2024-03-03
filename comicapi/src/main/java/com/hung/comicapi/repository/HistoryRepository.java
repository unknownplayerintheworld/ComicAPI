package com.hung.comicapi.repository;

import com.hung.comicapi.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
@Repository
public interface HistoryRepository extends JpaRepository<History,Integer> {
    @Query(value = "SELECT * FROM comic.history_comic left join history on history_comic.historyIDfk = history.historyID",nativeQuery = true)
    List<History> findAllHistory();

    @Modifying
    @Transactional
    @Query(value="DELETE FROM history_comic WHERE comicIDfk = :comicId AND historyIDfk = :historyIDfk",nativeQuery = true)
    Integer removeFromHistory(@Param("comicId") String comicId, @Param("historyIDfk") String historyID);

    @Query(value = "SELECT * FROM account left join history on account.id = history.accountID where username = :username",nativeQuery = true)
    List<History> findHistoryID(@Param("username") String username);

    // nếu có lịch sử truyện ở bên comic repository thì mới thực hiện
    @Transactional
    @Modifying
    @Query(value = "UPDATE comic.history_chapter " +
            "SET chapter_id = :chapterId " +
            "WHERE chapter_id = :chapterIdOld",
            nativeQuery = true)
    Integer updateHistoryChapter(@Param("chapterId") String chapterId,@Param("chapterIdOld") String chapterId_old);

    @Transactional
    @Modifying
    @Query(value = "UPDATE comic.history_comic " +
            "SET historyreading = NOW() " +
            "WHERE historyIDfk = :historyIDfk and comicIDfk = :comicIDfk",
            nativeQuery = true)
    Integer updateHistoryComic(@Param("historyIDfk") String historyIDfk,@Param("comicIDfk") String comicIDfk);
    @Query(value = "SELECT chapter_id " +
            " FROM comic " +
            " LEFT JOIN chapter ON comic.comicID = chapter.chapter_comicID " +
            " LEFT JOIN history_chapter ON chapter.chapterID = history_chapter.chapter_id " +
            " LEFT JOIN history_comic ON history_chapter.history_comic_id = history_comic   .hcID " +
            " LEFT JOIN history ON history_comic.historyIDfk = history.historyID " +
            " LEFT JOIN account ON history.accountID = account.id " +
            " WHERE comic.comicID = :comicId AND account.username= :username ",
            nativeQuery = true)
    String getChapterID(@Param("comicId") String comicId, @Param("username") String username);
// 2 truy vấn insert ở dưới thực hiện cùng lúc với nhau nhá:>
    @Modifying
    @Transactional
    @Query(value="INSERT INTO history_comic (comicIDfk, historyreading, historyIDfk) VALUES (:comicId, NOW(), :historyIDfk);",nativeQuery = true)
    Integer addToHistoryComic(@Param("comicId") String comicId, @Param("historyIDfk") String historyID);

    @Modifying
    @Transactional
    @Query(value="INSERT INTO history_chapter (history_comic_id,chapter_id) " +
            "VALUES (LAST_INSERT_ID(),:chapterID);",nativeQuery = true)
    Integer addToHistoryChapter(@Param("chapterID") String chapterID);
}
