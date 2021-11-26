package tr.gov.hmb.survey.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.gov.hmb.survey.entity.Question;
import tr.gov.hmb.survey.entity.User;
import tr.gov.hmb.survey.enums.Answer;
import tr.gov.hmb.survey.exception.QuestionException;
import tr.gov.hmb.survey.exception.UserException;
import tr.gov.hmb.survey.repository.QuestionRepository;
import tr.gov.hmb.survey.service.QuestionService;
import tr.gov.hmb.survey.service.UserService;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final UserService userService;

    public Question insert(User user, Question question) throws QuestionException, UserException {
        User dbUser = userService.getUser(user.getId());
        if (dbUser == null) throw new QuestionException(101, "Kullanıcı Bulunamadı!");
        if (question == null) throw new QuestionException(102, "Soru Bulunamadı!");

        if (dbUser.getQuestions() == null)
            dbUser.setQuestions(new ArrayList<>());

        question.setUser(dbUser);
        dbUser.getQuestions().add(question);

        dbUser = userService.persist(dbUser);
        return dbUser.getQuestions().get(dbUser.getQuestions().size() - 1);
    }

    public Question getQuestion(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    public Question answer(Question question, Answer answer) throws QuestionException {
        Question dbQuestion = getQuestion(question.getId());
        if (dbQuestion == null) throw new QuestionException(105, "Soru bulunamadı!");

        dbQuestion.setAnswer(answer);
        dbQuestion = questionRepository.save(dbQuestion);
        if (dbQuestion.getId() > 0)
            return dbQuestion;

        throw new QuestionException(106, "Cevap eklenemedi, DB'de problem oluştu!");
    }

    public boolean delete(Long id) {
        questionRepository.softDeleteById(id);
        return getQuestion(id) == null;
    }
}
