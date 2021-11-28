package tr.gov.hmb.survey.service;

import tr.gov.hmb.survey.dto.StatisticsAnswerDTO;
import tr.gov.hmb.survey.dto.StatisticsFillingDTO;

public interface StatisticsService {

    /**
     * Katilimci sayisini ve anket doldurma bilgisini gosteren method.
     * @return katilimci sayisi ve anket dolduranlarin sayisi
     */
    StatisticsFillingDTO getUserFilling();

    /**
     * Anket cevaplarini donen method. Kac evet, kac hayir bilgisi yer almaktadir.
     * @return evet, hayir seceneklerinin sayisi.
     */
    StatisticsAnswerDTO getAnswer();

}
