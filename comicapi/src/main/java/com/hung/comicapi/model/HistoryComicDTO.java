package com.hung.comicapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class HistoryComicDTO {
    private List<Comic> comicList;
    private List<Chapter> chapterList;
    private List<transteam> transteamList;
}
