package co.whitetree.searchservice.api.place.controller;

import co.whitetree.searchservice.api.common.ApiResponse;
import co.whitetree.searchservice.api.place.dto.PlaceSearchResponse;
import co.whitetree.searchservice.api.place.facade.PlaceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PlaceController {
    private final PlaceFacade placeFacade;

    @GetMapping("/v1/places")
    public ApiResponse<List<PlaceSearchResponse>> searchPlace(@RequestParam String query) {
        List<PlaceSearchResponse> result = placeFacade.search(query);
        return ApiResponse.from(result);
    }
}
