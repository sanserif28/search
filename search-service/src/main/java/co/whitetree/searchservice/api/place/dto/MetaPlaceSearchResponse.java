package co.whitetree.searchservice.api.place.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MetaPlaceSearchResponse {
    private Integer totalCount;

    public static MetaPlaceSearchResponse from(Integer totalCount) {
        MetaPlaceSearchResponse response = new MetaPlaceSearchResponse();
        response.setTotalCount(totalCount);
        return response;
    }
}
