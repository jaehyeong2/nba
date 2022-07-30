package jjfactory.nbaplayersearching.busniess.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class MatchCreate {
    private Long homeTeamId;
    private Long awayTeamId;

    private String location;
    private LocalDateTime matchTime;
    private int firstQtScore;
    private int secondQtScore;
    private int thirdQtScore;
    private int fourthQtScore;

    @Builder
    public MatchCreate(Long homeTeamId, Long awayTeamId, String location, LocalDateTime matchTime, int firstQtScore, int secondQtScore, int thirdQtScore, int fourthQtScore) {
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.location = location;
        this.matchTime = matchTime;
        this.firstQtScore = firstQtScore;
        this.secondQtScore = secondQtScore;
        this.thirdQtScore = thirdQtScore;
        this.fourthQtScore = fourthQtScore;
    }
}
