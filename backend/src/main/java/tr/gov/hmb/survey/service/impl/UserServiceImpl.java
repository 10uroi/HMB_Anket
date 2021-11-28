package tr.gov.hmb.survey.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.gov.hmb.survey.dto.UserRequestDTO;
import tr.gov.hmb.survey.entity.Question;
import tr.gov.hmb.survey.entity.User;
import tr.gov.hmb.survey.exception.UserException;
import tr.gov.hmb.survey.repository.QuestionRepository;
import tr.gov.hmb.survey.repository.UserRepository;
import tr.gov.hmb.survey.service.UserService;
import tr.gov.hmb.survey.util.LocaleMessage;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final LocaleMessage localeMessage;

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public User persist(User user) throws UserException {
        if (user == null)
            throw new UserException(111, localeMessage.getMessage("user.not.found"));
        if (user.getToken() == null || user.getUsername() == null)
            throw new UserException(112, localeMessage.getMessage("user.username.or.token.empty"));

        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUser(String token) {
        return userRepository.findByToken(token).orElse(null);
    }

    public boolean delete(Long id) {
        User user = getUser(id);
        if (user == null) return false;

        for (Question q : user.getQuestions())
            questionRepository.softDeleteById(q.getId());

        userRepository.softDeleteById(id);
        return getUser(id) == null;
    }

    public List<String> getTokens() {
        List<String> tokens = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            tokens.add(user.getToken());
        }
        return tokens;
    }
}
