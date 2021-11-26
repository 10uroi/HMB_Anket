package tr.gov.hmb.survey.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.gov.hmb.survey.entity.Question;
import tr.gov.hmb.survey.entity.User;
import tr.gov.hmb.survey.exception.UserException;
import tr.gov.hmb.survey.repository.QuestionRepository;
import tr.gov.hmb.survey.repository.UserRepository;
import tr.gov.hmb.survey.service.UserService;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public User persist(User user) throws UserException {
        if (user == null)
            throw new UserException(101, "Kullanıcı Bulunamadı!");
        if (user.getToken() == null || user.getUsername() == null)
            throw new UserException(101, "Kullanıcı adı veya token boş olamaz!");

        return  userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public boolean delete(Long id) {
        User user = getUser(id);
        if (user == null) return false;

        for (Question q : user.getQuestions())
            questionRepository.softDeleteById(q.getId());

        userRepository.softDeleteById(id);
        return getUser(id) == null;
    }
}
