package com.nipun.ipldashboard.repository;

import java.time.LocalDate;
import java.util.List;

import com.nipun.ipldashboard.model.Match;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> getByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pageable);

    @Query("select m from Match m where (m.team1 = :teamName or m.team2 = :teamName) and m.date between :dateStart and :dateEnd order by date desc")
    List<Match> getMatchesByTeamBetweenDates(@Param("teamName") String teamName,
            @Param("dateStart") LocalDate startDate, @Param("dateEnd") LocalDate endDate);

    // List<Match>
    // getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(String team1,
    // LocalDate date1,
    // LocalDate date2, String team2, LocalDate date3, LocalDate date4);

    default List<Match> getLatestMatchesByTeam(String teamName, int count) {
        Pageable pageable = PageRequest.of(0, count);
        return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, pageable);
    }
}
