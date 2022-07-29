package jjfactory.nbaplayersearching.busniess.response;

import jjfactory.nbaplayersearching.busniess.domain.rebound.ReboundType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ReboundRes {
    private String playerName;
    private ReboundType type;
}
