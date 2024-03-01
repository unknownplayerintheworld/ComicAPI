package com.hung.comicapi.controller;

import com.hung.comicapi.model.Chapter;
import com.hung.comicapi.model.DataJSON;
import com.hung.comicapi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/comic/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;
    @PostMapping("/id")
    public ResponseEntity<DataJSON> getListChapter(@RequestBody HashMap<String,String> hashMap){
        try{
            String comicID = hashMap.get("comicID");
            List<Chapter> chapterList = chapterService.getChapterList(comicID);
            if(chapterList.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new DataJSON(true,"There is not a record listchapter!","")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get listcjapter successful!",chapterList)
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),"")
            );
        }
    }
    @PostMapping("/chapterID")
    public ResponseEntity<DataJSON> getChapterID(@RequestBody HashMap<String,String> hashMap){
        try{
            String comicID = hashMap.get("comicID");
            String chapter = hashMap.get("chapterPos");
            List<Chapter> chapterList = chapterService.getChapterID(comicID,chapter);
            if(chapterList.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new DataJSON(true,"There is not a record listchapter!","")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get listcjapter successful!",chapterList)
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),"")
            );
        }
    }
}
