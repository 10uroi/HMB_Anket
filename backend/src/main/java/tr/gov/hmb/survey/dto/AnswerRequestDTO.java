package tr.gov.hmb.survey.dto;

import lombok.Data;
import tr.gov.hmb.survey.entity.Question;

import java.util.List;

@Data
public class AnswerRequestDTO {
    private String token;
    private List<Question> questions;
}
