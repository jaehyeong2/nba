package jjfactory.nbaplayersearching.busniess.response;

import jjfactory.nbaplayersearching.busniess.domain.rebound.Rebound;
import jjfactory.nbaplayersearching.busniess.domain.rebound.ReboundType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ReboundRes {
    private String playerName;
    private ReboundType type;

    public ReboundRes(Rebound rebound) {
        this.playerName = rebound.getPlayer().getName();
        this.type = rebound.getReboundType();
    }
}
