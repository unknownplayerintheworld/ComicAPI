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
            "WHERE account.username = :username order by history_comic.historyreading desc;", nativeQuery = true)
    List<Chapter> findChapterDetailsByUsername(@Param("username") String username);


    @Query(value = "SELECT * FROM comic.chapter WHERE chapter_comicID = ?1 AND chapter_number_pos = ?2", nativeQuery = true)
    List<Chapter> getChapterID(String comicId, String numberPos);

    @Query(value = "SELECT chapter.* FROM comment " +
            "            inner JOIN account ON comment.accountID = account.id " +
            "            left JOIN chapter ON comment.chapterID = chapter.chapterID where comicID = :comicID and " +
            "            parent_commentID is null order by comment.updated_at desc;",nativeQuery = true)
    List<Chapter> getChapterCommentForComic(@Param("comicID") String comicID);

    @Query(value = "SELECT chapter.* FROM comment " +
            "            inner JOIN account ON comment.accountID = account.id " +
            "            left JOIN chapter ON comment.chapterID = chapter.chapterID where comicID = :comicID and " +
            "            parent_commentID is null order by (comment.likeCount+comment.dislikeCount) desc;",nativeQuery = true)
    List<Chapter> getChapterCommentForComicReact(@Param("comicID") String comicID);

    @Query(value = "SELECT chapter.* \n" +
            "             FROM comic \n" +
            "             LEFT JOIN chapter ON comic.comicID = chapter.chapter_comicID \n" +
            "            LEFT JOIN history_chapter ON chapter.chapterID = history_chapter.chapter_id \n" +
            "\t\t\tLEFT JOIN history_comic ON history_chapter.history_comic_id = history_comic.hcID \n" +
            "             LEFT JOIN history ON history_comic.historyIDfk = history.historyID \n" +
            "            LEFT JOIN account ON history.accountID = account.id \n" +
            "            WHERE comic.comicID = :comicID AND account.id= :accountID",nativeQuery = true)
    List<Chapter> getChapterHistoryByComic(@Param("comicID") String comicID,@Param("accountID") String accountID);
}
