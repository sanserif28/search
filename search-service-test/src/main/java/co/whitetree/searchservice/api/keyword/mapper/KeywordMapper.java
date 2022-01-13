package co.whitetree.searchservice.api.keyword.mapper;

import co.whitetree.searchservice.api.keyword.dto.KeywordResponse;
import co.whitetree.searchservice.domain.keyword.entity.KeywordEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KeywordMapper {
    KeywordResponse toResponseDto(KeywordEntity keywordEntity);
}
