package co.whitetree.searchservice.domain.common.external.kakao.service;

import co.whitetree.searchservice.domain.common.external.RestTemplateClient;
import co.whitetree.searchservice.domain.common.external.kakao.dto.KaKaoSearchQueryParams;
import co.whitetree.searchservice.domain.common.external.kakao.dto.KakaoSearchResponse;
import co.whitetree.searchservice.domain.common.external.kakao.property.KakaoApiProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KakaoPlaceSearchService {
    private final RestTemplateClient restTemplateClient;
    private final KakaoApiProperty kakaoApiProperty;

    private static final Integer KAKAO_SEARCH_RESULT_COUNT = 5;

    public KakaoSearchResponse search(String query) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "KakaoAK " + kakaoApiProperty.getRestApiKey());

        KaKaoSearchQueryParams queryParams = new KaKaoSearchQueryParams(query, KAKAO_SEARCH_RESULT_COUNT);

        KakaoSearchResponse response = restTemplateClient.get(
                kakaoApiProperty.getUrl(),
                httpHeaders,
                KakaoSearchResponse.class,
                queryParams);

        return response;
    }
}
