package co.whitetree.searchservice.api.keyword.facade;

import co.whitetree.searchservice.api.keyword.dto.KeywordResponse;
import co.whitetree.searchservice.api.keyword.mapper.KeywordMapper;
import co.whitetree.searchservice.domain.keyword.service.KeywordFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class KeywordFacade {
    private final KeywordFindService keywordFindService;
    private final KeywordMapper keywordMapper;

    public List<KeywordResponse> findPopularKeywords() {
        return keywordFindService.findTop10OrderBySearchCount()
                .stream()
                .map(keywordMapper::toResponseDto)
                .collect(toList());
    }
}
