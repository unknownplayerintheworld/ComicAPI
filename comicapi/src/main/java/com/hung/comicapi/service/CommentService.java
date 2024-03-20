package com.hung.comicapi.service;

import com.hung.comicapi.model.Account;
import com.hung.comicapi.model.Chapter;
import com.hung.comicapi.model.Comment;
import com.hung.comicapi.repository.AccountRepository;
import com.hung.comicapi.repository.ChapterRepository;
import com.hung.comicapi.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ChapterRepository chapterRepository;
    public List<Comment> getAllRootCommentInTheChapter(String chapterID){
        try{
            return commentRepository.getAllRootCommentInTheChapter(chapterID);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Comment> getAllRootCommentInTheChapterByReact(String chapterID){
        try{
            return commentRepository.getAllRootCommentByLODReact(chapterID);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Comment> getAllRootCommentInTheChapterByTime(String chapterID){
        try{
            return commentRepository.getAllRootCommentByUpdatedTime(chapterID);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Comment> getAllRootCommentInTheComicByReact(String comicID){
        try{
            return commentRepository.getAllRootCommentByLODReactComic(comicID);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Comment> getAllRootCommentInTheComicByTime(String comicID){
        try{
            return commentRepository.getAllRootCommentByUpdatedTimeComic(comicID);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Account> getAllAccountRootCommentInTheChapter(String chapterID){
        try{
            return accountRepository.findAccountRootCommentChapter(chapterID);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Account> getAllAccountRootCommentInTheChapterReact(String chapterID){
        try{
            return accountRepository.findAccountRootCommentChapterByLODReact(chapterID);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Account> getAllAccountRootCommentInTheChapterTime(String chapterID){
        try{
            return accountRepository.findAccountRootCommentChapterByUpdatedTime(chapterID);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Account> getAllAccountRootCommentInTheComicReact(String chapterID){
        try{
            return accountRepository.findAccountRootCommentComicByLODReact(chapterID);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Account> getAllAccountRootCommentInTheComicTime(String chapterID){
        try{
            return accountRepository.findAccountRootCommentComicByUpdatedTime(chapterID);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Account> getAllAccountRootCommentInTheComic(String comicID){
        try{
            return accountRepository.findAccountRootCommentComic(comicID);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Account> getAccountChildComment(String parent_commentID){
        try{
            return accountRepository.getAccountChildComment(parent_commentID);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Integer> getChildCountComment(String parent_commentID){
        try{
            List<Integer> integers = new ArrayList<>();
            integers.add(commentRepository.getChildCommentCount(parent_commentID));
            return integers;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Comment> getAllRootCommentInTheComic(String comicID){
        try{
            return commentRepository.getAllRootCommentsForComic(comicID);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Comment> getAllChildComment(String parent_commentID){
        try{
            return commentRepository.getChildComment(parent_commentID);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public Boolean writeComment(String accountID,String chapterID,String content,String comicID){
        try{
            return commentRepository.writeRootComment(accountID, chapterID, content,comicID) > 0;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public Boolean replyComment(String accountID,String chapterID,String content,String parent,String comicID){
        try{
            return commentRepository.replyComment(accountID, chapterID, content,parent,comicID) > 0;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public Boolean updateLODIncrease(String commentID, Boolean lodType, String accountID) {
        try {
            int updatedRows = 0;
            if (lodType) {
                updatedRows += commentRepository.updateLODIncrease(commentID, lodType, accountID);
                if(updatedRows>0) {
                    updatedRows += commentRepository.updateLikeIncrease(commentID);
                }
            } else {
                updatedRows += commentRepository.updateLODIncrease(commentID, lodType, accountID);
                if(updatedRows>0){
                    updatedRows += commentRepository.updateDislikeIncrease(commentID);
                }
            }
            return updatedRows > 1; // Trả về true nếu có ít nhất một hàng được cập nhật
        } catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public Boolean updateLODDecrease(String commentID, Boolean lodType, String accountID) {
        try {
            int updatedRows = 0;
            if (lodType) {
                updatedRows += commentRepository.updateLODDecrease(commentID, lodType, accountID);
                if(updatedRows>0){
                    updatedRows += commentRepository.updateLikeDecrease(commentID);
                }
            } else {
                updatedRows += commentRepository.updateLODDecrease(commentID, lodType, accountID);
                if(updatedRows>0){
                    updatedRows += commentRepository.updateDislikeDecrease(commentID);
                }
            }
            return updatedRows > 1; // Trả về true nếu có ít nhất một hàng được cập nhật
        } catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Integer> checkAccountLOD(HashMap<String,Object> hashMap){
        try{
            return commentRepository.checkAccountLOD((String) hashMap.get("chapterID"));
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Boolean> checkLODTypeLOD(HashMap<String,Object> hashMap){
        try{
            return commentRepository.checklodTypeLOD((String) hashMap.get("chapterID"));
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Integer> checkCommentIDLOD(HashMap<String,Object> hashMap){
        try{
            return commentRepository.checkCommentIDLOD((String) hashMap.get("chapterID"));
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Integer> checkCommentWriteIDLOD(HashMap<String,Object> hashMap){
        try{
            return commentRepository.checkCommentWriteIDLOD((String) hashMap.get("chapterID"));
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    //
    public List<Integer> checkCommentIDLODComic(HashMap<String,Object> hashMap){
        try{
            return commentRepository.checkCommentIDLODChildAndComic((String) hashMap.get("comicID"));
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Integer> checkAccountIDLODComic(HashMap<String,Object> hashMap){
        try{
            return commentRepository.checkAccountIDLODChildAndComic((String) hashMap.get("comicID"));
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Boolean> checkLODTypeLODComic(HashMap<String,Object> hashMap){
        try{
            return commentRepository.checklodTypeLODForChildAndComic((String) hashMap.get("comicID"));
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Boolean> deleteComment(String commentID){
        List<Boolean> status = new ArrayList<>();
        try{
            int rows = commentRepository.deleteComment(commentID);
            if(rows > 0){
                status.add(true);
            }
            else{
                status.add(false);
            }
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return status;
    }
    public List<Comment> getCommentByCommentID(String commentID){
        try{
            return commentRepository.getCommentByCommentID(commentID);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Account> getAccountByCommentID(String commentID){
        try{
            return accountRepository.getAccountByCommentID(commentID);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Integer> getAllChildCommentCount(String chapterID){
        try{
            return commentRepository.getAllChildCommentCount(chapterID);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Integer> getAllChildCommentID(String chapterID){
        try{
            return commentRepository.getAllChildCommentID(chapterID);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Chapter> getChapterCommentForComic(String comicID){
        try{
            return chapterRepository.getChapterCommentForComic(comicID);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Chapter> getChapterCommentForComicReact(String comicID){
        try{
            return chapterRepository.getChapterCommentForComicReact(comicID);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Integer> getAllChildCommentCountComic(String comicID){
        try{
            return commentRepository.getAllChildCommentCountComic(comicID);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Integer> getAllChildCommentIDComic(String comicID){
        try{
            return commentRepository.getAllChildCommentIDComic(comicID);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
