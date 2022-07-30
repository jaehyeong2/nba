package jjfactory.nbaplayersearching.busniess.service;

import jjfactory.nbaplayersearching.busniess.domain.DeleteStatus;
import jjfactory.nbaplayersearching.busniess.domain.match.Match;
import jjfactory.nbaplayersearching.busniess.domain.player.Player;
import jjfactory.nbaplayersearching.busniess.domain.rebound.Rebound;
import jjfactory.nbaplayersearching.busniess.domain.rebound.ReboundType;
import jjfactory.nbaplayersearching.busniess.domain.team.Team;
import jjfactory.nbaplayersearching.busniess.repository.match.MatchRepository;
import jjfactory.nbaplayersearching.busniess.repository.player.PlayerRepository;
import jjfactory.nbaplayersearching.busniess.repository.rebound.ReboundRepository;
import jjfactory.nbaplayersearching.busniess.repository.team.TeamRepository;
import jjfactory.nbaplayersearching.busniess.request.ReboundCreate;
import jjfactory.nbaplayersearching.busniess.response.ReboundRes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class ReboundServiceTest {

    @Autowired
    ReboundService reboundService;
    @Autowired
    ReboundRepository reboundRepository;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    MatchRepository matchRepository;
    @Autowired
    TeamRepository teamRepository;

    // 리포지토리에서 한거랑 똑같지 않나..?
    @Test
    @DisplayName("한경기 한 플레이어 리바운드 셀렉트 테스트")
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

        for (int i = 0; i < 15; i++) {
            Rebound d = Rebound.builder().reboundType(ReboundType.DEFENSE).player(test1).match(match).build();
            reboundRepository.save(d);
        }

        //when
        Page<ReboundRes> allReboundsInMatch = reboundService.findAllReboundsInMatch(1, 10, match.getId(), null);

        //then
        assertThat(allReboundsInMatch.getTotalPages()).isEqualTo(2);
        assertThat(allReboundsInMatch.getTotalElements()).isEqualTo(15);
    }

    @Test
    @DisplayName("리바운드 생성 성공")
    void create() {
        //given
        Team teamA = Team.builder().name("teamA").build();
        Team teamB = Team.builder().name("teamB").build();
        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Match match = Match.builder().homeTeam(teamA).awayTeam(teamB).build();
        matchRepository.save(match);

        Player test1 = Player.builder().name("test").build();
        playerRepository.save(test1);

        ReboundCreate dto = ReboundCreate.builder().matchId(match.getId())
                .reboundType(ReboundType.DEFENSE).playerId(test1.getId()).build();

        //when
        String result = reboundService.create(dto);
        List<Player> players = reboundRepository.findAll().stream().map(r -> r.getPlayer()).collect(Collectors.toList());

        //then
        assertThat(result).isEqualTo("Y");
        assertThat(reboundRepository.count()).isEqualTo(1);
        assertThat(players).containsExactly(test1);
    }

    @Test
    @DisplayName("삭제 테스트")
    void delete() {
        //given
        Team teamA = Team.builder().name("teamA").build();
        Team teamB = Team.builder().name("teamB").build();
        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Match match = Match.builder().homeTeam(teamA).awayTeam(teamB).build();
        matchRepository.save(match);

        Player test1 = Player.builder().name("test").build();
        playerRepository.save(test1);

        Rebound d = Rebound.builder().reboundType(ReboundType.DEFENSE).player(test1).match(match).build();
        reboundRepository.save(d);

        //when
        reboundService.delete(d.getId());

        //then
        assertThat(d.getDeleteStatus()).isEqualTo(DeleteStatus.Y);
    }

}