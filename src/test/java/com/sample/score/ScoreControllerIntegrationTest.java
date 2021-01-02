package com.sample.score;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.play.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ScoreApplicationTest.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ScoreControllerIntegrationTest {
    private static final String PORT = "8080";

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void testFetchContoller() {
        ScoreReqParam param = new ScoreReqParam();
        param.setId(1L);

        ResponseEntity<String> responseEntity = this.testRestTemplate
                .postForEntity(createUrl("/play/score/"), param, String.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testCreateContoller() throws Exception {
        CreateReqParam param = new CreateReqParam();
        param.setName("Plyer100");
        param.setScore(100);
        param.setCreatedAt("2021-01-02 12:00:00");

        ResponseEntity<String> responseEntity = this.testRestTemplate
                .postForEntity(createUrl("/play/score/create"), param, String.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void scoreListControllerTest() {
        ScoreListReqParam param = new ScoreListReqParam();
        List<String> names = new ArrayList<>();
        names.add("Rashed");
        param.setNames(names);
        param.setBeforeTime("2021-12-12");
        param.setAfterTime("2020-01-01");

        ResponseEntity<String> responseEntity = this.testRestTemplate
                .postForEntity(createUrl("play/score/list?pageNum=1&pageSize=10"), param, String.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testDeleteContoller() {
        DeleteParam param = new DeleteParam();
        param.setScoreId(100L);

        this.testRestTemplate
                .delete(createUrl("/play/score/delete"), param, String.class);
    }

    @Test
    public void historyControllerTest() {
        NameReqParam param = new NameReqParam();
        param.setName("Rashed");

        ResponseEntity<String> responseEntity = this.testRestTemplate
                .postForEntity(createUrl("play/score/history"), param, String.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    private String createUrl(String uri) {
        return "http://localhost:" + PORT + uri;
    }
}
