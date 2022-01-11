package co.whitetree.searchservice.external.provider.kakao.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class KaKaoSearchQueryParams {
    private final String query;
    private final Integer size;
}
