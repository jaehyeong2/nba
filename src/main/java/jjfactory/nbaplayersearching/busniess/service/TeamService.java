package jjfactory.nbaplayersearching.busniess.service;


import jjfactory.nbaplayersearching.busniess.repository.team.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class TeamService {
    private final TeamRepository teamRepository;
}
