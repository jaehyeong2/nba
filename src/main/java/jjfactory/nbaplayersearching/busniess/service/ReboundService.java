package jjfactory.nbaplayersearching.busniess.service;

import jjfactory.nbaplayersearching.busniess.domain.match.Match;
import jjfactory.nbaplayersearching.busniess.domain.player.Player;
import jjfactory.nbaplayersearching.busniess.domain.rebound.Rebound;
import jjfactory.nbaplayersearching.busniess.repository.match.MatchRepository;
import jjfactory.nbaplayersearching.busniess.repository.player.PlayerRepository;
import jjfactory.nbaplayersearching.busniess.repository.rebound.ReboundQueryRepository;
import jjfactory.nbaplayersearching.busniess.repository.rebound.ReboundRepository;
import jjfactory.nbaplayersearching.busniess.request.ReboundCreate;
import jjfactory.nbaplayersearching.busniess.response.ReboundRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional
@Service
public class ReboundService {
    private final ReboundRepository reboundRepository;
    private final ReboundQueryRepository reboundQueryRepository;
    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;

    @Transactional(readOnly = true)
    public ReboundRes findRebound(Long reboundId){
        Rebound reboundInfo = getReboundInfo(reboundId);
        return new ReboundRes(reboundInfo);
    }

    @Transactional(readOnly = true)
    public Page<ReboundRes> findAllReboundsInMatch(int page, int size,Long matchId,String playerName){
        Pageable pageable = PageRequest.of(page,size);
        return reboundQueryRepository.findReboundsInMatch(pageable,matchId,playerName);
    }

    public String create(ReboundCreate dto){
        Player player = getPlayer(dto.getPlayerId());
        Match match = getMatch(dto.getMatchId());
        Rebound rebound = Rebound.create(dto, player, match);
        reboundRepository.save(rebound);
        return "Y";
    }

    public String delete(Long reboundId){
        Rebound reboundInfo = getReboundInfo(reboundId);
        reboundInfo.delete();
        return "Y";
    }

    public String update(Long reboundId){
        Rebound reboundInfo = getReboundInfo(reboundId);
        return "Y";
    }

    private Rebound getReboundInfo(Long matchId) {
        Rebound rebound = reboundRepository.findById(matchId).orElseThrow(NoSuchElementException::new);
        return rebound;
    }

    private Player getPlayer(Long playerId) {
        Player player = playerRepository.findById(playerId).orElseThrow(NoSuchElementException::new);
        return player;
    }

    private Match getMatch(Long matchId) {
        Match match = matchRepository.findById(matchId).orElseThrow(NoSuchElementException::new);
        return match;
    }
}
