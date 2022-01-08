package co.whitetree.searchservice.api.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ApiResponse<T> {
    private final T document;

    public static <T> ApiResponse<T> from(T document) {
        return new ApiResponse<>(document);
    }
}
