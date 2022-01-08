package co.whitetree.searchservice.api.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ApiResponse<T, U> {
    private final T meta;
    private final U documents;

    public static <T, U> ApiResponse<T, U> of(T meta, U documents) {
        return new ApiResponse<T, U>(meta, documents);
    }

    public static <T, U> ApiResponse<T, U> of(U documents) {
        return new ApiResponse<T, U>(null, documents);
    }
}
