package tr.gov.hmb.survey.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.gov.hmb.survey.dto.AnswerRequestDTO;
import tr.gov.hmb.survey.dto.ResponseDTO;
import tr.gov.hmb.survey.dto.UserRequestDTO;
import tr.gov.hmb.survey.entity.User;
import tr.gov.hmb.survey.exception.QuestionException;
import tr.gov.hmb.survey.service.QuestionService;
import tr.gov.hmb.survey.service.UserService;

@RestController
@RequestMapping("/api/question/v1")
@RequiredArgsConstructor
public class QuestionAPI {

    private final UserService userService;

    private final QuestionService questionService;

    /**
     * Bir kullaniciya sorulan tum sorulari cekmek icin kullanilir.
     *
     * @param user kullanicinin token bilgisinin dolu olmasi yeterlidir.
     * @return kullanici bilgisi doner. İcerisinde sorular bulunmaktadir.
     */
    @PostMapping(value = "/getQuestions")
    public ResponseEntity<User> getUserQuestionsFindByToken(@RequestBody UserRequestDTO user) {
        return ResponseEntity.ok(userService.getUser(user.getToken()));
    }

    /**
     * Bir kullanici bir soruyu veya sorulari cevaplarsa bu endpoint uzerinden bilgileri alinir ve db'ye kayit edilir.
     *
     * @param answerRequestDTO sorularin oldugu dto yapisi
     * @return durum bilgisi (basarili veya hata)
     */
    @PostMapping(value = "/setAnswer")
    public ResponseEntity<ResponseDTO> setAnswer(@RequestBody AnswerRequestDTO answerRequestDTO) {
        try {
            questionService.answer(answerRequestDTO.getQuestions());
            return ResponseEntity.ok(new ResponseDTO("OK"));
        } catch (QuestionException e) {
            return ResponseEntity.ok(new ResponseDTO(e));
        }
    }
}
