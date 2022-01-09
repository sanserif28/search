package co.whitetree.searchservice.domain.keyword.service;

import co.whitetree.searchservice.domain.keyword.entity.KeywordEntity;
import co.whitetree.searchservice.domain.keyword.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class KeywordAddService {
    private final KeywordRepository keywordRepository;

    public void add(String query) {
        keywordRepository.findByQuery(query)
                .ifPresentOrElse(
                        KeywordEntity::incrementSearchCount,
                        () -> {
                            KeywordEntity keywordEntity = KeywordEntity.builder()
                                    .query(query)
                                    .searchCount(1L)
                                    .build();
                            keywordRepository.save(keywordEntity);
                        });
    }
}
