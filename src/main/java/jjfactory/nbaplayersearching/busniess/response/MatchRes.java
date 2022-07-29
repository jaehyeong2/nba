package jjfactory.nbaplayersearching.busniess.response;

import com.fasterxml.jackson.annotation.JsonGetter;
import jjfactory.nbaplayersearching.busniess.domain.match.Match;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class MatchRes {

    private Long homeTeamId;
    private Long awayTeamId;
    private Long matchId;
    private String location;
    private LocalDateTime matchTime;

    @Builder
    public MatchRes(Long homeTeamId, Long awayTeamId, Long matchId, String location, LocalDateTime matchTime) {
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.matchId = matchId;
        this.location = location;
        this.matchTime = matchTime;
    }

    public MatchRes(Match match) {
        this.homeTeamId = match.getHomeTeam().getId();
        this.awayTeamId = match.getAwayTeam().getId();
        this.matchId = match.getId();
        this.location = match.getLocation();
        this.matchTime = match.getMatchTime();
    }
}
