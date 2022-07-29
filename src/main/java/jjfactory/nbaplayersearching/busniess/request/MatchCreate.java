package jjfactory.nbaplayersearching.busniess.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class MatchCreate {
    private String location;
    private LocalDateTime matchTime;
    private int firstQtScore;
    private int secondQtScore;
    private int thirdQtScore;
    private int fourthQtScore;
}
