package co.whitetree.searchservice.external.naver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
}
