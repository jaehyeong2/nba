package jjfactory.nbaplayersearching.busniess.service;

import jjfactory.nbaplayersearching.busniess.domain.DeleteStatus;
import jjfactory.nbaplayersearching.busniess.domain.player.Player;
import jjfactory.nbaplayersearching.busniess.domain.player.Team;
import jjfactory.nbaplayersearching.busniess.repository.PlayerRepository;
import jjfactory.nbaplayersearching.busniess.repository.TeamRepository;
import jjfactory.nbaplayersearching.busniess.response.PlayerDetailRes;
import jjfactory.nbaplayersearching.busniess.response.PlayerRes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@Transactional
@SpringBootTest
class PlayerServiceTest {

    @Autowired
    PlayerService playerService;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TeamRepository teamRepository;

    @Test
    @DisplayName("플레이어 찾기")
    void findPlayer() {
        //given
        Player 르브론 = Player.builder().name("르브론").build();
        Player 야니스_아테토쿤보 = Player.builder().name("야니스 아테토쿤보").build();

        playerRepository.save(르브론);
        playerRepository.save(야니스_아테토쿤보);

        PlayerDetailRes lebron = playerService.findPlayer(르브론.getId());

        assertThat(lebron.getName()).isEqualTo("르브론");
        assertThat(르브론.getDeleteStatus()).isEqualTo(DeleteStatus.N);

    }

    @Test
    @DisplayName("전체 플레이어 찾기 페이징")
    void findAllPlayer() {
        //given
        Team teamA = Team.builder().name("team A").build();

        List<Player> players = new ArrayList<>();
        for (int i = 1; i < 26; i++) {
//            Player player = Player.builder().name("player"+i).team(teamA).build();
            Player player = Player.builder().name("player"+i).build();
            players.add(player);
        }

        playerRepository.saveAll(players);

        //when
        Page<PlayerRes> allPlayers = playerService.findAllPlayers(0, 10);
        List<String> playerNames = allPlayers.stream().map(p -> p.getName()).collect(Collectors.toList());

        //then
        assertThat(allPlayers.getTotalElements()).isEqualTo(25);
        assertThat(allPlayers.getTotalPages()).isEqualTo(3);
//        assertThat(playerNames.get(0)).isEqualTo("player25");

    }

    @Test
    void registerPlayer() {
    }

    @Test
    @DisplayName("플레이어 삭제")
    void delete(){
        //given
        Player 르브론 = Player.builder().name("르브론").build();
        Player 야니스_아테토쿤보 = Player.builder().name("야니스 아테토쿤보").build();

        playerRepository.save(르브론);
        playerRepository.save(야니스_아테토쿤보);

        //when
        playerService.delete(르브론.getId());

        //then
        assertThat(르브론.getDeleteStatus()).isEqualTo(DeleteStatus.Y);
    }

    @Test
    void update() {
    }
}