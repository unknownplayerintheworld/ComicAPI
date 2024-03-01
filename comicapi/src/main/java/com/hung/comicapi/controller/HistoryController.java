package com.hung.comicapi.controller;

import com.hung.comicapi.model.*;
import com.hung.comicapi.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/history")
public class HistoryController {
    @Autowired
    private HistoryService historyService;
    @GetMapping("/")
    public ResponseEntity<DataJSON> getAllHistory(){
        try{
            List<History> history = historyService.getHistory();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get all history successful",history)
            );
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new DataJSON(false,e.getMessage(),"")
            );
        }
    }
    @DeleteMapping("/remove")
    public ResponseEntity<DataJSON> removeFromHistory(@RequestBody HashMap<String, String> requestData){
        try{
            String comicID = requestData.get("comicID");
            String historyID = requestData.get("historyID");
            List<Boolean> removeFromFavStatus = historyService.removeFromHistory(comicID,historyID);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Remove fav comics successful",removeFromFavStatus)
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),"")
            );
        }
    }
    @PostMapping("/comic")
    public ResponseEntity<DataJSON> getComicHistory(@RequestBody HashMap<String, String> requestData){
        try{
            String username = requestData.get("username");
            List<Comic> history = historyService.getComicHistory(username);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get all history successful",history)
            );
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new DataJSON(false,e.getMessage(),"")
            );
        }
    }
    @PostMapping("/trans")
    public ResponseEntity<DataJSON> getTransHistory(@RequestBody HashMap<String, String> requestData){
        try{
            String username = requestData.get("username");
            List<transteam> history = historyService.getTransteamHistory(username);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get all history successful",history)
            );
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new DataJSON(false,e.getMessage(),"")
            );
        }
    }
    @PostMapping("/chapter")
    public ResponseEntity<DataJSON> getChapterHistory(@RequestBody HashMap<String, String> requestData){
        try{
            String username = requestData.get("username");
            List<Chapter> history = historyService.getChapterHistory(username);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get all history successful",history)
            );
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new DataJSON(false,e.getMessage(),"")
            );
        }
    }
    @PostMapping("/id")
    public ResponseEntity<DataJSON> getHistoryID(@RequestBody HashMap<String,String> hashMap){
        try{
            String username = hashMap.get("username");
            List<History> history = historyService.getHistoryID(username);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get all history successful",history)
            );
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new DataJSON(false,e.getMessage(),"")
            );
        }
    }
    @PostMapping("/put")
    public ResponseEntity<DataJSON> upsertHistory(@RequestBody HashMap<String,String> hashMap){
        try{
            String username = hashMap.get("username");
            String comicID = hashMap.get("comicID");
            String chapterID = hashMap.get("chapterID");
            String historyID = hashMap.get("historyID");
            Boolean status = historyService.addToHistoryChapter(chapterID,username,comicID,historyID);
            List<Boolean> statusList = new ArrayList<>();
            statusList.add(status);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get history status upsert successful",statusList)
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new DataJSON(false,e.getMessage(),"")
            );
        }
    }
}
