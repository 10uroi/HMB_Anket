package tr.gov.hmb.survey;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import tr.gov.hmb.survey.entity.Question;
import tr.gov.hmb.survey.entity.User;
import tr.gov.hmb.survey.enums.Answer;
import tr.gov.hmb.survey.exception.QuestionException;
import tr.gov.hmb.survey.exception.UserException;
import tr.gov.hmb.survey.service.QuestionService;
import tr.gov.hmb.survey.service.UserService;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class DumyDataInitializer {

    private final UserService userService;
    private final QuestionService questionService;
    private final static int dumyDataCount = 10;

    /**
     * Uygulama başlangıcta test edilebilmesi icin dumy datalar ekleniyor.
     * @throws UserException
     * @throws QuestionException
     */
    @EventListener(ApplicationStartedEvent.class)
    private void setDumyDatas() throws UserException, QuestionException {
        for (int a = 0; a < dumyDataCount; a++) {
            User user = new User();
            user.setUsername("test_username_" + a);
            user.setToken("123456-abcd-xyzw-987654-" + a);
            user = userService.persist(user);

            Question question = new Question();
            question.setTitle("Çalıştığınız yerden memnun musunuz?");
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
    }

}
