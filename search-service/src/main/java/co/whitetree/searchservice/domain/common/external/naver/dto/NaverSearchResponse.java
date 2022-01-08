package co.whitetree.searchservice.domain.common.external.naver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NaverSearchResponse {
    @JsonProperty("items")
    private List<Item> items;

    @Getter
    @Setter
    public static class Item {
        @JsonProperty("title")
        private String title;
    }
}
