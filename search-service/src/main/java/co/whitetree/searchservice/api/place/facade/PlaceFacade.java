package co.whitetree.searchservice.api.place.facade;

import co.whitetree.searchservice.api.place.dto.PlaceSearchResponse;
import co.whitetree.searchservice.api.place.mapper.PlaceMapper;
import co.whitetree.searchservice.domain.keyword.service.KeywordAddRetryService;
import co.whitetree.searchservice.domain.place.model.PlaceOrderedSet;
import co.whitetree.searchservice.domain.place.policy.PlaceSortPolicy;
import co.whitetree.searchservice.external.provider.kakao.dto.KakaoSearchResponse;
import co.whitetree.searchservice.external.provider.kakao.service.KakaoPlaceSearchService;
import co.whitetree.searchservice.external.provider.naver.dto.NaverSearchResponse;
import co.whitetree.searchservice.external.provider.naver.service.NaverPlaceSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class PlaceFacade {
    private final KakaoPlaceSearchService kakaoPlaceSearchService;
    private final NaverPlaceSearchService naverPlaceSearchService;
    private final PlaceSortPolicy placeSortPolicy;
    private final PlaceMapper placeMapper;
    private final KeywordAddRetryService keywordAddRetryService;

    @Cacheable(value = "search", key = "#query")
    public List<PlaceSearchResponse> search(String query) {

        KakaoSearchResponse kakaoSearchResponse = kakaoPlaceSearchService.search(query);
        PlaceOrderedSet kakaoPlaces = PlaceOrderedSet.from(kakaoSearchResponse.toPlaceList());

        NaverSearchResponse naverSearchResponse = naverPlaceSearchService.search(query);
        PlaceOrderedSet naverPlaces = PlaceOrderedSet.from(naverSearchResponse.toPlaceList());

        PlaceOrderedSet places = placeSortPolicy.sort(List.of(kakaoPlaces, naverPlaces));

        return places.getPlaces()
                .stream()
                .map(placeMapper::toResponseDto)
                .collect(toList());
    }

    public void addPlaceKeyword(String query) {
        keywordAddRetryService.addKeywordSearchCount(query);
    }
}
