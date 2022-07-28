package jjfactory.nbaplayersearching.busniess.repository.player;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.nbaplayersearching.busniess.domain.player.Player;
import jjfactory.nbaplayersearching.busniess.domain.player.QPlayer;
import jjfactory.nbaplayersearching.busniess.domain.player.QTeam;
import jjfactory.nbaplayersearching.busniess.response.PlayerRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jjfactory.nbaplayersearching.busniess.domain.player.QPlayer.*;
import static jjfactory.nbaplayersearching.busniess.domain.player.QTeam.*;

@RequiredArgsConstructor
@Repository
public class PlayerQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Page<PlayerRes> findAllPlayers(Pageable pageable){
        List<PlayerRes> players = queryFactory.select(Projections.constructor(PlayerRes.class,
                        player.name.as("name"),
                        player.age.as("age"),
                        player.height.as("height"),
                        player.weight.as("weight"),
                        player.birth.as("birth")))
                .from(player)
//                .innerJoin(team).fetchJoin()
                .orderBy(player.createDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        int total = queryFactory.selectFrom(player)
                .fetch().size();

        return new PageImpl<>(players,pageable,total);
    }
}
