package tr.gov.hmb.survey.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tr.gov.hmb.survey.dto.StatisticsAnswerDTO;
import tr.gov.hmb.survey.dto.StatisticsFillingDTO;
import tr.gov.hmb.survey.entity.Question;
import tr.gov.hmb.survey.entity.User;
import tr.gov.hmb.survey.enums.Answer;
import tr.gov.hmb.survey.exception.QuestionException;
import tr.gov.hmb.survey.exception.UserException;

import java.util.Random;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(JUnit4.class)
class StatisticsServiceTest {

    public static int testUserCount = 20;
    public static int testUserFilling = 0;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private StatisticsService statisticsService;

    @BeforeEach
    void testUserAndQuestionInsert() throws UserException, QuestionException {
        for (int a = 0; a < testUserCount; a++) {
            User user = new User();
            user.setUsername("test_user_" + a);
            user.setToken("115567" + a);
            user = userService.persist(user);

            Question question = new Question();
            question.setTitle("soru " + a + ". test_question");
            int random = new Random().nextInt(3);
            switch (random) {
                case 0:
                    question.setAnswer(Answer.YES);
                    break;
                case 1:
                    question.setAnswer(Answer.NO);
                    break;
            }
            questionService.insert(user, question);
        }

        StatisticsFillingDTO statisticsFillingDTO = statisticsService.getUserFilling();
        testUserCount = Integer.parseInt(statisticsFillingDTO.getUserCount() + "");
        testUserFilling = Integer.parseInt(statisticsFillingDTO.getToFill() + "");
    }

    @Test
    void testGetFilling() {
        StatisticsFillingDTO statisticsFillingDTO = statisticsService.getUserFilling();
        assertEquals(statisticsFillingDTO.getUserCount(), testUserCount);
    }

    @Test
    void testGetAnswer() {
        StatisticsAnswerDTO statisticsAnswerDTO = statisticsService.getAnswer();
        assertEquals(statisticsAnswerDTO.getYes() + statisticsAnswerDTO.getNo(), testUserFilling);
    }

}
