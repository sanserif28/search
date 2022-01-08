package co.whitetree.searchservice.api.place.facade;

import co.whitetree.searchservice.api.place.dto.PlaceSearchResponse;
import co.whitetree.searchservice.domain.common.external.kakao.dto.KakaoSearchResponse;
import co.whitetree.searchservice.domain.common.external.kakao.service.KakaoPlaceSearchService;
import co.whitetree.searchservice.domain.common.external.naver.dto.NaverSearchResponse;
import co.whitetree.searchservice.domain.common.external.naver.service.NaverPlaceSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class PlaceFacade {
    private final KakaoPlaceSearchService kakaoPlaceSearchService;
    private final NaverPlaceSearchService naverPlaceSearchService;

    public List<PlaceSearchResponse> search(String query) {
        KakaoSearchResponse kakaoSearchResponse = kakaoPlaceSearchService.search(query);
        List<PlaceSearchResponse> kakaoResponse = kakaoSearchResponse.getDocuments()
                .stream()
                .map(document -> new PlaceSearchResponse(document.getPlaceName()))
                .collect(toList());

        NaverSearchResponse naverSearchResponse = naverPlaceSearchService.search(query);
        List<PlaceSearchResponse> naverResponse = naverSearchResponse.getItems()
                .stream()
                .map(item -> new PlaceSearchResponse(item.getTitle()))
                .collect(toList());

        return naverResponse;
    }
}
