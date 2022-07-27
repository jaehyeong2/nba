package jjfactory.nbaplayersearching.busniess.response;

import jjfactory.nbaplayersearching.busniess.domain.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PlayerDetailRes {
    private String name;
    private int age;

    private int height;
    private int weight;
    private String birth;

    public PlayerDetailRes(Player player) {
        this.name = player.getName();
        this.age = player.getAge();
        this.height = player.getHeight();
        this.weight = player.getWeight();
        this.birth = player.getBirth();
    }
}
