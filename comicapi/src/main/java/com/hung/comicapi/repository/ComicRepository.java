package com.hung.comicapi.repository;

import com.hung.comicapi.model.Comic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ComicRepository extends JpaRepository<Comic,Integer> {
    List<Comic> findByComicName(String comicName);
    // top tháng
    @Query(value = "SELECT * from comic order by views_in_month desc limit 2",nativeQuery = true)
    List<Comic> findOutstandingComic();
    // đề xuất,theo thể loại người dùng hay đọc
    @Query(value = "SELECT comic.comicID, comic.comicName, comic.avatarLink, comic.views, comic.views_in_month, comic.views_in_week, comic.created_at, comic.updated_at " +
            "FROM comic.history_comic " +
            "LEFT JOIN history ON history_comic.historyIDfk = history.historyID " +
            "LEFT JOIN comic ON history_comic.comicIDfk = comic.comicID " +
            "LEFT JOIN comic_genre ON comic.comicID = comic_genre.comicIDfk " +
            "LEFT JOIN genre ON comic_genre.genreIDfk = genre.genreID " +
            "LEFT JOIN account ON history.accountID = account.id " +
            "WHERE account.username = :username " +
            "AND genre.genrename = (" +
            " SELECT genre.genrename " +
            " FROM comic.history_comic " +
            " LEFT JOIN history ON history_comic.historyIDfk = history.historyID " +
            " LEFT JOIN comic ON history_comic.comicIDfk = comic.comicID " +
            " LEFT JOIN comic_genre ON comic.comicID = comic_genre.comicIDfk " +
            " LEFT JOIN genre ON comic_genre.genreIDfk = genre.genreID " +
            " LEFT JOIN account ON history.accountID = account.id " +
            " WHERE account.username = :username " +
            " GROUP BY genreID " +
            " ORDER BY COUNT(*) DESC " +
            " LIMIT 1" +
            ") " +
            "GROUP BY comic.comicID, comic.comicName, genre.genrename order by views_in_month desc limit 5;",nativeQuery = true)
    List<Comic> findProposalComic(@Param("username") String username);
    // top 5 do khá ít truyện
    // sau khi ấn vào views all thì giống hệt,copy xuống là xong,làm sau,bỏ limit đi là đc
    // top all
    @Query(value = "select * from Comic order by views desc limit 5;",nativeQuery = true)
    List<Comic> findRankingComic();

    @Query(value = "SELECT * FROM Comic " +
            "LEFT JOIN comic_genre ON comic.comicID = comic_genre.comicIDfk " +
            "LEFT JOIN genre ON genre.genreID = comic_genre.genreIDfk " +
            "WHERE genre.genrename = 'shounen' " +
            "ORDER BY views_in_month DESC " +
            "LIMIT 5", nativeQuery = true)
    List<Comic> findShounenComic();
    // sau khi ấn vào views all thì giống hệt,copy xuống là xong,làm sau,bỏ limit đi là đc
    // truyện nam
}
