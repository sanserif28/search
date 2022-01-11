package co.whitetree.searchservice.external.provider.kakao;

import co.whitetree.searchservice.external.provider.kakao.dto.KakaoSearchResponse;
import co.whitetree.searchservice.external.provider.SearchProvider;
import co.whitetree.searchservice.external.provider.kakao.dto.KaKaoSearchQueryParams;
import co.whitetree.searchservice.external.provider.kakao.property.KakaoApiProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KakaoSearchProvider implements SearchProvider {
    private static final Integer KAKAO_SEARCH_RESULT_COUNT = 5;
    private final KakaoApiProperty kakaoApiProperty;

    @Override
    public String getUrl() {
        return kakaoApiProperty.getUrl();
    }

    @Override
    public HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "KakaoAK " + kakaoApiProperty.getRestApiKey());
        return httpHeaders;
    }

    @Override
    public Class<KakaoSearchResponse> getSearchResponseType() {
        return KakaoSearchResponse.class;
    }

    @Override
    public Object getQueryParams(String query) {
        return new KaKaoSearchQueryParams(query, KAKAO_SEARCH_RESULT_COUNT);
    }
}
