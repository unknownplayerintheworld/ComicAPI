package com.hung.comicapi.service;

import com.hung.comicapi.model.Chapter;
import com.hung.comicapi.model.Comic;
import com.hung.comicapi.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterService {
    @Autowired
    private ChapterRepository chapterRepository;
    public List<Chapter> getChapterList(String comicID){
        try{
            List<Chapter> chapters = chapterRepository.findChaptersByComicID(comicID);
            return chapters;
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Chapter> getChapterID(String comicID,String chapter_number_pos){
        try{
            List<Chapter> chapters = chapterRepository.getChapterID(comicID,chapter_number_pos);
            return chapters;
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Chapter> getChapterHistoryComic(String comicID,String accountID){
        try{
            return chapterRepository.getChapterHistoryByComic(comicID,accountID);
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
