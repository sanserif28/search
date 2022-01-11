package co.whitetree.searchservice.external.provider.kakao.dto;

import co.whitetree.searchservice.external.provider.SearchResponse;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class KakaoSearchResponse extends SearchResponse {

    @JsonCreator
    public KakaoSearchResponse(@JsonProperty("documents") List<KakaoDocument> documents) {
        super(documents == null ?
                new ArrayList<>() :
                documents.stream()
                .map(k -> new Document(k.getTitle(), k.getAddress(), k.getRoadAddress()))
                .collect(Collectors.toList()));
    }

    public static class KakaoDocument extends Document {

        @JsonCreator
        public KakaoDocument(
                @JsonProperty("place_name") String title,
                @JsonProperty("address_name") String address,
                @JsonProperty("road_address_name") String roadAddress) {
            super(title, address, roadAddress);
        }
    }
}