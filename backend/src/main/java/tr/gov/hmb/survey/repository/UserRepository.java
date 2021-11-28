package tr.gov.hmb.survey.repository;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tr.gov.hmb.survey.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {

    /**
     * Token bilgisi ile kullaniciyi aramak icin kullanilir.
     * @param token kullanicinin token bilgisi verilir.
     * @return kullanici optional olarak alinir.
     */
    Optional<User> findByToken(String token);

}
