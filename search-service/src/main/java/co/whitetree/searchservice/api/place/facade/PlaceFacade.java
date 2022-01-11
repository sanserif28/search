package co.whitetree.searchservice.api.place.facade;

import co.whitetree.searchservice.api.place.dto.PlaceSearchResponse;
import co.whitetree.searchservice.api.place.mapper.PlaceMapper;
import co.whitetree.searchservice.domain.keyword.service.KeywordAddRetryService;
import co.whitetree.searchservice.domain.place.model.PlaceOrderedSet;
import co.whitetree.searchservice.domain.place.policy.PlaceSortPolicy;
import co.whitetree.searchservice.domain.place.service.PlaceSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class PlaceFacade {
    private final PlaceSortPolicy placeSortPolicy;
    private final PlaceMapper placeMapper;
    private final KeywordAddRetryService keywordAddRetryService;
    private final PlaceSearchService placeSearchService;

    @Cacheable(value = "search", key = "#query")
    public List<PlaceSearchResponse> search(String query) {

        List<PlaceOrderedSet> placeOrderedSets = placeSearchService.search(query);

        PlaceOrderedSet places = placeSortPolicy.sort(placeOrderedSets);

        return places.getPlaces()
                .stream()
                .map(placeMapper::toResponseDto)
                .collect(toList());
    }

    public void addPlaceKeyword(String query) {
        keywordAddRetryService.addKeywordSearchCount(query);
    }
}
