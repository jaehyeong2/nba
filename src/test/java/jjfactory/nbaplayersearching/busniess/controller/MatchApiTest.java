package jjfactory.nbaplayersearching.busniess.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jjfactory.nbaplayersearching.busniess.domain.match.Match;
import jjfactory.nbaplayersearching.busniess.domain.team.Team;
import jjfactory.nbaplayersearching.busniess.repository.match.MatchRepository;
import jjfactory.nbaplayersearching.busniess.repository.team.TeamRepository;
import jjfactory.nbaplayersearching.busniess.request.MatchCreate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest
class MatchApiTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("경기 한개 조회 성공")
    void findMatch() throws Exception {
        //given
        Team home = Team.builder().name("home").build();
        Team away = Team.builder().name("away").build();
        teamRepository.save(home);
        teamRepository.save(away);

        Match match = Match.builder().awayTeam(away).homeTeam(home).build();
        matchRepository.save(match);

        //expected
        mockMvc.perform(get("/matches/{matchId}",match.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.homeTeamId").value(home.getId()))
                .andDo(print());
    }

    @Test
    void findMatches() {
    }

    @Test
    @DisplayName("경기 생성 성공")
    void createMatch() throws Exception {
        //given
        Team home = Team.builder().name("home").build();
        Team away = Team.builder().name("away").build();
        teamRepository.save(home);
        teamRepository.save(away);

        MatchCreate match = MatchCreate.builder().homeTeamId(home.getId()).awayTeamId(away.getId()).build();
        String matchStr = objectMapper.writeValueAsString(match);


        //expected
        mockMvc.perform(post("/matches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(matchStr))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    void deleteMatch() {
    }
}