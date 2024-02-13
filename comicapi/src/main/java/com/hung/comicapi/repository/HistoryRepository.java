package com.hung.comicapi.repository;

import com.hung.comicapi.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History,Integer> {
    @Query(value = "SELECT * FROM comic.history_comic left join history on history_comic.historyIDfk = history.historyID",nativeQuery = true)
    List<History> findAllHistory();
}
