package jjfactory.nbaplayersearching.busniess.service;

import jjfactory.nbaplayersearching.busniess.domain.player.Player;
import jjfactory.nbaplayersearching.busniess.domain.player.Team;
import jjfactory.nbaplayersearching.busniess.repository.PlayerRepository;
import jjfactory.nbaplayersearching.busniess.repository.TeamRepository;
import jjfactory.nbaplayersearching.busniess.request.PlayerCreate;
import jjfactory.nbaplayersearching.busniess.response.PlayerDetailRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional
@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    @Transactional(readOnly = true)
    public PlayerDetailRes findPlayer(Long playerId){
        Player player = getPlayer(playerId);
        return new PlayerDetailRes(player);
    }
    public String registerPlayer(PlayerCreate dto,Long teamId){
        Team team = getTeam(teamId);
        Player.create(dto,team);
        return "Y";
    }

    public String delete(Long playerId){
        Player player = getPlayer(playerId);
        player.delete();
        return "Y";
    }

    public String update(){
        return "Y";
    }

    private Player getPlayer(Long playerId) {
        Player player = playerRepository.findById(playerId).orElseThrow(NoSuchElementException::new);
        return player;
    }

    private Team getTeam(Long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow(NoSuchElementException::new);
        return team;
    }
}
