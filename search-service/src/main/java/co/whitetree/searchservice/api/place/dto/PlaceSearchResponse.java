package co.whitetree.searchservice.api.place.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class PlaceSearchResponse {
    private final String title;
    private final String address;
    private final String roadAddress;
}
