package jjfactory.nbaplayersearching.busniess.controller;

import jjfactory.nbaplayersearching.busniess.request.MatchCreate;
import jjfactory.nbaplayersearching.busniess.response.ApiResponse;
import jjfactory.nbaplayersearching.busniess.response.MatchRes;
import jjfactory.nbaplayersearching.busniess.response.PagingResponse;
import jjfactory.nbaplayersearching.busniess.service.MatchService;
import jjfactory.nbaplayersearching.busniess.service.ReboundService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/matches")
@RestController
public class MatchApi {
    private final MatchService matchService;

    @GetMapping("/{matchId}")
    public ApiResponse<MatchRes> findMatch(@PathVariable Long matchId){
        return new ApiResponse<>(matchService.findMatchInfo(matchId));
    }

    @GetMapping("")
    public PagingResponse<Page<MatchRes>> findMatches(@RequestParam(required = false, defaultValue = "1") int page,
                                            @RequestParam(required = false, defaultValue = "10") int size){
        return new PagingResponse(matchService.findMatches(page,size));
    }

    @PostMapping("")
    public ApiResponse<String> createMatch(@RequestBody MatchCreate dto){
        return new ApiResponse<>(matchService.create(dto));
    }

    @DeleteMapping("/{matchId}")
    public ApiResponse<String> deleteMatch(@PathVariable Long matchId){
        return new ApiResponse<>(matchService.delete(matchId));
    }


}
