package jjfactory.nbaplayersearching.busniess.repository.rebound;

import jjfactory.nbaplayersearching.busniess.domain.match.Match;
import jjfactory.nbaplayersearching.busniess.domain.player.Player;
import jjfactory.nbaplayersearching.busniess.domain.rebound.Rebound;
import jjfactory.nbaplayersearching.busniess.domain.rebound.ReboundType;
import jjfactory.nbaplayersearching.busniess.domain.team.Team;
import jjfactory.nbaplayersearching.busniess.repository.match.MatchRepository;
import jjfactory.nbaplayersearching.busniess.repository.player.PlayerRepository;
import jjfactory.nbaplayersearching.busniess.repository.team.TeamRepository;
import jjfactory.nbaplayersearching.busniess.response.PlayerRes;
import jjfactory.nbaplayersearching.busniess.response.ReboundRes;
import net.bytebuddy.utility.nullability.AlwaysNull;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class ReboundQueryRepositoryTest {

    @Autowired
    ReboundRepository reboundRepository;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    MatchRepository matchRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    ReboundQueryRepository reboundQueryRepository;

    @Test
    @DisplayName("한경기 모든 리바운드 다 셀렉트 테스트")
    void findAllReboundsInMatch() {
        //given
        Team teamA = Team.builder().name("teamA").build();
        Team teamB = Team.builder().name("teamB").build();
        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Match match = Match.builder().homeTeam(teamA).awayTeam(teamB).build();
        Match match2 = Match.builder().homeTeam(teamA).awayTeam(teamB).build();
        matchRepository.save(match);
        matchRepository.save(match2);

        Player test1 = Player.builder().name("test").build();
        Player test2 = Player.builder().name("test2").build();
        playerRepository.save(test1);
        playerRepository.save(test2);

        for (int i = 0; i < 10; i++) {
            Rebound d = Rebound.builder().reboundType(ReboundType.DEFENSE).player(test1).match(match).build();
            Rebound o = Rebound.builder().reboundType(ReboundType.OFFENSE).player(test2).match(match2).build();
            reboundRepository.save(d);
            reboundRepository.save(o);
        }
        Pageable pageRequest = PageRequest.of(1, 10);

        //when
        Page<ReboundRes> reboundsInMatch = reboundQueryRepository.findReboundsInMatch(pageRequest, match.getId());

        //then
        assertThat(reboundsInMatch.getTotalPages()).isEqualTo(1);
        assertThat(reboundsInMatch.getTotalElements()).isEqualTo(10);
    }

    @Test
    @DisplayName("공격/수비 리바운드 카운트 테스트")
    void countReboundByType() {
        //given
        Player test = Player.builder().name("test").build();
        playerRepository.save(test);

        for (int i = 0; i < 5; i++) {
            Rebound d = Rebound.builder().reboundType(ReboundType.DEFENSE).player(test).build();
            Rebound o = Rebound.builder().reboundType(ReboundType.OFFENSE).player(test).build();
            reboundRepository.save(d);
            reboundRepository.save(o);
        }

        //when
        int defenseReboundCount = reboundQueryRepository.countReboundByType(ReboundType.DEFENSE);
        int offenseReboundCount = reboundQueryRepository.countReboundByType(ReboundType.OFFENSE);

        assertThat(defenseReboundCount).isEqualTo(5);
        assertThat(offenseReboundCount).isEqualTo(5);
    }

    @Test
    @DisplayName("플레이어 시즌 총 리바운드 구하기 테스트")
    void countSeasonTotalRebound() {
    }
}