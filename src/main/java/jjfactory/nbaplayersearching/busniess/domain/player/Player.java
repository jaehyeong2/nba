package jjfactory.nbaplayersearching.busniess.domain.player;

import jjfactory.nbaplayersearching.busniess.domain.BaseTimeEntity;
import jjfactory.nbaplayersearching.busniess.request.PlayerCreate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Player extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "team_id")
//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    private Team team;

    private String name;
    private int age;
    private int height;
    private int weight;
    private String birth;

    private int uniformNumber;

    @Enumerated(EnumType.STRING)
    private Position position;

    @Builder
    public Player(Team team, String name, int age, int height, int weight, String birth, int uniformNumber, Position position) {
        this.team = team;
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.birth = birth;
        this.uniformNumber = uniformNumber;
        this.position = position;
    }

    public static Player create(PlayerCreate dto, Team team){
        return Player.builder()
                .name(dto.getName())
                .age(dto.getAge())
                .team(team)
                .height(dto.getHeight())
                .weight(dto.getWeight())
                .build();
    }
}