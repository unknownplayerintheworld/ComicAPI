package com.hung.comicapi.controller;

import com.hung.comicapi.model.DataJSON;
import com.hung.comicapi.model.ImageChapter;
import com.hung.comicapi.model.Chapter;
import com.hung.comicapi.service.ImageChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/imagechapter")
public class ImageChapterController {
    @Autowired
    ImageChapterService imageChapterService;

    @PostMapping("/")
    public ResponseEntity<DataJSON> getAllImagesByChapter(@RequestBody Chapter Chapter){
        try{
            List<ImageChapter> images = imageChapterService.getListImageByChapter(Chapter);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get Image successful",images)
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),"")
            );
        }
    }
}
