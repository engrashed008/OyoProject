package com.sample.play.controller;

import com.sample.play.core.CommonPage;
import com.sample.play.core.CommonResponse;
import com.sample.play.dto.*;
import com.sample.play.resonse.PlayerHistoryResponseDto;
import com.sample.play.resonse.ScoreReponseDto;
import com.sample.play.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("play/score")
public class ScoreController {
    @Autowired
    ScoreService service;

    @PostMapping("/")
    public CommonResponse<ScoreReponseDto> fetch(@RequestBody @Valid ScoreReqParam param) {
        ScoreReponseDto dto = service.getScore(param.getId());
        return CommonResponse.success(dto);
    }

    @PostMapping("/create")
    public CommonResponse create(@RequestBody @Valid CreateReqParam param) {
        service.create(param);
        return CommonResponse.success("OK");
    }

    @DeleteMapping("/delete")
    public CommonResponse delete(@RequestBody @Valid DeleteParam param) {
        service.deleteScore(param.getScoreId());
        return CommonResponse.success("OK");
    }

    @PostMapping("/list")
    public CommonResponse<CommonPage> list(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize,
                                           @RequestBody @Valid ScoreListReqParam param) {
        List<ScoreReponseDto> dto = service.scoreList(param, pageNum, pageSize);
        return CommonResponse.success(CommonPage.restPage(dto));
    }

    @PostMapping("/history")
    public CommonResponse<PlayerHistoryResponseDto> history(@RequestBody @Valid NameReqParam param) {
        PlayerHistoryResponseDto dto = service.playerHistory(param.getName());
        return CommonResponse.success(dto);
    }
}
