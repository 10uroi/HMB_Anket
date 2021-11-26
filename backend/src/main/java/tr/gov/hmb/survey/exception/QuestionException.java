package tr.gov.hmb.survey.exception;

import lombok.Data;

@Data
public class QuestionException extends Exception {

    private final int code;
    private final String message;

    public QuestionException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
