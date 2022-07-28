package jjfactory.nbaplayersearching.busniess.response;

import com.querydsl.core.annotations.QueryProjection;
import jjfactory.nbaplayersearching.busniess.domain.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PlayerRes {
    private String name;
    private int age;

    private int height;
    private int weight;
    private String birth;

    public PlayerRes(Player player) {
        this.name = player.getName();
        this.age = player.getAge();
        this.height = player.getHeight();
        this.weight = player.getWeight();
        this.birth = player.getBirth();
    }

    @QueryProjection
    public PlayerRes(String name, int age, int height, int weight, String birth) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.birth = birth;
    }
}
