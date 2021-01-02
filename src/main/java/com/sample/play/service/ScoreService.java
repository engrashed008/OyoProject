package com.sample.play.service;

import com.github.pagehelper.PageHelper;
import com.sample.play.core.Constants;
import com.sample.play.core.ErrorStatus;
import com.sample.play.core.ObjectMapperHelper;
import com.sample.play.core.ServiceException;
import com.sample.play.dto.CreateReqParam;
import com.sample.play.dto.ScoreListReqParam;
import com.sample.play.entity.ScoreEntity;
import com.sample.play.mapper.playscore.ScoreMapper;
import com.sample.play.resonse.PlayerHistoryResponseDto;
import com.sample.play.resonse.ScoreReponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScoreService {


    private final ScoreMapper mapper;

    public ScoreReponseDto getScore(long id) {
        ScoreEntity entity = mapper.getById(id);
        if (ObjectUtils.isEmpty(entity)) {
            throw new ServiceException(ErrorStatus.Err_DATA);
        }
        // Converting Entity to Response DTO
        ScoreReponseDto dto = ObjectMapperHelper.map(entity, ScoreReponseDto.class);
        return dto;
    }

    @Transactional(rollbackFor = {Exception.class})
    public void create(CreateReqParam param) {
        // Converting Request DTO to Entity
        ScoreEntity entity = ObjectMapperHelper.map(param, ScoreEntity.class);
        Integer count = mapper.create(entity);
        if (count < 1) {
            throw new ServiceException(ErrorStatus.Err_CREATE);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public void deleteScore(long id) {
        Integer count = mapper.update(id);
        if (count < 1) {
            throw new ServiceException(ErrorStatus.Err_DELETE);
        }
    }

    public List<ScoreReponseDto> scoreList(ScoreListReqParam param, int pageNum, int pageSize) {
        // Preparing upper time limit and starting time limit
        if (!StringUtils.isEmpty(param.getAfterTime())) {
            param.setAfterTime(param.getAfterTime() + Constants.upperTimeLimit);
        }
        if (!StringUtils.isEmpty(param.getBeforeTime())) {
            param.setBeforeTime(param.getBeforeTime() + Constants.lowerTimeLimit);
        }

        PageHelper.startPage(pageNum, pageSize);
        List<ScoreReponseDto> dto = mapper.getScoreList(param.getNames(), param.getBeforeTime(), param.getAfterTime());
        if (ObjectUtils.isEmpty(dto)) {
            throw new ServiceException(ErrorStatus.Err_DATA);
        }
        return dto;
    }

    public PlayerHistoryResponseDto playerHistory(String name) {
        List<ScoreEntity> scoreList = mapper.getByName(name);
        if (CollectionUtils.isEmpty(scoreList)) {
            throw new ServiceException(ErrorStatus.Err_DATA);
        }
        ScoreEntity topScoreTime = scoreList.stream().max(Comparator.comparing(
                ScoreEntity::getScore)).orElse(null);

        ScoreEntity lowScoreTime = scoreList.stream().min(Comparator.comparing(
                ScoreEntity::getScore)).orElse(null);

        double averageScore = scoreList.stream().collect(Collectors.averagingInt(x -> x.getScore()));
        List<Integer> list = scoreList.stream().map(ScoreEntity::getScore).collect(Collectors.toList());

        // Preparing response DTO
        PlayerHistoryResponseDto dto = new PlayerHistoryResponseDto();
        dto.setName(name);
        dto.setTopScore(topScoreTime.getScore());
        dto.setTopScoreTime(topScoreTime.getCreatedAt());
        dto.setLowScore(lowScoreTime.getScore());
        dto.setLowScoreTime(lowScoreTime.getCreatedAt());
        dto.setAverageScore((int) averageScore);
        dto.setAllScores(list);
        return dto;
    }
}
