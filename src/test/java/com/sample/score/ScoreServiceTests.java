package com.sample.score;

import com.sample.play.core.ErrorStatus;
import com.sample.play.core.ObjectMapperHelper;
import com.sample.play.dto.CreateReqParam;
import com.sample.play.dto.ScoreListReqParam;
import com.sample.play.entity.ScoreEntity;
import com.sample.play.mapper.playscore.ScoreMapper;
import com.sample.play.resonse.PlayerHistoryResponseDto;
import com.sample.play.resonse.ScoreReponseDto;
import com.sample.play.service.ScoreService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@Import({ScoreService.class})
@SpringBootTest(classes = ScoreServiceTests.class)
public class ScoreServiceTests {

    @Autowired
    private ScoreService service;

    @MockBean
    private ScoreMapper mapper;

    @Test
    public void getScoreTest_Ok() {
        ScoreEntity entity = new ScoreEntity();
        entity.setScoreId(1L);
        entity.setScore(500);
        entity.setName("Rashed");
        entity.setCreatedAt("2021-01-02");
        when(mapper.getById(1)).thenReturn(entity);

        assertEquals(entity.getScore(), service.getScore(1).getScore());
    }

    @Test
    public void getScoreTest_NG() {
        ScoreEntity entity = new ScoreEntity();
        entity.setScoreId(1L);
        entity.setScore(500);
        entity.setName("Rashed");
        entity.setCreatedAt("2021-01-02 15:00:00");
        when(mapper.getById(1)).thenReturn(entity);

        try {
            service.getScore(2);
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), ErrorStatus.Err_DATA.getErrorMessage());
        }
    }

    @Test
    public void createScoreTest_Ok() {
        int count = 1;
        CreateReqParam param = new CreateReqParam();
        param.setName("Oyo Player");
        param.setScore(1500);
        param.setCreatedAt("2021-01-02 14:00:00");
        ScoreEntity entity = ObjectMapperHelper.map(param, ScoreEntity.class);
        List<ScoreEntity> list = new ArrayList<>();
        list.add(entity);
        when(mapper.create(entity)).thenReturn(count);
        when(mapper.getByName("Oyo Player")).thenReturn(list);

        service.create(param);
        List<ScoreEntity> entities = mapper.getByName("Oyo Player");
        assertEquals(entities.size(), list.size());
        assertEquals(entities.get(0).getName(), param.getName());
    }

    @Test
    public void deleteScoreTest_Ok() {
        int count = 1;
        CreateReqParam param = new CreateReqParam();
        param.setName("Old Player");
        param.setScore(1500);
        param.setCreatedAt("2021-01-02 10:00:00");
        ScoreEntity entity = ObjectMapperHelper.map(param, ScoreEntity.class);
        entity.setScoreId(99999L);
        when(mapper.create(entity)).thenReturn(count);
        when(mapper.update(entity.getScoreId())).thenReturn(count);

        service.deleteScore(entity.getScoreId());
        assertEquals(mapper.getById(entity.getScoreId()), null);
    }

    @Test
    public void deleteScoreTest_NG() {
        int count = 1;
        CreateReqParam param = new CreateReqParam();
        param.setName("Old Player");
        param.setScore(1100);
        param.setCreatedAt("2021-01-02 12:0:00");
        ScoreEntity entity = ObjectMapperHelper.map(param, ScoreEntity.class);
        entity.setScoreId(99998L);
        when(mapper.create(entity)).thenReturn(count);
        when(mapper.update(entity.getScoreId())).thenReturn(count);

        try {
            service.deleteScore(99999L);
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), ErrorStatus.Err_DELETE.getErrorMessage());
        }
    }

    @Test
    public void playerHistoryTest_Ok() {
        String name = "Player1";
        List<ScoreEntity> listEntity = new ArrayList<>();
        listEntity.add(createScore(name, 1500, "2021-01-02 10:00:00"));
        listEntity.add(createScore(name, 1100, "2021-01-02 10:00:00"));
        listEntity.add(createScore(name, 500, "2021-01-02 10:00:00"));

        when(mapper.getByName(name)).thenReturn(listEntity);

        PlayerHistoryResponseDto dto = service.playerHistory(name);
        double avg = listEntity.stream().collect(Collectors.averagingInt(x -> x.getScore()));
        assertEquals(dto.getName(), name);
        assertEquals(dto.getAverageScore().intValue(), (int) avg);
        assertEquals(dto.getTopScore(), listEntity.stream().max(Comparator.comparing(
                ScoreEntity::getScore)).orElse(null).getScore());
    }

    @Test
    public void playerHistoryTest_NG() {
        String name = "Player1";
        List<ScoreEntity> listEntity = new ArrayList<>();
        when(mapper.getByName(name)).thenReturn(listEntity);
        try {
            service.playerHistory(name);
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), ErrorStatus.Err_DATA.getErrorMessage());
        }
    }

    @Test
    public void scoreListTest_NG() {
        List<String> players = new ArrayList<String>();
        players.add("Player1");
        players.add("Player2");
        players.add("Player3");

        List<ScoreEntity> listEntity = new ArrayList<>();
        listEntity.add(createScore(players.get(0), 1500, "2021-01-02 10:00:00"));
        listEntity.add(createScore(players.get(1), 1100, "2021-01-02 10:00:00"));
        listEntity.add(createScore(players.get(2), 500, "2020-01-02 10:00:00"));

        ScoreListReqParam param = new ScoreListReqParam();
        param.setNames(players);
        param.setBeforeTime("2021-12-12");
        param.setAfterTime("2020-01-01");

        ScoreReponseDto scoreDto = new ScoreReponseDto();
        scoreDto.setName(listEntity.get(0).getName());
        scoreDto.setScore(listEntity.get(0).getScore());
        scoreDto.setScoreId(99997L);

        ScoreReponseDto scoreDto1 = new ScoreReponseDto();
        scoreDto1.setName(listEntity.get(1).getName());
        scoreDto1.setScore(listEntity.get(1).getScore());
        scoreDto1.setScoreId(99998L);

        ScoreReponseDto scoreDto2 = new ScoreReponseDto();
        scoreDto2.setName(listEntity.get(2).getName());
        scoreDto2.setScore(listEntity.get(2).getScore());
        scoreDto2.setScoreId(99999L);

        List<ScoreReponseDto> listDto = new ArrayList<>();
        listDto.add(scoreDto);
        listDto.add(scoreDto1);
        listDto.add(scoreDto2);

        when(mapper.getScoreList(players, "2021-12-12", "2020-01-01")).thenReturn(listDto);
        try {
            List<ScoreReponseDto> scoreList = service.scoreList(param, 1, 10);
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), ErrorStatus.Err_DATA.getErrorMessage());
        }
    }

    private ScoreEntity createScore(String name, int score, String date) {
        int count = 1;
        CreateReqParam param = new CreateReqParam();
        param.setName(name);
        param.setScore(score);
        param.setCreatedAt(date);
        ScoreEntity entity = ObjectMapperHelper.map(param, ScoreEntity.class);
        when(mapper.create(entity)).thenReturn(count);
        return entity;
    }
}

