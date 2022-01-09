package co.whitetree.searchservice.api.place.facade;

import co.whitetree.searchservice.api.place.dto.PlaceSearchResponse;
import co.whitetree.searchservice.api.place.mapper.PlaceMapper;
import co.whitetree.searchservice.domain.keyword.service.KeywordAddRetryService;
import co.whitetree.searchservice.domain.place.model.PlaceOrderedSet;
import co.whitetree.searchservice.domain.place.service.PlaceSortService;
import co.whitetree.searchservice.external.kakao.dto.KakaoSearchResponse;
import co.whitetree.searchservice.external.kakao.service.KakaoPlaceSearchService;
import co.whitetree.searchservice.external.naver.dto.NaverSearchResponse;
import co.whitetree.searchservice.external.naver.service.NaverPlaceSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlaceFacade {
    private final KakaoPlaceSearchService kakaoPlaceSearchService;
    private final NaverPlaceSearchService naverPlaceSearchService;
    private final PlaceSortService placeSortService;
    private final PlaceMapper placeMapper;
    private final KeywordAddRetryService keywordAddRetryService;

    public List<PlaceSearchResponse> search(String query) {

        KakaoSearchResponse kakaoSearchResponse = kakaoPlaceSearchService.search(query);
        PlaceOrderedSet kakaoPlaces = PlaceOrderedSet.from(kakaoSearchResponse.toPlaceList());

        NaverSearchResponse naverSearchResponse = naverPlaceSearchService.search(query);
        PlaceOrderedSet naverPlaces = PlaceOrderedSet.from(naverSearchResponse.toPlaceList());

        PlaceOrderedSet places = placeSortService.sort(kakaoPlaces, naverPlaces);

        keywordAddRetryService.addKeywordSearchCount(query);

        return places.getPlaces()
                .stream()
                .map(placeMapper::toResponseDto)
                .collect(toList());
    }
}
