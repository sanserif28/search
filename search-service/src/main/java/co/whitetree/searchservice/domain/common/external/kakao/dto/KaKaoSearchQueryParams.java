package co.whitetree.searchservice.domain.common.external.kakao.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class KaKaoSearchQueryParams {
    private final String query;
}
