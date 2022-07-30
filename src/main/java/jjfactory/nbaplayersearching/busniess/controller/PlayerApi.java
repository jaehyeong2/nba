package jjfactory.nbaplayersearching.busniess.controller;

import jjfactory.nbaplayersearching.busniess.service.PlayerService;
import jjfactory.nbaplayersearching.busniess.service.ReboundService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/players")
@RestController
public class PlayerApi {
    private final PlayerService playerService;


}
