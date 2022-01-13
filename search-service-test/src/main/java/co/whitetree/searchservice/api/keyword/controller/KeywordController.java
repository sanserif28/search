package co.whitetree.searchservice.api.keyword.controller;

import co.whitetree.searchservice.api.common.dto.ApiResponse;
import co.whitetree.searchservice.api.common.dto.MetaResponse;
import co.whitetree.searchservice.api.keyword.dto.KeywordResponse;
import co.whitetree.searchservice.api.keyword.facade.KeywordFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class KeywordController {
    private final KeywordFacade keywordFacade;

    @GetMapping("/v1/keywords")
    public ApiResponse<MetaResponse, List<KeywordResponse>> findPopularKeywords() {
        List<KeywordResponse> keywordResponses = keywordFacade.findPopularKeywords();
        MetaResponse metaResponse = MetaResponse.from(keywordResponses.size());
        return ApiResponse.of(metaResponse, keywordResponses);
    }

}
