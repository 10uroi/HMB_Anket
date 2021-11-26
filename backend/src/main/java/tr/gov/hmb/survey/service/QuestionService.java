package tr.gov.hmb.survey.service;

import tr.gov.hmb.survey.entity.Question;
import tr.gov.hmb.survey.entity.User;
import tr.gov.hmb.survey.enums.Answer;
import tr.gov.hmb.survey.exception.QuestionException;
import tr.gov.hmb.survey.exception.UserException;

public interface QuestionService {

    Question insert(User user, Question question) throws QuestionException, UserException;

    Question getQuestion(Long id);

    Question answer(Question question, Answer answer) throws QuestionException;

    boolean delete(Long id);

}
