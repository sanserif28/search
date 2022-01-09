package co.whitetree.searchservice.domain.keyword.service;

import co.whitetree.searchservice.domain.keyword.entity.KeywordEntity;
import co.whitetree.searchservice.domain.keyword.repository.query.KeywordQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class KeywordFindService {
    private final KeywordQueryRepository keywordQueryRepository;

    public List<KeywordEntity> findTop10OrderBySearchCount() {
        return keywordQueryRepository.findKeywordOrderBySearchCountAndLimit10();
    }
}
