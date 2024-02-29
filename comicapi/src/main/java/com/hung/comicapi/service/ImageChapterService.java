package com.hung.comicapi.service;

import com.hung.comicapi.model.ImageChapter;
import com.hung.comicapi.model.Chapter;
import com.hung.comicapi.repository.ImageChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageChapterService {
    @Autowired
    ImageChapterRepository imageChapterRepository;

    public List<ImageChapter> getListImageByChapter(Chapter Chapter){
        try{
            List<ImageChapter> images = imageChapterRepository.getImageChapterWithChapterID(Chapter.getChapter_number_pos(),Chapter.getChapter_comicID());
            if(images.isEmpty()){
                throw new RuntimeException("Don't have an image for chapter"+Chapter.getChapter_number_pos() + Chapter.getChapter_comicID());
            }
            else{
                return images;
            }
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
