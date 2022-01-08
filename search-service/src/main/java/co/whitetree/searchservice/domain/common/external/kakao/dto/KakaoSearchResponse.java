package co.whitetree.searchservice.domain.common.external.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class KakaoSearchResponse {
    @JsonProperty("documents")
    private List<Document> documents;

    @Getter
    @Setter
    public static class Document {
        @JsonProperty("place_name")
        private String title;

        @JsonProperty("address_name")
        private String address;

        @JsonProperty("road_address_name")
        private String roadAddress;
    }
}
