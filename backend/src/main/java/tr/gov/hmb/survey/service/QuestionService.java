package tr.gov.hmb.survey.service;

import tr.gov.hmb.survey.entity.Question;
import tr.gov.hmb.survey.entity.User;
import tr.gov.hmb.survey.enums.Answer;
import tr.gov.hmb.survey.exception.QuestionException;
import tr.gov.hmb.survey.exception.UserException;

import java.util.List;

public interface QuestionService {

    /**
     * Soru eklemek icin. Bir kullaniciya birden fazla soru eklenebilir.
     * @param user kullanicinin kendisi
     * @param question soru
     * @return eklenen soru geri donmektedir.
     * @throws QuestionException
     * @throws UserException
     */
    Question insert(User user, Question question) throws QuestionException, UserException;

    /**
     * ID'si verilen soru aliniyor.
     * @param id -long soru id'si.
     * @return soru donuyor.
     */
    Question getQuestion(Long id);

    /**
     * Bir soruya cevap eklemek icin kullanilan method.
     * @param question soru
     * @param answer cevap
     * @return cevap eklenmis hali ise soru geri donmektedir.
     * @throws QuestionException
     */
    Question answer(Question question, Answer answer) throws QuestionException;

    /**
     * Birden fazla soruya cevap eklemek icin kullanilan method. sorularin icine set edilen cevaplar kayit edilir.
     * @param question sorular
     * @return kayit edilmis sekilde sorular geri gelmektedir.
     * @throws QuestionException
     */
    List<Question> answer(List<Question> question) throws QuestionException;

    /**
     * Bir soruyu soft delete yapmak icin kullanilan method. Hard delete mumkun degildir.
     * @param id -long silinecek sorunun id'si.
     * @return true-basarili. false-basarisiz
     */
    boolean delete(Long id);

}
