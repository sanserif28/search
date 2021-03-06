package co.whitetree.searchservice.external.provider.naver.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NaverSearchQueryParams {
    private final String query;
    private final Integer display;
}
