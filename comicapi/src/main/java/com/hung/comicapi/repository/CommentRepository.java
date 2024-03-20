package com.hung.comicapi.repository;

import com.hung.comicapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    @Query(value = "SELECT * FROM comic.comment where chapterID = :chapterID and parent_commentID is null;",nativeQuery = true)
    List<Comment> getAllRootCommentInTheChapter(@Param("chapterID") String chapterID);
    @Query(value = "select count(*) as childcount from comic.comment where parent_commentID = :parent_commentID ;",nativeQuery = true)
    Integer getChildCommentCount(@Param("parent_commentID")String parent_commentID);
//
    @Query(value = "SELECT comment.* FROM comment " +
            "inner JOIN account ON comment.accountID = account.id " +
            "left JOIN chapter ON comment.chapterID = chapter.chapterID where comicID = :comicID and " +
            "parent_commentID is null order by comment.updated_at desc;", nativeQuery = true)
    List<Comment> getAllRootCommentsForComic(@Param("comicID") String comicID);

//
    @Query(value = "select * from comic.comment where parent_commentID = :parent_commentID ;",nativeQuery = true)
    List<Comment> getChildComment(@Param("parent_commentID") String parent_commentID);
//
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO comic.comment (parent_commentID, content, likeCount, dislikeCount, accountID, chapterID,comicID) " +
            "VALUES (NULL, :content, 0, 0, :accountID, :chapterID, :comicID)", nativeQuery = true)
    Integer writeRootComment(@Param("accountID") String accountID,@Param("chapterID") String chapterID,@Param("content") String content,@Param("comicID") String comicID);
//
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO comic.comment (parent_commentID, content, likeCount, dislikeCount, accountID, chapterID,comicID) " +
            "VALUES (:parent_commentID, :content, 0, 0, :accountID, :chapterID , :comicID)", nativeQuery = true)
    Integer replyComment(@Param("accountID") String accountID,@Param("chapterID") String chapterID,@Param("content") String content,@Param("parent_commentID") String parent_commentID,@Param("comicID") String comicID);
//

    @Transactional
    @Modifying
    @Query(value = "update comment set likeCount = likeCount+1 where commentID = :commentID",nativeQuery = true)
    Integer updateLikeIncrease(String commentID);

    @Transactional
    @Modifying
    @Query(value = "update comment set dislikeCount = dislikeCount+1 where commentID = :commentID",nativeQuery = true)
    Integer updateDislikeIncrease(String commentID);

    @Transactional
    @Modifying
    @Query(value = "update comment set likeCount = likeCount-1 where commentID = :commentID",nativeQuery = true)
    Integer updateLikeDecrease(String commentID);

    @Transactional
    @Modifying
    @Query(value = "update comment set dislikeCount = dislikeCount-1 where commentID = :commentID",nativeQuery = true)
    Integer updateDislikeDecrease(String commentID);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO comment_lod (lodType, accountID, commentID) VALUES " +
            "(:lodType , :accountID , :commentID ); ",nativeQuery = true)
    Integer updateLODIncrease(@Param("commentID") String commentID,@Param("lodType") Boolean lodType,@Param("accountID") String accountID);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM comment_lod WHERE commentID = :commentID and accountID = :accountID and lodType = :lodType ;",nativeQuery = true)
    Integer updateLODDecrease(@Param("commentID") String commentID,@Param("lodType") Boolean lodType,@Param("accountID") String accountID);

    @Query(value = "SELECT comment_lod.accountID FROM comic.comment_lod inner join comic.comment on comment_lod.commentID = comment.commentID where chapterID = :chapterID order by comment_lod.lodID;",nativeQuery = true)
    List<Integer> checkAccountLOD(@Param("chapterID") String chapterID);

    @Query(value = "SELECT lodType FROM comic.comment_lod inner join comic.comment on comment_lod.commentID = comment.commentID where chapterID = :chapterID order by comment_lod.lodID;",nativeQuery = true)
    List<Boolean> checklodTypeLOD(@Param("chapterID") String chapterID);
    @Query(value = "SELECT comment_lod.commentID FROM comic.comment_lod inner join comic.comment on comment_lod.commentID = comment.commentID where chapterID = :chapterID order by comment_lod.lodID;",nativeQuery = true)
    List<Integer> checkCommentIDLOD(@Param("chapterID") String chapterID);

    @Query(value = "SELECT comment.commentID FROM comic.comment_lod inner join comic.comment on comment_lod.commentID = comment.commentID where chapterID = :chapterID order by comment_lod.lodID;",nativeQuery = true)
    List<Integer> checkCommentWriteIDLOD(@Param("chapterID") String chapterID);

    // for child comment and root comic comment,not for root chapter comment
    @Query(value = "SELECT lodType FROM comic.comment_lod inner join comic.comment on comment_lod.commentID = comment.commentID where comicID = :comicID order by comment_lod.lodID;",nativeQuery = true)
    List<Boolean> checklodTypeLODForChildAndComic(@Param("comicID") String comicID);
    @Query(value = "SELECT comment_lod.commentID FROM comic.comment_lod inner join comic.comment on comment_lod.commentID = comment.commentID where comicID = :comicID order by comment_lod.lodID;",nativeQuery = true)
    List<Integer> checkCommentIDLODChildAndComic(@Param("comicID") String comicID);

    @Query(value = "SELECT comment_lod.accountID FROM comic.comment_lod inner join comic.comment on comment_lod.commentID = comment.commentID where comicID = :comicID order by comment_lod.lodID;",nativeQuery = true)
    List<Integer> checkAccountIDLODChildAndComic(@Param("comicID") String comicID);


    @Transactional
    @Modifying
    @Query(value = "UPDATE comment " +
            "SET content = :commentID " +
            "WHERE commentID = :commentID;",nativeQuery = true)
    List<Boolean> editComment(@Param("commentID") String commentID);

    @Query(value = "select * from comment where chapterID = :chapterID and parent_commentID is null order by (likeCount+dislikeCount) desc;",nativeQuery = true)
    List<Comment> getAllRootCommentByLODReact(@Param("chapterID") String chapterID);

    @Query(value = "select * from comment where chapterID = :chapterID and parent_commentID is null order by updated_at desc;",nativeQuery = true)
    List<Comment> getAllRootCommentByUpdatedTime(@Param("chapterID") String chapterID);

    @Query(value = "SELECT comment.* FROM comment " +
            "            inner JOIN account ON comment.accountID = account.id " +
            "            left JOIN chapter ON comment.chapterID = chapter.chapterID where comicID = :comicID and " +
            "            parent_commentID is null order by (dislikeCount+likeCount) desc;",nativeQuery = true)
    List<Comment> getAllRootCommentByLODReactComic(@Param("comicID") String comicID);

    @Query(value = "SELECT comment.* FROM comment " +
            "            inner JOIN account ON comment.accountID = account.id " +
            "            left JOIN chapter ON comment.chapterID = chapter.chapterID where comicID = :comicID and " +
            "            parent_commentID is null order by comment.updated_at desc;",nativeQuery = true)
    List<Comment> getAllRootCommentByUpdatedTimeComic(@Param("comicID") String comicID);

    @Transactional
    @Modifying
    @Query(value="DELETE FROM comic.comment WHERE comment.commentID = :commentID ;",nativeQuery = true)
    Integer deleteComment(@Param("commentID") String commentID);

    @Query(value = "select * from comic.comment where commentID = :commentID ;",nativeQuery = true)
    List<Comment> getCommentByCommentID(@Param("commentID") String commentID);

    @Query(value = "SELECT COUNT(c2.commentID) AS childCount " +
            "FROM comment c1 " +
            "LEFT JOIN comment c2 ON c1.commentID = c2.parent_commentID " +
            "WHERE c1.parent_commentID IS NULL " +
            "GROUP BY c1.commentID; ",nativeQuery = true)
    List<Integer> getAllChildCommentCount(@Param("chapterID") String chapterID);
    @Query(value = "SELECT c1.commentID " +
            "FROM comment c1 " +
            "LEFT JOIN comment c2 ON c1.commentID = c2.parent_commentID " +
            "WHERE c1.parent_commentID IS NULL " +
            "GROUP BY c1.commentID;",nativeQuery = true)
    List<Integer> getAllChildCommentID(@Param("chapterID") String chapterID);

    @Query(value = "SELECT COUNT(child_comments.child_commentID) AS child_comment_count " +
            "FROM (" +
            "    SELECT commentID " +
            "    FROM comment " +
            "    WHERE parent_commentID IS NULL AND comicID = :comicID " +
            ") AS root_comment " +
            "LEFT JOIN (" +
            "    SELECT parent_commentID, COUNT(*) AS child_commentID " +
            "    FROM comment " +
            "    WHERE parent_commentID IS NOT NULL AND comicID = :comicID " +
            "    GROUP BY parent_commentID " +
            ") AS child_comments ON root_comment.commentID = child_comments.parent_commentID " +
            "GROUP BY root_comment.commentID;",nativeQuery = true)
    List<Integer> getAllChildCommentCountComic(@Param("comicID") String comicID);
    @Query(value = "SELECT root_comment.commentID " +
            "            FROM ( " +
            "            SELECT commentID " +
            "            FROM comment " +
            "                WHERE parent_commentID IS NULL AND comicID = :comicID " +
            "            ) AS root_comment " +
            "            LEFT JOIN (" +
            "            SELECT parent_commentID, COUNT(*) AS child_commentID " +
            "            FROM comment " +
            "            WHERE parent_commentID IS NOT NULL AND comicID = :comicID " +
            "               GROUP BY parent_commentID " +
            "            ) AS child_comments ON root_comment.commentID = child_comments.parent_commentID " +
            "            GROUP BY root_comment.commentID;",nativeQuery = true)
    List<Integer> getAllChildCommentIDComic(@Param("comicID") String comicID);
}
