package co.whitetree.searchservice.domain.common.external.kakao.service;

import co.whitetree.searchservice.api.place.dto.PlaceSearchResponse;
import co.whitetree.searchservice.domain.common.external.RestTemplateClient;
import co.whitetree.searchservice.domain.common.external.kakao.dto.KaKaoSearchQueryParams;
import co.whitetree.searchservice.domain.common.external.kakao.dto.KakaoSearchResponse;
import co.whitetree.searchservice.domain.common.external.kakao.property.KakaoApiProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
@Service
public class KakaoPlaceSearchService {
    private final RestTemplateClient restTemplateClient;
    private final KakaoApiProperty kakaoApiProperty;

    public KakaoSearchResponse search(String query) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "KakaoAK " + kakaoApiProperty.getRestApiKey());
        KaKaoSearchQueryParams queryParams = new KaKaoSearchQueryParams(query);

        KakaoSearchResponse response = restTemplateClient.get(
                kakaoApiProperty.getUrl(),
                httpHeaders,
                KakaoSearchResponse.class,
                queryParams);



        return response;
    }
}
