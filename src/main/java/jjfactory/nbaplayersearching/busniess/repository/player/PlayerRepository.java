package jjfactory.nbaplayersearching.busniess.repository.player;

import jjfactory.nbaplayersearching.busniess.domain.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player,Long> {
}
