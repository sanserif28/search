package co.whitetree.searchservice.api.place.mapper;

import co.whitetree.searchservice.api.place.dto.PlaceSearchResponse;
import co.whitetree.searchservice.domain.place.model.Place;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlaceMapper {
    PlaceSearchResponse toResponseDto(Place place);
}
