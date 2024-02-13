package com.hung.comicapi.service;

import com.hung.comicapi.model.Comic;
import com.hung.comicapi.repository.ComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComicService {
    @Autowired
    private ComicRepository comicrepo;
    public List<Comic> getAllComic(){
        try{
            return comicrepo.findAll();
        }catch (Exception e){
            throw new RuntimeException("Failed to get all comic");
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
}
