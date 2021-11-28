package tr.gov.hmb.survey.util;

import tr.gov.hmb.survey.dto.AnswerOptionDTO;
import tr.gov.hmb.survey.enums.Answer;

import java.util.ArrayList;
import java.util.List;

public class AnswerOptionUtils {

    public static List<AnswerOptionDTO> getOptions() {
        List<AnswerOptionDTO> answerOptionDTOS = new ArrayList<>();
        for (Answer answer : Answer.values()) {
            answerOptionDTOS.add(new AnswerOptionDTO(answer.name(), answer.getTitle(answer)));
        }
        return answerOptionDTOS;
    }

}
