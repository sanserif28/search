package co.whitetree.searchservice.api.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class MetaResponse {
    @JsonProperty("total_count")
    private final Integer totalCount;

    public static MetaResponse from(Integer totalCount) {
        return MetaResponse.builder()
                .totalCount(totalCount)
                .build();
    }
}

