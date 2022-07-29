package jjfactory.nbaplayersearching.busniess.repository.match;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.nbaplayersearching.busniess.response.MatchRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.JpaQueryCreator;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jjfactory.nbaplayersearching.busniess.domain.match.QMatch.match;


@RequiredArgsConstructor
@Repository
public class MatchQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<MatchRes> findMatches(Pageable pageable){
        List<MatchRes> matches = queryFactory.select(Projections.constructor(MatchRes.class, match))
                .from(match)
                .orderBy(match.createDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        int total = queryFactory.select(Projections.constructor(MatchRes.class), match)
                .from(match).fetch().size();

        return new PageImpl<>(matches,pageable,total);
    }
}
