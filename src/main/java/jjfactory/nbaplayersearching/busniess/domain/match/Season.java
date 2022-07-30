package jjfactory.nbaplayersearching.busniess.domain.match;

import jjfactory.nbaplayersearching.busniess.domain.BaseTimeEntity;
import jjfactory.nbaplayersearching.busniess.request.SeasonCreate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class Season extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String startYear;
    private String endYear;

    @Builder
    public Season(String startYear, String endYear) {
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public static Season create(SeasonCreate dto) {
        return Season.builder()
                .startYear(dto.getStartYear())
                .endYear(dto.getEndYear())
                .build();
    }
}
