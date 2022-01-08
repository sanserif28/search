package co.whitetree.searchservice.external.naver.service;

import co.whitetree.searchservice.external.common.util.RestTemplateClient;
import co.whitetree.searchservice.external.naver.dto.NaverSearchQueryParams;
import co.whitetree.searchservice.external.naver.dto.NaverSearchResponse;
import co.whitetree.searchservice.external.naver.property.NaverApiProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class NaverPlaceSearchService {
    private final RestTemplateClient restTemplateClient;
    private final NaverApiProperty naverApiProperty;

    private static final Integer NAVER_SEARCH_RESULT_COUNT = 5;

    public NaverSearchResponse search(String query) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-Naver-Client-Id", naverApiProperty.getClientId());
        httpHeaders.set("X-Naver-Client-Secret", naverApiProperty.getClientSecret());
        NaverSearchQueryParams queryParams = new NaverSearchQueryParams(query, NAVER_SEARCH_RESULT_COUNT);

        NaverSearchResponse response = restTemplateClient.get(
                naverApiProperty.getUrl(),
                httpHeaders,
                NaverSearchResponse.class,
                queryParams);
        if (isNull(response)) {
            return new NaverSearchResponse();
        }

        return response;
    }
}
