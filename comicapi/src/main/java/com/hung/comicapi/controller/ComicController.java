package com.hung.comicapi.controller;

import com.hung.comicapi.model.Account;
import com.hung.comicapi.model.Comic;
import com.hung.comicapi.model.DataJSON;
import com.hung.comicapi.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/comic")
public class ComicController {
    @Autowired
    private ComicService comicService;

    @GetMapping("/")
    public ResponseEntity<DataJSON> getAllComics(){
        try{
            List<Comic> comic = comicService.getAllComic();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get all comics successful",comic)
            );
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new DataJSON(false,e.getMessage(),"")
            );
        }
    }
    @GetMapping("/outstanding")
    public ResponseEntity<DataJSON> getOutstandingComics(){
        try{
            List<Comic> comics = comicService.getOutstandingComicByMonth();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get Outstanding comics successful",comics)
            );
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),"")
            );
        }
    }
    @PostMapping("/proposal")
    public ResponseEntity<DataJSON> getProposalComics(@RequestBody Account account){
        try{
            List<Comic> comics = comicService.getProposalComicByUsername(account.getUsername());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get proposal comics successful",comics)
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),"")
            );
        }
    }
    @GetMapping("/ranking")
    public ResponseEntity<DataJSON> getRankingComics(){
        try{
            List<Comic> comics = comicService.getRankingComic();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get ranking comics successful",comics)
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),"")
            );
        }
    }
    @GetMapping("/shounen")
    public ResponseEntity<DataJSON> getShounenComic(){
        try{
            List<Comic> comics = comicService.getShounenComic();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get shounen comics successful",comics)
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),"")
            );
        }
    }
}
