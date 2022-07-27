package jjfactory.nbaplayersearching.busniess.repository;

import jjfactory.nbaplayersearching.busniess.domain.player.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match,Long> {
}
