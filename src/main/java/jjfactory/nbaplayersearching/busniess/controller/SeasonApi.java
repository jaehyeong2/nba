package jjfactory.nbaplayersearching.busniess.controller;

import jjfactory.nbaplayersearching.busniess.domain.match.Season;
import jjfactory.nbaplayersearching.busniess.service.ReboundService;
import jjfactory.nbaplayersearching.busniess.service.SeasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/season")
@RestController
public class SeasonApi {
    private final SeasonService seasonService;


}
