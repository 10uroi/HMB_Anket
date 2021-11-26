package tr.gov.hmb.survey.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.gov.hmb.survey.dto.StatisticsAnswerDTO;
import tr.gov.hmb.survey.dto.StatisticsFillingDTO;
import tr.gov.hmb.survey.enums.Answer;
import tr.gov.hmb.survey.repository.UserRepository;
import tr.gov.hmb.survey.service.StatisticsService;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final UserRepository userRepository;

    public StatisticsFillingDTO getUserFilling() {
        StatisticsFillingDTO statisticsFillingDTO = new StatisticsFillingDTO();
        statisticsFillingDTO.setUserCount(userRepository.count());
        userRepository.findAll().forEach(user -> {
            user.getQuestions().forEach(question -> {
                if (question.getAnswer() != null)
                    statisticsFillingDTO.setToFill(statisticsFillingDTO.getToFill() + 1);
            });
        });
        return statisticsFillingDTO;
    }

    public StatisticsAnswerDTO getAnswer() {
        StatisticsAnswerDTO statisticsAnswerDTO = new StatisticsAnswerDTO();
        userRepository.findAll().forEach(user -> {
            user.getQuestions().forEach(question -> {
                if (question.getAnswer() != null) {
                    if (question.getAnswer() == Answer.YES)
                        statisticsAnswerDTO.setYes(statisticsAnswerDTO.getYes() + 1);
                    else
                        statisticsAnswerDTO.setNo(statisticsAnswerDTO.getNo() + 1);
                }
            });
        });
        return statisticsAnswerDTO;
    }


}
