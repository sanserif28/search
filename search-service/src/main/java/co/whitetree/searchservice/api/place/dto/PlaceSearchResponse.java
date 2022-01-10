package co.whitetree.searchservice.api.place.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceSearchResponse {
    private String title;
    private String address;
    private String roadAddress;
}
