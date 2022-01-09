package co.whitetree.searchservice.domain.keyword.repository;

import co.whitetree.searchservice.domain.keyword.entity.KeywordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KeywordRepository extends JpaRepository<KeywordEntity, Long> {
    Optional<KeywordEntity> findByQuery(String query);
}
