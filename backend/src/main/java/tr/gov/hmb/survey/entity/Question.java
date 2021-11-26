package tr.gov.hmb.survey.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import tr.gov.hmb.survey.enums.Answer;

import javax.persistence.*;

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
    private User user;

    @Column(nullable = false)
    private String title;

    private Answer answer;

}
