package jjfactory.nbaplayersearching.busniess.domain.match;

import jjfactory.nbaplayersearching.busniess.domain.BaseTimeEntity;
import jjfactory.nbaplayersearching.busniess.domain.team.Team;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Match extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "home_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Team homeTeam;

    @JoinColumn(name = "away_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Team awayTeam;

    private LocalDateTime matchTime;

    private int firstQtScore;
    private int secondQtScore;
    private int thirdQtScore;
    private int fourthQtScore;

    @Builder
    public Match(Team homeTeam, Team awayTeam, LocalDateTime matchTime, int firstQtScore, int secondQtScore, int thirdQtScore, int fourthQtScore) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchTime = matchTime;
        this.firstQtScore = firstQtScore;
        this.secondQtScore = secondQtScore;
        this.thirdQtScore = thirdQtScore;
        this.fourthQtScore = fourthQtScore;
    }
}
