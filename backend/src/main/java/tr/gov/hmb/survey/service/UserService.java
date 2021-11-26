package tr.gov.hmb.survey.service;

import tr.gov.hmb.survey.entity.User;
import tr.gov.hmb.survey.exception.UserException;

public interface UserService {

    User persist(User user) throws UserException;

    User getUser(Long id);

    boolean delete(Long id);
}
