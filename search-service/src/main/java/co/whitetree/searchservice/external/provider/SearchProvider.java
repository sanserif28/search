package co.whitetree.searchservice.external.provider;

import org.springframework.http.HttpHeaders;

public interface SearchProvider {

    String getUrl();

    HttpHeaders getHttpHeaders();

    Class<? extends SearchResponse> getSearchResponseType();

    Object getQueryParams(String query);
}
