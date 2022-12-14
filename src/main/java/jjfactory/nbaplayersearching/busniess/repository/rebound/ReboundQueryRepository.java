package jjfactory.nbaplayersearching.busniess.repository.rebound;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.nbaplayersearching.busniess.domain.rebound.ReboundType;
import jjfactory.nbaplayersearching.busniess.response.ReboundRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static jjfactory.nbaplayersearching.busniess.domain.rebound.QRebound.*;

@RequiredArgsConstructor
@Repository
public class ReboundQueryRepository{
    private final JPAQueryFactory queryFactory;

    public Page<ReboundRes> findReboundsInMatch(Pageable pageable, Long matchId, String playerName){
        List<ReboundRes> reboundResList = queryFactory.select(Projections.constructor(ReboundRes.class, rebound))
                .from(rebound)
                .where(rebound.match.id.eq(matchId),
                        eqPlayerName(playerName))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        int total = queryFactory.select(Projections.constructor(ReboundRes.class, rebound))
                .from(rebound)
                .where(rebound.match.id.eq(matchId),
                        eqPlayerName(playerName))
                .fetch().size();

        return new PageImpl<>(reboundResList,pageable,total);
    }

    private BooleanExpression eqPlayerName(String playerName){
        if(StringUtils.hasText(playerName)) {
            return rebound.player.name.eq(playerName);
        }
        return null;
    }

    public int countReboundByType(ReboundType type){
        return queryFactory.selectFrom(rebound)
                .where(rebound.reboundType.eq(type))
                .fetch().size();
    }

    public int countSeasonTotalRebound(){
        return queryFactory.selectFrom(rebound)
                .fetch().size();
    }
}
