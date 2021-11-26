package tr.gov.hmb.survey.service;

import tr.gov.hmb.survey.dto.StatisticsAnswerDTO;
import tr.gov.hmb.survey.dto.StatisticsFillingDTO;

public interface StatisticsService {

    StatisticsFillingDTO getUserFilling();

    StatisticsAnswerDTO getAnswer();

}
