package com.hung.comicapi.service;

import com.hung.comicapi.model.History;
import com.hung.comicapi.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {
    @Autowired
    HistoryRepository historyRepository;

    public List<History> getHistory(){
        try{
            return historyRepository.findAllHistory();
        }catch (Exception q){
            throw new RuntimeException(q.getMessage());
        }
    }
}
