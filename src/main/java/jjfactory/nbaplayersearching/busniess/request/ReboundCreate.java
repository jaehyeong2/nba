package jjfactory.nbaplayersearching.busniess.request;

import jjfactory.nbaplayersearching.busniess.domain.rebound.ReboundType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ReboundCreate {
    private Long playerId;
    private Long matchId;
    private ReboundType reboundType;

    @Builder
    public ReboundCreate(Long playerId, Long matchId, ReboundType reboundType) {
        this.playerId = playerId;
        this.matchId = matchId;
        this.reboundType = reboundType;
    }
}
