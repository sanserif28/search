package co.whitetree.searchservice.domain.place.policy;

import co.whitetree.searchservice.domain.place.model.PlaceOrderedSet;

import java.util.List;

public interface PlaceSortPolicy {

    PlaceOrderedSet sort(List<PlaceOrderedSet> placesList);
}
