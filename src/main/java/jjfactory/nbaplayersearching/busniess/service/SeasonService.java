package jjfactory.nbaplayersearching.busniess.service;

import jjfactory.nbaplayersearching.busniess.domain.match.Season;
import jjfactory.nbaplayersearching.busniess.repository.season.SeasonRepository;
import jjfactory.nbaplayersearching.busniess.request.SeasonCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional
@Service
public class SeasonService {
    private final SeasonRepository seasonRepository;

    public String create(SeasonCreate dto){
        Season season = Season.create(dto);
        seasonRepository.save(season);
        return "Y";
    }

    public String delete(Long seasonId){
        Season season = seasonRepository.findById(seasonId).orElseThrow(NoSuchElementException::new);
        season.delete();
        return "Y";
    }
}
