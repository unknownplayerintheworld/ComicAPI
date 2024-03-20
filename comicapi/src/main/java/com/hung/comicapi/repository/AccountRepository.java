package com.hung.comicapi.repository;

import com.hung.comicapi.model.Account;
import com.hung.comicapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    List<Account> findByUsername(String username);

    @Query(value = "select * from account where id = :accountid",nativeQuery = true)
    List<Account> findAccountByAccountID(@Param("accountid") String accountid);

    Optional<Account> findOneByUsername(String username);
//    Account updateAccount(String username);

    @Query(value = "SELECT comment.*, account.*, chapter.* FROM comment " +
            "INNER JOIN account ON comment.accountID = account.id " +
            "INNER JOIN chapter ON comment.chapterID = chapter.chapterID where comment.chapterID = :chapterID and parent_commentID is null", nativeQuery = true)
    List<Account> findAccountRootCommentChapter(@Param("chapterID") String chapterID);

    @Query(value = "SELECT account.* FROM comment " +
            "            inner JOIN account ON comment.accountID = account.id " +
            "            left JOIN chapter ON comment.chapterID = chapter.chapterID where comicID = :comicID and " +
            "            parent_commentID is null order by comment.updated_at desc;", nativeQuery = true)
    List<Account> findAccountRootCommentComic(@Param("comicID") String comicID);

    @Query(value ="select * from comic.comment inner join account on comment.accountID = account.id where parent_commentID = :parent_commentID ;", nativeQuery = true)
    List<Account> getAccountChildComment(@Param("parent_commentID") String parent_commentID);

    @Query(value = "SELECT comment.*, account.*, chapter.* FROM comment " +
            "INNER JOIN account ON comment.accountID = account.id " +
            "INNER JOIN chapter ON comment.chapterID = chapter.chapterID where comment.chapterID = :chapterID and parent_commentID is null order by (comment.likeCount+comment.dislikeCount) desc ;", nativeQuery = true)
    List<Account> findAccountRootCommentChapterByLODReact(@Param("chapterID") String chapterID);

    @Query(value = "SELECT comment.*, account.*, chapter.* FROM comment " +
            "INNER JOIN account ON comment.accountID = account.id " +
            "INNER JOIN chapter ON comment.chapterID = chapter.chapterID where comment.chapterID = :chapterID and parent_commentID is null order by comment.updated_at desc ;", nativeQuery = true)
    List<Account> findAccountRootCommentChapterByUpdatedTime(@Param("chapterID") String chapterID);

    @Query(value = "SELECT account.* FROM comment " +
            "            inner JOIN account ON comment.accountID = account.id " +
            "            left JOIN chapter ON comment.chapterID = chapter.chapterID where comicID = :comicID and " +
            "            parent_commentID is null order by (dislikeCount+likeCount) desc;", nativeQuery = true)
    List<Account> findAccountRootCommentComicByLODReact(@Param("comicID") String comicID);

    @Query(value = "SELECT account.* FROM comment " +
            "            inner JOIN account ON comment.accountID = account.id " +
            "            left JOIN chapter ON comment.chapterID = chapter.chapterID where comicID = :comicID and " +
            "            parent_commentID is null order by comment.updated_at desc;", nativeQuery = true)
    List<Account> findAccountRootCommentComicByUpdatedTime(@Param("comicID") String comicID);

    @Query(value = "select * from comic.comment inner join account on comment.accountID = account.id where commentID = :commentID ;",nativeQuery = true)
    List<Account> getAccountByCommentID(@Param("commentID") String commentID);
}
