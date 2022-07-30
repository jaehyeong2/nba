package jjfactory.nbaplayersearching.busniess.controller;

import jjfactory.nbaplayersearching.busniess.service.ReboundService;
import jjfactory.nbaplayersearching.busniess.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/teams")
@RestController
public class TeamApi {
    private final TeamService teamService;


}
