package co.whitetree.searchservice.api.place.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class MetaPlaceSearchResponse {
    private final Integer totalCount;

    public static MetaPlaceSearchResponse from(Integer totalCount) {
        return new MetaPlaceSearchResponse(totalCount);
    }
}
