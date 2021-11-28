package tr.gov.hmb.survey.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import tr.gov.hmb.survey.entity.BaseEntity;

@NoRepositoryBean
public interface BaseRepository<T, I> extends CrudRepository<T, I> {

    Logger logger = LoggerFactory.getLogger(BaseRepository.class);

    //Soft delete islemi icin bir adet custom repository eklendi. Bu sayede hard delete yasaklandi.
    default void softDelete(T entity) {
        if (entity != null) {
            ((BaseEntity) entity).setDeleted(true);
            save(entity);
        }
    }

    default void softDeleteById(I id) {
        this.softDelete(findById(id).orElse(null));
    }

    String HARD_DELETED_NON = "Hard delete işlemine izin verilmemektedir!";

    @Override
    default void deleteById(I id) {
        logger.error(HARD_DELETED_NON);
    }

    @Override
    default void delete(T entity) {
        logger.error(HARD_DELETED_NON);
    }

    @Override
    default void deleteAllById(Iterable<? extends I> ids) {
        logger.error(HARD_DELETED_NON);
    }

    @Override
    default void deleteAll(Iterable<? extends T> entities) {
        logger.error(HARD_DELETED_NON);
    }

    @Override
    default void deleteAll() {
        logger.error(HARD_DELETED_NON);
    }

}
