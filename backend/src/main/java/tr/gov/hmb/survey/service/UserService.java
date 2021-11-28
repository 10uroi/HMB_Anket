package tr.gov.hmb.survey.service;

import tr.gov.hmb.survey.entity.User;
import tr.gov.hmb.survey.exception.UserException;

import java.util.List;

public interface UserService {

    /**
     * Bir kullaniciyi kayit etmek veya guncellemek icin kullanilan method. Kullanici varsa gunceller yoksa kayit eder.
     * @param user kullanici bilgisi.
     * @return kayit edilen veya guncellenen kullanici geri doner.
     * @throws UserException
     */
    User persist(User user) throws UserException;

    /**
     * id ile kullanici sorgulanan method.
     * @param id -long kullanici id'si.
     * @return kullanici geri doner varsa, yoksa null bilgisi doner.
     */
    User getUser(Long id);

    /**
     * token bilgisi ise kullanici arama methodu.
     * @param token kullanicinin token bilgisi.
     * @return kullanici bilgisi donmektedir. Yoksa null degeri doner.
     */
    User getUser(String token);

    /**
     * Bir kullaniciyi soft delete yapmak icin kullanılan method. Hard delete yapilamamaktadir.
     * @param id silinmesi istenen kullanici id'si.
     * @return true-basarili, false-basarisiz.
     */
    boolean delete(Long id);

    /**
     * Test edilmesi icin kullanicilarin token bilgilerini donen method.
     * @return tum kullanici bilgilerinin token bilgisini donmektedir.
     */
    List<String> getTokens();
}
