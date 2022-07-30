package jjfactory.nbaplayersearching.busniess.domain.rebound;


import jjfactory.nbaplayersearching.busniess.domain.BaseTimeEntity;
import jjfactory.nbaplayersearching.busniess.domain.match.Match;
import jjfactory.nbaplayersearching.busniess.domain.player.Player;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Rebound extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "player_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Player player;

    @JoinColumn(name = "match_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Match match;

    private ReboundType reboundType;

    @Builder
    public Rebound(Player player, Match match, ReboundType reboundType) {
        this.player = player;
        this.match = match;
        this.reboundType = reboundType;
    }
}
