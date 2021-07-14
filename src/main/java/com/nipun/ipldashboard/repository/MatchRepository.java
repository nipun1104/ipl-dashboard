package com.nipun.ipldashboard.repository;

import java.util.List;

import com.nipun.ipldashboard.model.Match;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> getByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pageable);

    default List<Match> getLatestMatchesByTeam(String teamName, int count){
        Pageable pageable = PageRequest.of(0, count);
        return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, pageable);
    }
}
