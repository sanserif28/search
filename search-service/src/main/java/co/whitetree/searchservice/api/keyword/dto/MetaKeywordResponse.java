package co.whitetree.searchservice.api.keyword.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class MetaKeywordResponse {
    private final Integer totalCount;
}
