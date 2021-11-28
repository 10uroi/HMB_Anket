package tr.gov.hmb.survey.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import tr.gov.hmb.survey.dto.AnswerOptionDTO;
import tr.gov.hmb.survey.enums.Answer;
import tr.gov.hmb.survey.util.AnswerOptionUtils;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "question")
@Where(clause = BaseEntity.SOFT_DELETED_CLAUSE)
@Getter
@Setter
public class Question extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Column(nullable = false)
    private String title;

    private Answer answer;

    @Transient
    private List<AnswerOptionDTO> options;

    public List<AnswerOptionDTO> getOptions() {
        options = AnswerOptionUtils.getOptions();
        return options;
    }
}
