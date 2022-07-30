package jjfactory.nbaplayersearching.busniess.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SeasonCreate {
    private String startYear;
    private String endYear;

    @Builder
    public SeasonCreate(String startYear, String endYear) {
        this.startYear = startYear;
        this.endYear = endYear;
    }
}
