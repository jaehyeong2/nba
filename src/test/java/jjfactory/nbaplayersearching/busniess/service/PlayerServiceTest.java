package jjfactory.nbaplayersearching.busniess.service;

import jjfactory.nbaplayersearching.busniess.domain.DeleteStatus;
import jjfactory.nbaplayersearching.busniess.domain.player.Player;
import jjfactory.nbaplayersearching.busniess.repository.PlayerRepository;
import jjfactory.nbaplayersearching.busniess.response.PlayerDetailRes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@Transactional
@SpringBootTest
class PlayerServiceTest {

    @Autowired
    PlayerService playerService;

    @Autowired
    PlayerRepository playerRepository;

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