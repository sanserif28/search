package co.whitetree.searchservice.api.place.facade;

import co.whitetree.searchservice.api.place.dto.PlaceSearchResponse;
import co.whitetree.searchservice.api.place.mapper.PlaceMapper;
import co.whitetree.searchservice.domain.common.external.kakao.dto.KakaoSearchResponse;
import co.whitetree.searchservice.domain.common.external.kakao.service.KakaoPlaceSearchService;
import co.whitetree.searchservice.domain.common.external.naver.dto.NaverSearchResponse;
import co.whitetree.searchservice.domain.common.external.naver.service.NaverPlaceSearchService;
import co.whitetree.searchservice.domain.place.model.Place;
import co.whitetree.searchservice.domain.place.model.PlaceOrderedSet;
import co.whitetree.searchservice.domain.place.service.PlaceSortService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class PlaceFacade {
    private final KakaoPlaceSearchService kakaoPlaceSearchService;
    private final NaverPlaceSearchService naverPlaceSearchService;
    private final PlaceSortService placeSortService;
    private final PlaceMapper placeMapper;

    public List<PlaceSearchResponse> search(String query) {

        KakaoSearchResponse kakaoSearchResponse = kakaoPlaceSearchService.search(query);
        PlaceOrderedSet kakaoPlaces = PlaceOrderedSet.from(
                kakaoSearchResponse.getDocuments()
                        .stream()
                        .map(Place::ofKakao)
                        .collect(toList()));


        NaverSearchResponse naverSearchResponse = naverPlaceSearchService.search(query);
        PlaceOrderedSet naverPlaces = PlaceOrderedSet.from(
                naverSearchResponse.getItems()
                        .stream()
                        .map(Place::ofNaver)
                        .collect(toList()));

        PlaceOrderedSet places = placeSortService.sort(kakaoPlaces, naverPlaces);

        return places.getPlaces()
                .stream()
                .map(placeMapper::toResponseDto)
                .collect(toList());
    }
}
