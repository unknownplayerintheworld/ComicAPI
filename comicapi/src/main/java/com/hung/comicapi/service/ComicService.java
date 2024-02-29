package com.hung.comicapi.service;

import com.hung.comicapi.model.Account;
import com.hung.comicapi.model.Comic;
import com.hung.comicapi.model.Genre;
import com.hung.comicapi.model.favourite;
import com.hung.comicapi.repository.ComicRepository;
import com.hung.comicapi.repository.FavouriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComicService {
    @Autowired
    private ComicRepository comicrepo;
    @Autowired
    private FavouriteRepository favouriteRepository;
    public List<Comic> getAllComic(){
        try{
            return comicrepo.findAll();
        }catch (Exception e){
            throw new RuntimeException("Failed to get all comic");
        }
    }
    public List<Comic> getComic(Comic comic){
        try{
            return comicrepo.findByComicID(comic.getComicID());
        }catch (Exception e){
            throw new RuntimeException(e.getMessage() + "\n Failed to get comic with ID:" + comic.getComicID());
        }
    }
    public List<Comic> getOutstandingComicByMonth(){
        try{
            return comicrepo.findOutstandingComic();
        }catch (Exception e){
            throw new RuntimeException("Failed to get outstanding comics");
        }
    }
    public List<Comic> getProposalComicByUsername(String username){
        try{
            List<Comic> comics = comicrepo.findProposalComic(username);
            if(comics.isEmpty()){
                throw new RuntimeException("There is not having a record!Please check object you request to server!");
            }
            else{
                return comics;
            }
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Comic> getRankingComic(){
        try{
            List<Comic> comics = comicrepo.findRankingComic();
            if(comics.isEmpty()){
                throw new RuntimeException("There is not having a record ranking!");
            }
            else{
                return comics;
            }
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Comic> getShounenComic(){
        try{
            List<Comic> comics = comicrepo.findShounenComic();
            if(comics.isEmpty()){
                throw new RuntimeException("There is not having a record shounen!");
            }
            else{
                return comics;
            }
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Comic> getComicByGenre(Genre genre){
        try{
            List<Comic> comics = comicrepo.getComicsByGenre(genre.getGenrename());
            if(comics.isEmpty()){
                throw new RuntimeException("There is not having a record comic!");
            }
            else{
                return comics;
            }
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Comic> getFavouritesComic(Account account){
        try{
            List<Comic> comics = comicrepo.getFavouriteComics(account.getUsername());
            if(comics.isEmpty()){
                throw new RuntimeException("There is not having a record favourites comic!");
            }
            else{
                return comics;
            }
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<favourite> getFavouriteStatus(String username,String comicID){
        try{
            List<favourite> favourites = favouriteRepository.getFavouriteStatus(username,comicID);
            return favourites;
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<favourite> getFavouriteID(String username){
        try{
            List<favourite> favourites = favouriteRepository.getFavouriteID(username);
            return favourites;
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Boolean> AddtoFavourite(String comicID,String favID){
        List<Boolean> status = new ArrayList<>();
        try{
            int rows = comicrepo.AddtoFavourite(comicID,favID);
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
    public List<Boolean> removeFromFavourite(String comicID,String favID){
        List<Boolean> status = new ArrayList<>();
        try{
            int rows = comicrepo.removeFromFavourite(comicID,favID);
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
    public List<Comic> getComicByTransID(String transteamID){
        try{
            List<Comic> comics = comicrepo.findComicByTransID(transteamID);
            if(comics.isEmpty()){
                throw new RuntimeException("There is not having a record comic with transteamID:"+transteamID +"!");
            }
            else{
                return comics;
            }
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Comic> getComicSearch(String comicName){
        try{
            List<Comic> comics = comicrepo.findComics(comicName);
                return comics;
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Boolean> updateCountViews(String comicID){
        List<Boolean> status = new ArrayList<>();
        try{
            int rows = comicrepo.updateViewsCounts(comicID);
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
}
