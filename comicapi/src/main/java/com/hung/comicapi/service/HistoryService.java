package com.hung.comicapi.service;

import com.hung.comicapi.model.*;
import com.hung.comicapi.repository.ChapterRepository;
import com.hung.comicapi.repository.ComicRepository;
import com.hung.comicapi.repository.HistoryRepository;
import com.hung.comicapi.repository.TransTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class HistoryService {
    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    ComicRepository comicRepository;

    @Autowired
    TransTeamRepository transTeamRepository;

    @Autowired
    ChapterRepository chapterRepository;

    public List<History> getHistory(){
        try{
            return historyRepository.findAllHistory();
        }catch (Exception q){
            throw new RuntimeException(q.getMessage());
        }
    }
    public List<Comic> getComicHistory(String username){
        try{
            return comicRepository.findComicDetailsByUsername(username);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<transteam> getTransteamHistory(String username){
        try{
            return transTeamRepository.findTransDetailsByUsername(username);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Chapter> getChapterHistory(String username){
        try{
            return chapterRepository.findChapterDetailsByUsername(username);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Boolean> removeFromHistory(String comicID,String historyID){
        List<Boolean> status = new ArrayList<>();
        try{
            int rows = historyRepository.removeFromHistory(comicID,historyID);
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
    public List<History> getHistoryID(String username){
        try{
            return historyRepository.findHistoryID(username);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public Boolean addToHistoryChapter(String chapterID,String username,String comicID,String historyID){
        try{
            List<Comic> comics = comicRepository.findComicHistoryByUsernameAndComicID(comicID,username);
            if(!comics.isEmpty()){
                String chapterOLD = historyRepository.getChapterID(comicID,username);
                if(!chapterOLD.isEmpty()) {
                    int rows = historyRepository.updateHistoryChapter(chapterID,chapterOLD);
                    if (rows > 0){
                        int rows2 = historyRepository.updateHistoryComic(historyID,comicID);
                        return rows2 > 0;
                    }
                    else{
                        return false;
                    }
                }
                else{
                    return false;
                }
            }
            else{
                int rows1 = historyRepository.addToHistoryComic(comicID,historyID);
                if(rows1 > 0){
                    int rows2 = historyRepository.addToHistoryChapter(chapterID);
                    return rows2 > 0;
                }
                else{
                    return false;
                }
            }
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
