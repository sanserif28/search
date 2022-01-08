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
        @JsonProperty("id")
        private Long id;

        @JsonProperty("place_name")
        private String placeName;
    }
}
