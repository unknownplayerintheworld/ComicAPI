package com.hung.comicapi.repository;

import com.hung.comicapi.model.Comic;
import com.hung.comicapi.model.favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface ComicRepository extends JpaRepository<Comic,Integer> {
    @Query(value = "SELECT * FROM comic WHERE comicID = :comicID ",nativeQuery = true)
    List<Comic> findByComicID(@Param("comicID") int comicID);
    // top tháng
    @Query(value = "SELECT * from comic order by views_in_month desc limit 7",nativeQuery = true)
    List<Comic> findOutstandingComic();
    // đề xuất,theo thể loại người dùng hay đọc
    @Query(value = "SELECT " +
            "c2.comicID, " +
            "c2.comicName, " +
            "c2.avatarLink, " +
            "c2.views, " +
            "c2.views_in_month, " +
            "c2.views_in_week, " +
            "c2.created_at, " +
            "c2.updated_at, " +
            "c2.description " +
            "FROM " +
            "comic.history_comic hc " +
            "LEFT JOIN " +
            "history h ON hc.historyIDfk = h.historyID " +
            "LEFT JOIN " +
            "comic c2 ON hc.comicIDfk = c2.comicID " +
            "LEFT JOIN " +
            "comic_genre cg ON c2.comicID = cg.comicIDfk " +
            "LEFT JOIN " +
            "genre g ON cg.genreIDfk = g.genreID " +
            "LEFT JOIN " +
            "account a ON h.accountID = a.id " +
            "WHERE " +
            "a.username = :username " +
            "AND " +
            "g.genrename = (" +
            "SELECT " +
            "g.genrename " +
            "FROM " +
            "comic.history_comic hc " +
            "LEFT JOIN " +
            "history h ON hc.historyIDfk = h.historyID " +
            "LEFT JOIN " +
            "comic c ON hc.comicIDfk = c.comicID " +
            "LEFT JOIN " +
            "comic_genre cg ON c.comicID = cg.comicIDfk " +
            "LEFT JOIN " +
            "genre g ON cg.genreIDfk = g.genreID " +
            "LEFT JOIN " +
            "account a ON h.accountID = a.id " +
            "WHERE " +
            "a.username = :username " +
            "GROUP BY " +
            "genreID " +
            "ORDER BY " +
            "COUNT(*) DESC " +
            "LIMIT 1" +
            ") " +
            "GROUP BY " +
            "c2.comicID, " +
            "c2.comicName, " +
            "g.genrename " +
            "ORDER BY " +
            "c2.views_in_month DESC " +
            "LIMIT " +
            "5", nativeQuery = true)
    List<Comic> findProposalComic(@Param("username") String username);
    // top 5 do khá ít truyện
    // sau khi ấn vào views all thì giống hệt,copy xuống là xong,làm sau,bỏ limit đi là đc
    // top all
    @Query(value = "select * from Comic order by views desc limit 5;",nativeQuery = true)
    List<Comic> findRankingComic();

    @Query(value = "SELECT comic.*, genre.description AS genre_description " +
            "FROM Comic " +
            "LEFT JOIN comic_genre ON comic.comicID = comic_genre.comicIDfk " +
            "LEFT JOIN genre ON genre.genreID = comic_genre.genreIDfk " +
            "WHERE genre.genrename = 'shounen' " +
            "ORDER BY views_in_month DESC " +
            "LIMIT 5", nativeQuery = true)

    List<Comic> findShounenComic();
    // sau khi ấn vào views all thì giống hệt,copy xuống là xong,làm sau,bỏ limit đi là đc
    // truyện nam
    @Query(value = "SELECT comic.*, genre.description AS genre_description " +
            "FROM comic " +
            "LEFT JOIN comic_genre ON comic.comicID = comic_genre.comicIDfk " +
            "LEFT JOIN genre ON genre.genreID = comic_genre.genreIDfk " +
            "WHERE genrename = :genrename",nativeQuery = true)

    List<Comic> getComicsByGenre(@Param("genrename") String genrename);

    @Query(value = "SELECT * FROM comic.favourite " +
            "LEFT JOIN account ON favourite.accountID = account.id " +
            "LEFT JOIN favourite_comic ON favourite_comic.favouriteIDfk = favourite.favouriteID " +
            "LEFT JOIN comic ON comic.comicID = favourite_comic.comicIDfk " +
            "WHERE account.username = :username", nativeQuery = true)
    List<Comic> getFavouriteComics(@Param("username") String username);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO favourite_comic(comicIDfk, favouriteIDfk) VALUES (:comicID, :favouriteID)",nativeQuery = true)
    Integer AddtoFavourite(@Param("comicID") String comicID, @Param("favouriteID") String favouriteID);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM favourite_comic WHERE comicIDfk = :comicId AND favouriteIDfk = :favouriteId",nativeQuery = true)
    Integer removeFromFavourite(@Param("comicId") String comicId, @Param("favouriteId") String favouriteId);

    @Query(value = "SELECT * FROM comic LEFT JOIN comic_trans ON comic.comicID = comic_trans.comicIDfk " +
            "LEFT JOIN transteam ON comic_trans.transIDfk = transteam.transteamID " +
            "WHERE transteam.transteamID = ?1", nativeQuery = true)
    List<Comic> findComicByTransID(String transteamID);
    @Query(value = "SELECT comic.comicID,comic.comicName,comic.avatarLink,comic.views,comic.views_in_month,comic.views_in_week,comic.created_at,comic.updated_at,comic.description FROM comic " +
            "LEFT JOIN comic_trans ON comic.comicID = comic_trans.comicIDfk " +
            "LEFT JOIN transteam ON transteam.transteamID = comic_trans.transIDfk " +
            "LEFT JOIN chapter ON comic.comicID = chapter.chapter_comicID " +
            "INNER JOIN history_chapter ON history_chapter.chapter_id = chapter.chapterID " +
            "LEFT JOIN history_comic ON history_comic.comicIDfk = comic.comicID " +
            "LEFT JOIN history ON history.historyID = history_comic.historyIDfk " +
            "LEFT JOIN account ON account.id = history.accountID " +
            "WHERE account.username = :username order by history_comic.historyreading desc;", nativeQuery = true)
    List<Comic> findComicDetailsByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM comic.comic where comicName like %:comicName% ;",nativeQuery = true)
    List<Comic> findComics(@Param("comicName") String comicName);

    @Query(value = "SELECT comic.comicID, comic.comicName, comic.avatarLink, comic.views, comic.views_in_month, comic.views_in_week, comic.created_at, comic.updated_at, comic.description " +
            "FROM comic " +
            "LEFT JOIN chapter ON comic.comicID = chapter.chapter_comicID " +
            "LEFT JOIN history_chapter ON chapter.chapterID = history_chapter.chapter_id " +
            "LEFT JOIN history_comic ON history_chapter.history_comic_id = history_comic.hcID " +
            "LEFT JOIN history ON history_comic.historyIDfk = history.historyID " +
            "LEFT JOIN account ON history.accountID = account.id " +
            "WHERE comic.comicID = :comicID AND account.username = :username", nativeQuery = true)
    List<Comic> findComicHistoryByUsernameAndComicID(@Param("comicID") String comicID, @Param("username") String username);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Comic " +
            "SET views = views + 1, " +
            "views_in_month = views_in_month + 1, " +
            "views_in_week = views_in_week + 1 " +
            "WHERE comicID = :comicID", nativeQuery = true)
    Integer updateViewsCounts(@Param("comicID") String comicID);
}
