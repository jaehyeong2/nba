package jjfactory.nbaplayersearching.busniess.repository.season;

import jjfactory.nbaplayersearching.busniess.domain.match.Season;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonRepository extends JpaRepository<Season,Long> {
}
