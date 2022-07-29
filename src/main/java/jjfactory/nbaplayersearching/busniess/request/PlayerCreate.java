package jjfactory.nbaplayersearching.busniess.request;

import jjfactory.nbaplayersearching.busniess.domain.player.Country;
import jjfactory.nbaplayersearching.busniess.domain.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PlayerCreate {
    private String name;
    private int age;

    private int height;
    private int weight;
    private String birth;
    private Country country;

    public PlayerCreate(Player player) {
        this.name = player.getName();
        this.age = player.getAge();
        this.height = player.getHeight();
        this.weight = player.getWeight();
        this.birth = player.getBirth();
        this.country = player.getCountry();
    }
}
