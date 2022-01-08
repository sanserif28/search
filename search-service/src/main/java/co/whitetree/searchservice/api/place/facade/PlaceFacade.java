package co.whitetree.searchservice.api.place.facade;

import co.whitetree.searchservice.api.place.dto.PlaceSearchResponse;
import co.whitetree.searchservice.domain.common.external.kakao.dto.KakaoSearchResponse;
import co.whitetree.searchservice.domain.common.external.kakao.service.KakaoPlaceSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class PlaceFacade {
    private final KakaoPlaceSearchService kakaoPlaceSearchService;

    public List<PlaceSearchResponse> search(String query) {
        KakaoSearchResponse kakaoSearchResponse = kakaoPlaceSearchService.search(query);
        return kakaoSearchResponse.getDocuments()
                .stream()
                .map(document -> new PlaceSearchResponse(document.getPlaceName()))
                .collect(toList());
    }
}
