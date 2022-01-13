package co.whitetree.searchservice.api.common.dto;

import co.whitetree.searchservice.api.common.enums.ErrorType;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@Builder
public class ErrorResponse {
    private final ErrorType errorType;
    private final String message;
    private final List<ErrorField> errorFields;

    @Getter
    @RequiredArgsConstructor
    @Builder
    public static class ErrorField {
        private final String field;
        private final String message;
    }
}
