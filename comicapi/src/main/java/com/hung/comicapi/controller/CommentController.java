package com.hung.comicapi.controller;

import com.hung.comicapi.model.Account;
import com.hung.comicapi.model.Comment;
import com.hung.comicapi.model.DataJSON;
import com.hung.comicapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("/chapter/root")
    public ResponseEntity<DataJSON> getAllRootCommentInTheChapter(@RequestBody HashMap<String,String> hashMap){
        try{
            List<Comment> commentRoots = commentService.getAllRootCommentInTheChapter(hashMap.get("chapterID"));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get all root comments successful",commentRoots)
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null)
            );
        }
    }
    @PostMapping("/chapter/filter/react")
    public ResponseEntity<DataJSON> getAllRootCommentInTheChapterByReact(@RequestBody HashMap<String,String> hashMap){
        try{
            List<Comment> commentRoots = commentService.getAllRootCommentInTheChapterByReact(hashMap.get("chapterID"));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get all root comments successful",commentRoots)
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null)
            );
        }
    }
    @PostMapping("/chapter/filter/updatedtime")
    public ResponseEntity<DataJSON> getAllRootCommentInTheChapterByTime(@RequestBody HashMap<String,String> hashMap){
        try{
            List<Comment> commentRoots = commentService.getAllRootCommentInTheChapterByTime(hashMap.get("chapterID"));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get all root comments successful",commentRoots)
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null)
            );
        }
    }
    @PostMapping("/comic/filter/react")
    public ResponseEntity<DataJSON> getAllRootCommentInTheComicByReact(@RequestBody HashMap<String,String> hashMap){
        try{
            List<Comment> commentRoots = commentService.getAllRootCommentInTheComicByReact(hashMap.get("comicID"));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get all root comments successful",commentRoots)
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null)
            );
        }
    }
    @PostMapping("/comic/filter/updatedtime")
    public ResponseEntity<DataJSON> getAllRootCommentInTheComicByTime(@RequestBody HashMap<String,String> hashMap){
        try{
            List<Comment> commentRoots = commentService.getAllRootCommentInTheComicByTime(hashMap.get("comicID"));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get all root comments successful",commentRoots)
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null)
            );
        }
    }
    @PostMapping("/account/root/chapter")
    public ResponseEntity<DataJSON> getAccountRootCommentChapter(@RequestBody HashMap<String,String> hashMap){
        try{
            List<Account> commentRoots = commentService.getAllAccountRootCommentInTheChapter(hashMap.get("chapterID"));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get all account root comments successful",commentRoots)
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null)
            );
        }
    }
    @PostMapping("/chapter/account/filter/react")
    public ResponseEntity<DataJSON> getAccountRootCommentChapterByReact(@RequestBody HashMap<String,String> hashMap){
        try{
            List<Account> commentRoots = commentService.getAllAccountRootCommentInTheChapterReact(hashMap.get("chapterID"));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get all account root comments successful",commentRoots)
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null)
            );
        }
    }
    @PostMapping("/chapter/account/filter/updatedtime")
    public ResponseEntity<DataJSON> getAccountRootCommentChapterByTime(@RequestBody HashMap<String,String> hashMap){
        try{
            List<Account> commentRoots = commentService.getAllAccountRootCommentInTheChapterTime(hashMap.get("chapterID"));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get all account root comments successful",commentRoots)
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null)
            );
        }
    }
    @PostMapping("/comic/account/filter/react")
    public ResponseEntity<DataJSON> getAccountRootCommentComicByReact(@RequestBody HashMap<String,String> hashMap){
        try{
            List<Account> commentRoots = commentService.getAllAccountRootCommentInTheComicReact(hashMap.get("comicID"));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get all account root comments successful",commentRoots)
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null)
            );
        }
    }
    @PostMapping("/comic/account/filter/updatedtime")
    public ResponseEntity<DataJSON> getAccountRootCommentComicByTime(@RequestBody HashMap<String,String> hashMap){
        try{
            List<Account> commentRoots = commentService.getAllAccountRootCommentInTheComicTime(hashMap.get("comicID"));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get all account root comments successful",commentRoots)
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null)
            );
        }
    }
    @PostMapping("/account/root/comic")
    public ResponseEntity<DataJSON> getAccountRootCommentComic(@RequestBody HashMap<String,String> hashMap){
        try{
            List<Account> commentRoots = commentService.getAllAccountRootCommentInTheComic(hashMap.get("comicID"));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get all account root comments successful",commentRoots)
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null)
            );
        }
    }
    @PostMapping("/account/child")
    public ResponseEntity<DataJSON> getAccountChildComment(@RequestBody HashMap<String,String> hashMap){
        try{
            List<Account> commentRoots = commentService.getAccountChildComment(hashMap.get("parent_commentID"));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get all account child comments successful",commentRoots)
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null)
            );
        }
    }
//    @PostMapping("/child/count")
//    public ResponseEntity<DataJSON> getChildCount(@RequestBody HashMap<String,String> hashMap){
//        try{
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new DataJSON(true,"Get child count comments successful",commentService.getChildCountComment(hashMap.get("parent_commentID")))
//            );
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
//                    new DataJSON(false,e.getMessage(),null)
//            );
//        }
//    }
    @PostMapping("/comic/root")
    public ResponseEntity<DataJSON> getAllRootCommentsInTheComic(@RequestBody HashMap<String,String> hashMap){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get all root comments in the comic successful",commentService.getAllRootCommentInTheComic(hashMap.get("comicID")))
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null)
            );
        }
    }
    @PostMapping("/child")
    public ResponseEntity<DataJSON> getAllChildCommentByCommentChapter(@RequestBody HashMap<String,String> hashMap){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get all child comments successful",commentService.getAllChildComment(hashMap.get("parent_commentID")))
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null)
            );
        }
    }
    @PostMapping("/write/root")
    public ResponseEntity<DataJSON> writeComment(@RequestBody HashMap<String,String> hashMap){
        try{
            if(commentService.writeComment(hashMap.get("accountID"),hashMap.get("chapterID"),hashMap.get("content"),hashMap.get("comicID"))) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new DataJSON(true, "write comment successful", null)
                );
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null)
            );
        }
        return null;
    }
    @PostMapping("/reply")
    public ResponseEntity<DataJSON> replyComment(@RequestBody HashMap<String,String> hashMap){
        try{
            if(commentService.replyComment(hashMap.get("accountID"),hashMap.get("chapterID"),hashMap.get("content"),hashMap.get("parent_commentID"), hashMap.get("comicID"))) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new DataJSON(true, "write comment successful", null)
                );
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null)
            );
        }
        return null;
    }
    @PostMapping("/react/increase")
    public ResponseEntity<DataJSON> updateLODIncrease(@RequestBody HashMap<String,Object> hashMap){
        try{
            if(commentService.updateLODIncrease((String) hashMap.get("commentID"), (Boolean) hashMap.get("lodType"), (String) hashMap.get("accountID"))) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new DataJSON(true, "update react comment successful", null)
                );
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null)
            );
        }
        return null;
    }
    @PostMapping("/react/decrease")
    public ResponseEntity<DataJSON> updateLODDecrease(@RequestBody HashMap<String,Object> hashMap){
        try{
            if(commentService.updateLODDecrease((String) hashMap.get("commentID"), (Boolean) hashMap.get("lodType"), (String) hashMap.get("accountID"))) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new DataJSON(true, "update react comment successful", null)
                );
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new DataJSON(false, "No record react comment for del", null)
                );
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null)
            );
        }
    }
    @PostMapping("react/check/account")
    public ResponseEntity<DataJSON> checkAccountLOD(@RequestBody HashMap<String,Object> hashMap){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get List account react successful!",commentService.checkAccountLOD(hashMap))
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null));
        }
    }
    @PostMapping("react/check/lodtype")
    public ResponseEntity<DataJSON> checkLODTypeLOD(@RequestBody HashMap<String,Object> hashMap){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get List lodType react successful!",commentService.checkLODTypeLOD(hashMap))
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null));
        }
    }
    @PostMapping("react/check/commentlodid")
    public ResponseEntity<DataJSON> checkCommetIDLOD(@RequestBody HashMap<String,Object> hashMap){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get List commentID react successful!",commentService.checkCommentIDLOD(hashMap))
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null));
        }
    }
    @PostMapping("react/check/commentid")
    public ResponseEntity<DataJSON> checkCommentIDLOD(@RequestBody HashMap<String,Object> hashMap){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get List commentID react successful!",commentService.checkCommentWriteIDLOD(hashMap))
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null));
        }
    }

    @PostMapping("react/check/comic/commentlodid")
    public ResponseEntity<DataJSON> checkCommentIDLODComic(@RequestBody HashMap<String,Object> hashMap){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get List commentID react successful!",commentService.checkCommentIDLODComic(hashMap))
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null));
        }
    }
    @PostMapping("react/check/comic/accountid")
    public ResponseEntity<DataJSON> checkAccountIDLODComic(@RequestBody HashMap<String,Object> hashMap){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get List commentID react successful!",commentService.checkAccountIDLODComic(hashMap))
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null));
        }
    }
    @PostMapping("react/check/comic/lodtpye")
    public ResponseEntity<DataJSON> checkLODTypeLODComic(@RequestBody HashMap<String,Object> hashMap){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get List commentID react successful!",commentService.checkLODTypeLODComic(hashMap))
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null));
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<DataJSON> deleteComment(@RequestBody HashMap<String, String> requestData){
        try{
            String commentID = requestData.get("commentID");
            List<Boolean> DelStatus = commentService.deleteComment(commentID);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Remove comment successful!",DelStatus)
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)    .body(
                    new DataJSON(false,e.getMessage(),"")
            );
        }
    }
    @PostMapping("/id")
    public ResponseEntity<DataJSON> getCommentByCommentID(@RequestBody HashMap<String,String> hashMap){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get comment successful!",commentService.getCommentByCommentID(hashMap.get("commentID")))
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null));
        }
    }
    @PostMapping("/account/id")
    public ResponseEntity<DataJSON> getAccountByCommentID(@RequestBody HashMap<String,String> hashMap){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get account successful!",commentService.getAccountByCommentID(hashMap.get("commentID")))
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null));
        }
    }
    @PostMapping("/child/count/comment")
    public ResponseEntity<DataJSON> getAllChildCommentCount(@RequestBody HashMap<String,String> hashMap){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get child count comments successful",commentService.getAllChildCommentCount(hashMap.get("chapterID")))
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null)
            );
        }
    }
    @PostMapping("/child/count/commentid")
    public ResponseEntity<DataJSON> getAllChildCommentID(@RequestBody HashMap<String,String> hashMap){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get child count comments successful",commentService.getAllChildCommentID(hashMap.get("chapterID")))
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null)
            );
        }
    }
    @PostMapping("/comic/child/count/comment")
    public ResponseEntity<DataJSON> getAllChildCommentCountComic(@RequestBody HashMap<String,String> hashMap){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get child count comments successful",commentService.getAllChildCommentCountComic(hashMap.get("comicID")))
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null)
            );
        }
    }
    @PostMapping("/comic/child/count/commentid")
    public ResponseEntity<DataJSON> getAllChildCommentIDComic(@RequestBody HashMap<String,String> hashMap){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get child count comments successful",commentService.getAllChildCommentIDComic(hashMap.get("comicID")))
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null)
            );
        }
    }
    @PostMapping("/comic/chapter")
    public ResponseEntity<DataJSON> getChapterCommentForComic(@RequestBody HashMap<String,String> hashMap){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get child count comments successful",commentService.getChapterCommentForComic(hashMap.get("comicID")))
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null)
            );
        }
    }
    @PostMapping("/comic/chapter/react")
    public ResponseEntity<DataJSON> getChapterCommentForComicReact(@RequestBody HashMap<String,String> hashMap){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get child count comments successful",commentService.getChapterCommentForComicReact(hashMap.get("comicID")))
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),null)
            );
        }
    }
}
