package co.whitetree.searchservice.external.provider.naver.dto;

import co.whitetree.searchservice.external.provider.SearchResponse;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NaverSearchResponse extends SearchResponse {

    @JsonCreator
    public NaverSearchResponse(@JsonProperty("items") List<KakaoDocument> documents) {
        super(documents == null ?
                new ArrayList<>() :
                documents.stream()
                .map(k -> new Document(k.getTitle(), k.getAddress(), k.getRoadAddress()))
                .collect(Collectors.toList()));
    }

    public static class KakaoDocument extends Document {

        @JsonCreator
        public KakaoDocument(
                @JsonProperty("title") String title,
                @JsonProperty("address") String address,
                @JsonProperty("roadAddress") String roadAddress) {
            super(title, address, roadAddress);
        }
    }
}