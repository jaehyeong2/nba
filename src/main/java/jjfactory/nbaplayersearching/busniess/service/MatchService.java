package jjfactory.nbaplayersearching.busniess.service;

import jjfactory.nbaplayersearching.busniess.domain.match.Match;
import jjfactory.nbaplayersearching.busniess.domain.team.Team;
import jjfactory.nbaplayersearching.busniess.repository.match.MatchQueryRepository;
import jjfactory.nbaplayersearching.busniess.repository.match.MatchRepository;
import jjfactory.nbaplayersearching.busniess.repository.team.TeamRepository;
import jjfactory.nbaplayersearching.busniess.request.MatchCreate;
import jjfactory.nbaplayersearching.busniess.response.MatchRes;
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
public class MatchService {
    private final MatchRepository matchRepository;
    private final MatchQueryRepository matchQueryRepository;
    private final TeamRepository teamRepository;

    @Transactional(readOnly = true)
    public MatchRes findMatchInfo(Long matchId){
        Match match = getMatch(matchId);
        return new MatchRes(match);
    }

    @Transactional(readOnly = true)
    public Page<MatchRes> findMatches(int page,int size){
        Pageable pageable = PageRequest.of(page,size);
        return matchQueryRepository.findMatches(pageable);
    }

    public String create(MatchCreate dto,Long homeId, Long awayId){
        Team home = getTeam(homeId);
        Team away = getTeam(awayId);

        Match.create(dto,home,away);
        return "Y";
    }

    public String delete(Long matchId){
        Match match = getMatch(matchId);
        match.delete();
        return "Y";
    }

    public String update(Long matchId){
        Match match = getMatch(matchId);
        return "Y";
    }

    private Match getMatch(Long matchId) {
        Match match = matchRepository.findById(matchId).orElseThrow(NoSuchElementException::new);
        return match;
    }

    private Team getTeam(Long awayId) {
        Team away = teamRepository.findById(awayId).orElseThrow(NoSuchElementException::new);
        return away;
    }
}
