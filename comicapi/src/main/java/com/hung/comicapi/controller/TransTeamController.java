package com.hung.comicapi.controller;

import com.hung.comicapi.model.Comic;
import com.hung.comicapi.model.DataJSON;
import com.hung.comicapi.model.transteam;
import com.hung.comicapi.service.TransTeamService;
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
@RequestMapping(path = "api/v1/trans")
public class TransTeamController {
    @Autowired
    private TransTeamService transTeamService;
    @PostMapping("/comic")
    public ResponseEntity<DataJSON> getTransTeam(@RequestBody HashMap<String,String> hashMap){
        try{
            String comicID = hashMap.get("comicID");
            List<transteam> transteamList = transTeamService.getTrans(comicID);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"get TransTeam successful!",transteamList)
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,"Failed to get TransTeam!","")
            );
        }
    }
    @PostMapping("/id")
    public ResponseEntity<DataJSON> getTransTeamByID(@RequestBody HashMap<String,String> hashMap){
        try{
            String comicID = hashMap.get("transteamID");
            List<transteam> transteamList = transTeamService.getTransByID(comicID);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"get TransTeam successful!",transteamList)
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,"Failed to get TransTeam!","")
            );
        }
    }
    @PostMapping("/transName")
    public ResponseEntity<DataJSON> getTransSearch(@RequestBody HashMap<String,String> hashMap){
        try{
            String transname = hashMap.get("transname");
            List<transteam> transteamList = transTeamService.getTransSearch(transname);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"get TransTeam successful!",transteamList)
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,"Failed to get TransTeam!","")
            );
        }
    }
}
