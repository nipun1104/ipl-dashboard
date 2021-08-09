package com.nipun.ipldashboard.controller;

import java.util.List;

import com.nipun.ipldashboard.model.Match;
import com.nipun.ipldashboard.model.Team;
import com.nipun.ipldashboard.repository.MatchRepository;
import com.nipun.ipldashboard.repository.TeamRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class TeamController {

    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/teams/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
        Team team = teamRepository.findByTeamName(teamName);
        if (team != null) {
            List<Match> matches = matchRepository.getLatestMatchesByTeam(teamName, 4);
            team.setMatches(matches);
        }
        return team;
    }
}
