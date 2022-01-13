package co.whitetree.searchservice.api.keyword.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class KeywordResponse {
    private final String query;
    private final Long searchCount;
}
