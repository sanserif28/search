package co.whitetree.searchservice.external.provider;

import co.whitetree.searchservice.domain.place.model.Place;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class SearchResponse {
    private final List<Document> documents;

    public SearchResponse(List<Document> documents) {
        this.documents = documents;
    }

    @Getter
    @RequiredArgsConstructor
    public static class Document {
        private final String title;
        private final String address;
        private final String roadAddress;
    }

    public List<Place> toPlaceList() {
        return documents.stream()
                .map(Place::of)
                .collect(toList());
    }
}