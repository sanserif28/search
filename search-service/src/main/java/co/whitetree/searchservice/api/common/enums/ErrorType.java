package co.whitetree.searchservice.api.common.enums;

import co.whitetree.searchservice.api.common.dto.ErrorResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public enum ErrorType {
    INVALID_FIELDS("Correct parameter required");

    private final String message;

    public ErrorResponse toErrorResponse(List<ErrorResponse.ErrorField> errorFields) {
        return ErrorResponse.builder()
                .errorType(this)
                .message(this.message)
                .errorFields(errorFields)
                .build();
    }
}
