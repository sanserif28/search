package co.whitetree.searchservice.external.provider.naver;

import co.whitetree.searchservice.external.provider.SearchProvider;
import co.whitetree.searchservice.external.provider.naver.dto.NaverSearchQueryParams;
import co.whitetree.searchservice.external.provider.naver.dto.NaverSearchResponse;
import co.whitetree.searchservice.external.provider.naver.property.NaverApiProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NaverSearchProvider implements SearchProvider {
    private static final Integer NAVER_SEARCH_RESULT_COUNT = 5;
    private final NaverApiProperty naverApiProperty;

    @Override
    public String getUrl() {
        return naverApiProperty.getUrl();
    }

    @Override
    public HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-Naver-Client-Id", naverApiProperty.getClientId());
        httpHeaders.set("X-Naver-Client-Secret", naverApiProperty.getClientSecret());
        return httpHeaders;
    }

    @Override
    public Class<NaverSearchResponse> getSearchResponseType() {
        return NaverSearchResponse.class;
    }

    @Override
    public Object getQueryParams(String query) {
        return new NaverSearchQueryParams(query, NAVER_SEARCH_RESULT_COUNT);
    }
}
