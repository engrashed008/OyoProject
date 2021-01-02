package com.sample.play.mapper.playscore;

import com.sample.play.dto.ScoreListReqParam;
import com.sample.play.entity.ScoreEntity;
import com.sample.play.resonse.ScoreReponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScoreMapper {

    ScoreEntity getById(long id);

    List<ScoreEntity> getByName(@Param("player") String player);

    Integer create(ScoreEntity entiry);

    Integer update(@Param("id") long id);

    List<ScoreReponseDto> getScoreList(@Param("playerName") List<String> name,
                                       @Param("beforeTime") String beforeTime,
                                       @Param("afterTime") String afterTime);
}
