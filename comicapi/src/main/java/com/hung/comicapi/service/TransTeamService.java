package com.hung.comicapi.service;

import com.hung.comicapi.model.Comic;
import com.hung.comicapi.model.transteam;
import com.hung.comicapi.repository.ComicRepository;
import com.hung.comicapi.repository.TransTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransTeamService {
    @Autowired
    private TransTeamRepository transTeamRepository;

    public List<transteam> getTrans(String comicID){
        try{
            List<transteam> list = transTeamRepository.findByComicName(comicID);
            return list;
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<transteam> getTransByID(String transID){
        try{
            List<transteam> list = transTeamRepository.findByTransTeamID(transID);
            return list;
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<transteam> getTransSearch(String transName){
        try{
            List<transteam> list = transTeamRepository.findTrans(transName);
            return list;
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
