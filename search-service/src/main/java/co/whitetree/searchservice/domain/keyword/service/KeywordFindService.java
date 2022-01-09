package co.whitetree.searchservice.domain.keyword.service;

import co.whitetree.searchservice.domain.keyword.entity.KeywordEntity;
import co.whitetree.searchservice.domain.keyword.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class KeywordFindService {
    private final KeywordRepository keywordRepository;

    public List<KeywordEntity> findTop10OrderBySearchCount() {
        return keywordRepository.findTop10OrderBySearchCount();
    }
}
