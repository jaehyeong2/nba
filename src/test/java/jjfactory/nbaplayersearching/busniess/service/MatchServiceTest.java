package jjfactory.nbaplayersearching.busniess.service;

import jjfactory.nbaplayersearching.busniess.domain.DeleteStatus;
import jjfactory.nbaplayersearching.busniess.domain.match.Match;
import jjfactory.nbaplayersearching.busniess.domain.team.Team;
import jjfactory.nbaplayersearching.busniess.repository.match.MatchRepository;
import jjfactory.nbaplayersearching.busniess.repository.team.TeamRepository;
import jjfactory.nbaplayersearching.busniess.request.MatchCreate;
import jjfactory.nbaplayersearching.busniess.response.MatchRes;
import jjfactory.nbaplayersearching.global.handler.ex.BusinessException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MatchServiceTest {

    @Autowired
    MatchService matchService;

    @Autowired
    MatchRepository matchRepository;
    @Autowired
    TeamRepository teamRepository;

    @Test
    @DisplayName("경기 단건 조회 성공")
    void findMatchInfo() {
        //given
        Team home = Team.builder().name("home").build();
        Team away = Team.builder().name("away").build();
        teamRepository.save(home);
        teamRepository.save(away);

        Match match = Match.builder().awayTeam(away).homeTeam(home).build();
        matchRepository.save(match);

        //when
        MatchRes matchInfo = matchService.findMatchInfo(match.getId());

        //then
        assertThat(matchInfo.getHomeTeamId()).isEqualTo(home.getId());
        assertThat(matchInfo.getAwayTeamId()).isEqualTo(away.getId());

    }

    @Test
    @DisplayName("경기 조회 페이징")
    void findMatches() {
        //given
        Team home = Team.builder().name("home").build();
        Team away = Team.builder().name("away").build();
        teamRepository.save(home);
        teamRepository.save(away);

        for (int i = 1; i < 26; i++) {
            Match match = Match.builder().awayTeam(away).homeTeam(home).build();
            matchRepository.save(match);
        }

        //when
        Page<MatchRes> matches = matchService.findMatches(1, 10);

        //then
        assertThat(matches.getTotalElements()).isEqualTo(25);
        assertThat(matches.getTotalPages()).isEqualTo(3);

    }

    @Test
    @DisplayName("경기 생성 성공")
    void create() {
        //given
        Team home = Team.builder().name("home").build();
        Team away = Team.builder().name("away").build();
        teamRepository.save(home);
        teamRepository.save(away);

        MatchCreate dto = MatchCreate.builder().awayTeamId(away.getId()).homeTeamId(home.getId()).build();
        matchService.create(dto);

        //when
        List<Team> teams = matchRepository.findAll().stream().map(m -> m.getHomeTeam()).collect(Collectors.toList());

        //then
        assertThat(matchRepository.count()).isEqualTo(1);
        assertThat(teams).contains(home);
    }

    @Test
    @DisplayName("홈팀 어웨이팀 같으면 경기 생성 실패")
    void createEx() {
        //given
        Team home = Team.builder().name("home").build();
        teamRepository.save(home);

        MatchCreate dto = MatchCreate.builder().awayTeamId(home.getId()).homeTeamId(home.getId()).build();

        //expected
        assertThatThrownBy(() -> matchService.create(dto)
        ).isInstanceOf(BusinessException.class);
    }

    @Test
    @DisplayName("경기 소프트 딜리트 성공")
    void delete() {
        //given
        Team home = Team.builder().name("home").build();
        Team away = Team.builder().name("away").build();
        teamRepository.save(home);
        teamRepository.save(away);

        Match match = Match.builder().awayTeam(away).homeTeam(home).build();
        matchRepository.save(match);

        //when
        matchService.delete(match.getId());

        //then
        assertThat(match.getDeleteStatus()).isEqualTo(DeleteStatus.Y);
    }


    //TODO 이거해야함
    @Test
    void update() {
        //given
        Team home = Team.builder().name("home").build();
        Team away = Team.builder().name("away").build();
        teamRepository.save(home);
        teamRepository.save(away);

        Match match = Match.builder().awayTeam(away).homeTeam(home).build();
        matchRepository.save(match);

    }
}