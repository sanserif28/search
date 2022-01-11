package co.whitetree.searchservice.external.provider.naver.dto;

import co.whitetree.searchservice.domain.place.model.Place;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@Setter
public class NaverSearchResponse {
    @JsonProperty("items")
    private List<Item> items = new ArrayList<>();

    @Getter
    @Setter
    public static class Item {
        @JsonProperty("title")
        private String title;

        @JsonProperty("address")
        private String address;

        @JsonProperty("roadAddress")
        private String roadAddress;
    }

    public List<Place> toPlaceList() {
        return items.stream()
                .map(Place::ofNaver)
                .collect(toList());
    }
}
