package co.whitetree.searchservice.domain.keyword.repository;

import co.whitetree.searchservice.domain.keyword.entity.KeywordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;

public interface KeywordRepository extends JpaRepository<KeywordEntity, Long> {

    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE) // 비관적락 중 XLock
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")}) // timeout 3초
    Optional<KeywordEntity> findWithLockByQuery(String query);

    Optional<KeywordEntity> findByQuery(String query);
}
