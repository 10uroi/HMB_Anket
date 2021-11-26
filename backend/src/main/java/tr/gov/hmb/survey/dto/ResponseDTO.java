package tr.gov.hmb.survey.dto;

import lombok.Data;
import tr.gov.hmb.survey.exception.QuestionException;

@Data
public class ResponseDTO {

    private String message;
    private QuestionException error;

    public ResponseDTO(String message) {
        this.message = message;
    }

    public ResponseDTO(QuestionException error) {
        this.error = error;
    }
}
