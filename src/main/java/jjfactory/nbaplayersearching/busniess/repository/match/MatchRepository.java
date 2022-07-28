package jjfactory.nbaplayersearching.busniess.repository.match;

import jjfactory.nbaplayersearching.busniess.domain.match.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match,Long> {
}
