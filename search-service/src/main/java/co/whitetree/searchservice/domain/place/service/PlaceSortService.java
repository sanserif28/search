package co.whitetree.searchservice.domain.place.service;

import co.whitetree.searchservice.domain.place.model.PlaceOrderedSet;
import org.springframework.stereotype.Service;

@Service
public class PlaceSortService {

    public PlaceOrderedSet sort(PlaceOrderedSet kakaoPlaces, PlaceOrderedSet naverPlaces) {
        PlaceOrderedSet places = kakaoPlaces.orderedIntersection(naverPlaces);
        places.orderedUnion(kakaoPlaces);
        places.orderedUnion(naverPlaces);
        return places;
    }
}
