package tr.gov.hmb.survey.repository;

import org.springframework.stereotype.Repository;
import tr.gov.hmb.survey.entity.User;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
}
