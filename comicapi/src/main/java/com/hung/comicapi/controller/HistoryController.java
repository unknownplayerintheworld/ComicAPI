package com.hung.comicapi.controller;

import com.hung.comicapi.model.Comic;
import com.hung.comicapi.model.DataJSON;
import com.hung.comicapi.model.History;
import com.hung.comicapi.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
