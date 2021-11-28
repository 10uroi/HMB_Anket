package tr.gov.hmb.survey.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AnswerOptionDTO implements Serializable {
    private String key;
    private String value;

    public AnswerOptionDTO() {
    }

    public AnswerOptionDTO(String key, String value) {
        this.key = key;
        this.value = value;
    }

}
