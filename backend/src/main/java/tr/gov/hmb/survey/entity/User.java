package tr.gov.hmb.survey.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Where(clause = BaseEntity.SOFT_DELETED_CLAUSE)
@Getter
@Setter
@ToString
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String token;

    @OneToMany(targetEntity = Question.class, mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Question> questions;

}
