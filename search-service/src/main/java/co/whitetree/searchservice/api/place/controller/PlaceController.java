package co.whitetree.searchservice.api.place.controller;

import co.whitetree.searchservice.api.common.dto.ApiResponse;
import co.whitetree.searchservice.api.common.dto.MetaResponse;
import co.whitetree.searchservice.api.place.dto.PlaceSearchResponse;
import co.whitetree.searchservice.api.place.facade.PlaceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
public class PlaceController {
    private final PlaceFacade placeFacade;

    @GetMapping("/v1/places")
    public ApiResponse<MetaResponse, List<PlaceSearchResponse>> searchPlace(
            @RequestParam @NotBlank String query) {
        List<PlaceSearchResponse> result = placeFacade.search(query);
        MetaResponse metaResponse = MetaResponse.from(result.size());

        placeFacade.addPlaceKeyword(query);

        return ApiResponse.of(metaResponse, result);
    }
}
