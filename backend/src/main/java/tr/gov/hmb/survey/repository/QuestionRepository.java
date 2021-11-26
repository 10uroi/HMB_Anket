package tr.gov.hmb.survey.repository;

import org.springframework.stereotype.Repository;
import tr.gov.hmb.survey.entity.Question;

@Repository
public interface QuestionRepository extends BaseRepository<Question, Long> {
}
