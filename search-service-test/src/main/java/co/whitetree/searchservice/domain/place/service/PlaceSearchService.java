package co.whitetree.searchservice.domain.place.service;

import co.whitetree.searchservice.domain.place.model.PlaceOrderedSet;
import co.whitetree.searchservice.external.common.util.RestTemplateClient;
import co.whitetree.searchservice.external.provider.SearchProvider;
import co.whitetree.searchservice.external.provider.SearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class PlaceSearchService {
    private final RestTemplateClient restTemplateClient;
    private final List<SearchProvider> searchProviders;

    public List<PlaceOrderedSet> search(String query) {
        return searchProviders.stream()
                .map(searchProvider -> getPlaces(query, searchProvider))
                .map(SearchResponse::toPlaceList)
                .map(PlaceOrderedSet::new)
                .collect(toList());
    }

    private SearchResponse getPlaces(String query, SearchProvider searchProvider) {
        SearchResponse response = restTemplateClient.get(
                searchProvider.getUrl(),
                searchProvider.getHttpHeaders(),
                searchProvider.getSearchResponseType(),
                searchProvider.getQueryParams(query));
        if (isNull(response)) {
            return new SearchResponse(new ArrayList<>());
        }

        return response;
    }
}
