package tr.gov.hmb.survey.service;

import org.checkerframework.checker.units.qual.A;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tr.gov.hmb.survey.entity.Question;
import tr.gov.hmb.survey.entity.User;
import tr.gov.hmb.survey.enums.Answer;
import tr.gov.hmb.survey.exception.QuestionException;
import tr.gov.hmb.survey.exception.UserException;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(JUnit4.class)
class QuestionServiceTest {

    public static Long questionID = 1L;
    public static Long userID = 1L;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @BeforeEach
    void init() throws UserException, QuestionException {
        Question question = new Question();
        question.setId(questionID);
        question.setTitle("soru 2. bilmemmi");

        User user = new User();
        user.setUsername("deneme");
        user.setToken("115566");
        user = userService.persist(user);
        userID = user.getId();

        question = questionService.insert(user, question);
        questionID = question.getId();
        assertNotNull(question);
    }

    @Test
    void testGetQuestion() {
        assertNotNull(questionService.getQuestion(questionID));
    }

    @Test
    void testSetAnswer() throws QuestionException {
        Question question = questionService.getQuestion(questionID);
        Answer answer = Answer.YES;
        assertNotNull(questionService.answer(question, answer));
    }

    @Test
    void testDeleteQuestion() {
        Question question = questionService.getQuestion(questionID);
        assertFalse(questionService.delete(question.getId()));
    }

}
