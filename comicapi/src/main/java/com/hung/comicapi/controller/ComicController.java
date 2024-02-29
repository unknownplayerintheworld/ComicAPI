package com.hung.comicapi.controller;

import com.hung.comicapi.model.*;
import com.hung.comicapi.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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
    @PostMapping("/comicID")
    public ResponseEntity<DataJSON> getProposalComics(@RequestBody Comic comic){
        try{
            List<Comic> comics = comicService.getComic(comic);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get comic successful",comics)
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
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
    @PostMapping("/category")
    public ResponseEntity<DataJSON> getComicByGenre(@RequestBody Genre genre){
        try{
            List<Comic> comics = comicService.getComicByGenre(genre);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get comics successful",comics)
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),"")
            );
        }
    }
    @PostMapping("/favourite")
    public ResponseEntity<DataJSON> getFavouritesComic(@RequestBody Account account){
        try{
            List<Comic> comics = comicService.getFavouritesComic(account);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get fav comics successful",comics)
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),"")
            );
        }
    }
    @PostMapping("/favourite/status")
    public ResponseEntity<DataJSON> getFavouriteStatus(@RequestBody HashMap<String, String> requestData){
        try{
            String username = requestData.get("username");
            String comicID = requestData.get("comicID");
            List<favourite> status = comicService.getFavouriteStatus(username,comicID);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get fav comics successful",status)
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),"")
            );
        }
    }
    @PostMapping("/favourite/id")
    public ResponseEntity<DataJSON> getFavouriteID(@RequestBody HashMap<String, String> requestData){
        try{
            String username = requestData.get("username");
            List<favourite> status = comicService.getFavouriteID(username);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get fav id successful",status)
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),"")
            );
        }
    }

    @PostMapping("/favourite/add")
    public ResponseEntity<DataJSON> addToFav(@RequestBody HashMap<String, String> requestData){
        try{
            String comicID = requestData.get("comicID");
            String favouriteID = requestData.get("favouriteID");
            List<Boolean> addtoFavStatus = comicService.AddtoFavourite(comicID,favouriteID);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Add to fav comics successful",addtoFavStatus)
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),"")
            );
        }
    }
    @DeleteMapping("/favourite/remove")
    public ResponseEntity<DataJSON> removeFromFavourite(@RequestBody HashMap<String, String> requestData){
        try{
            String comicID = requestData.get("comicID");
            String favouriteID = requestData.get("favouriteID");
            List<Boolean> removeFromFavStatus = comicService.removeFromFavourite(comicID,favouriteID);
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
    @PostMapping("/trans")
    public ResponseEntity<DataJSON> getComicByTransID(@RequestBody HashMap<String,String> hashMap){
        try{
            String transteamID = hashMap.get("transteamID");
            List<Comic> comics = comicService.getComicByTransID(transteamID);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get comics successful",comics)
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),"")
            );
        }
    }
    @PostMapping("/comicName")
    public ResponseEntity<DataJSON> getComics(@RequestBody HashMap<String,String> hashMap){
        try{
            String comicName = hashMap.get("comicName");
            List<Comic> comics = comicService.getComicSearch(comicName);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Get comics successful",comics)
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),"")
            );
        }
    }
    @PostMapping("/update/views")
    public ResponseEntity<DataJSON> updateViewsCount(@RequestBody HashMap<String, String> requestData){
        try{
            String comicID = requestData.get("comicID");
            List<Boolean> updateViewsCountStatus = comicService.updateCountViews(comicID);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Remove fav comics successful",updateViewsCountStatus)
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,e.getMessage(),"")
            );
        }
    }
}
