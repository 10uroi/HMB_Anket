package tr.gov.hmb.survey.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.gov.hmb.survey.dto.*;
import tr.gov.hmb.survey.entity.User;
import tr.gov.hmb.survey.exception.QuestionException;
import tr.gov.hmb.survey.service.QuestionService;
import tr.gov.hmb.survey.service.StatisticsService;
import tr.gov.hmb.survey.service.UserService;

@RestController
@RequestMapping("/api/statistics/v1")
@RequiredArgsConstructor
public class StatisticsAPI {

    private final StatisticsService statisticsService;

    /**
     * Anket doldurma oranini gosteren endpoint methodu.
     *
     * @return katilimci sayisi ve dolduran sayisi gosterilmektedir.
     */
    @GetMapping(value = "/getFilling")
    public ResponseEntity<StatisticsFillingDTO> getFilling() {
        return ResponseEntity.ok(statisticsService.getUserFilling());
    }

    /**
     * Anketi cevaplarini gosteren endpoint yapisi. Kac kisi evet, kac kisi hayir cevap iletmis bu bilgi gosterilir.
     *
     * @return evet-hayir sayisi
     */
    @GetMapping(value = "/getAnswer")
    public ResponseEntity<StatisticsAnswerDTO> getAnswer() {
        return ResponseEntity.ok(statisticsService.getAnswer());
    }

}
